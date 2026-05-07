package com.ayurbalance.ui.analytics

import android.content.Context
import com.ayurbalance.AyurBalanceApp
import com.ayurbalance.ui.meals.AyurvedicMealEngine
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AnalyticsRepository(private val context: Context) {

    private val fmt = DateTimeFormatter.ISO_LOCAL_DATE

    suspend fun loadAnalytics(): AnalyticsState = withContext(Dispatchers.IO) {
        val supabase = AyurBalanceApp.supabaseClient
        val userId = supabase.auth.currentUserOrNull()?.id
            ?: return@withContext AnalyticsState(isLoading = false, isEmpty = true)

        val prakriti    = fetchPrakriti(userId)
        val primaryDosha = extractPrimaryDosha(prakriti)
        val ritu        = AyurvedicMealEngine.currentRitu()

        val today = LocalDate.now()
        val day0  = today.minusDays(13)   // 14 days of data (7 + prev 7 for delta)

        val allLogs: List<JsonObject> = runCatching {
            supabase.from("food_logs")
                .select {
                    filter {
                        eq("user_id", userId)
                        gte("created_at", day0.format(fmt))
                    }
                }
                .decodeList<JsonObject>()
        }.getOrDefault(emptyList())

        if (allLogs.isEmpty()) {
            return@withContext AnalyticsState(isLoading = false, isEmpty = true)
        }

        val thisWeekStart = today.minusDays(6)
        val prevWeekEnd   = today.minusDays(7)
        val prevWeekStart = today.minusDays(13)

        val thisWeekLogs = allLogs.filter { dateOf(it) >= thisWeekStart }
        val prevWeekLogs = allLogs.filter {
            val d = dateOf(it); d >= prevWeekStart && d <= prevWeekEnd
        }

        // ── Avg daily kcal ───────────────────────────────────────────────────
        val thisKcalMap = groupCaloriesByDay(thisWeekLogs)
        val prevKcalMap = groupCaloriesByDay(prevWeekLogs)
        val avgThis = if (thisKcalMap.isNotEmpty()) thisKcalMap.values.average().toInt() else 0
        val avgPrev = if (prevKcalMap.isNotEmpty()) prevKcalMap.values.average().toInt() else 0
        val kcalDelta = if (avgPrev > 0) (avgThis - avgPrev).toFloat() / avgPrev * 100f else 0f

        // ── 7-day chart bars ─────────────────────────────────────────────────
        val chartBars = (0..6).map { daysBack ->
            val date    = today.minusDays((6 - daysBack).toLong())
            val dayLogs = thisWeekLogs.filter { dateOf(it) == date }
            ChartBar(
                dayLabel         = shortDayLabel(date),
                alignmentPercent = computeDoshaAlignment(dayLogs, primaryDosha),
                isToday          = date == today
            )
        }

        // ── Overall dosha alignment ──────────────────────────────────────────
        val alignThis  = computeDoshaAlignment(thisWeekLogs, primaryDosha)
        val alignPrev  = computeDoshaAlignment(prevWeekLogs, primaryDosha)
        val alignDelta = (alignThis - alignPrev).toFloat()

        // ── Macros ───────────────────────────────────────────────────────────
        val totalCarbs   = thisWeekLogs.sumOf { it.floatOf("carbs_g").toDouble() }
        val totalProtein = thisWeekLogs.sumOf { it.floatOf("protein_g").toDouble() }
        val totalFat     = thisWeekLogs.sumOf { it.floatOf("fat_g").toDouble() }
        val carbsCal     = totalCarbs   * 4
        val proteinCal   = totalProtein * 4
        val fatCal       = totalFat     * 9
        val totalCal     = (carbsCal + proteinCal + fatCal).coerceAtLeast(1.0)

        val carbsPct   = (carbsCal   / totalCal * 100).toInt().coerceIn(0, 100)
        val proteinPct = (proteinCal / totalCal * 100).toInt().coerceIn(0, 100)
        val fatPct     = (fatCal     / totalCal * 100).toInt().coerceIn(0, 100)

        AnalyticsState(
            avgDailyKcal          = avgThis,
            kcalDeltaPercent      = kcalDelta,
            doshaAlignmentPercent = alignThis,
            doshaAlignmentDelta   = alignDelta,
            chartBars             = chartBars,
            carbsPercent          = carbsPct,
            proteinPercent        = proteinPct,
            fatPercent            = fatPct,
            insightText           = generateInsight(alignThis, primaryDosha, ritu, carbsPct, proteinPct, fatPct),
            isLoading             = false,
            isEmpty               = thisWeekLogs.isEmpty()
        )
    }

    // ─── Private helpers ──────────────────────────────────────────────────────

    private suspend fun fetchPrakriti(userId: String): String = runCatching {
        val rows = AyurBalanceApp.supabaseClient
            .from("user_profiles")
            .select { filter { eq("user_id", userId) } }
            .decodeList<JsonObject>()
        rows.firstOrNull()?.get("prakriti_type")
            ?.let { it.toString().trim('"') } ?: "VATA"
    }.getOrDefault("VATA")

    private fun extractPrimaryDosha(prakriti: String): String {
        val upper = prakriti.uppercase().trim()
        return when {
            upper.startsWith("PI") -> "PITTA"
            upper.startsWith("KA") -> "KAPHA"
            else                   -> "VATA"
        }
    }

    private fun dateOf(row: JsonObject): LocalDate = runCatching {
        val ts = row["created_at"]?.jsonPrimitive?.content ?: return@runCatching LocalDate.now()
        LocalDate.parse(ts.take(10))
    }.getOrDefault(LocalDate.now())

    private fun groupCaloriesByDay(logs: List<JsonObject>): Map<LocalDate, Int> =
        logs.groupBy { dateOf(it) }
            .mapValues { (_, rows) -> rows.sumOf { it.intOf("calories") } }

    private fun computeDoshaAlignment(logs: List<JsonObject>, primaryDosha: String): Int {
        if (logs.isEmpty()) return 0
        val matching = logs.count { row ->
            val tag = row["dosha_tag"]?.jsonPrimitive?.content ?: ""
            primaryDosha in tag.uppercase()
        }
        return (matching.toFloat() / logs.size * 100).toInt()
    }

    private fun shortDayLabel(date: LocalDate): String = when (date.dayOfWeek.value) {
        1 -> "M"; 2 -> "T"; 3 -> "W"; 4 -> "T"; 5 -> "F"; 6 -> "S"; else -> "S"
    }

    private fun generateInsight(
        alignment: Int, dosha: String, ritu: String,
        carbs: Int, protein: Int, fat: Int
    ): String {
        val doshaAdvice = when (dosha) {
            "PITTA" -> when {
                alignment >= 75 -> "Your cooling diet is pacifying Pitta beautifully this week. Keep prioritising bitter and sweet tastes during $ritu."
                alignment >= 50 -> "Pitta is moderately balanced. Try reducing spicy foods and include more coconut and coriander in your meals."
                else            -> "Pitta may be aggravated. Favour cooling foods — cucumber, curd rice, and mint — especially during $ritu season."
            }
            "KAPHA" -> when {
                alignment >= 75 -> "Excellent Kapha balance this week! Light, well-spiced meals are keeping your energy vibrant and digestion sharp."
                alignment >= 50 -> "Kapha is fairly balanced. Add more ginger and black pepper to stimulate digestive fire during $ritu."
                else            -> "Kapha may need attention. Favour light, dry, and warming foods. Avoid excess dairy and sweets during $ritu."
            }
            else -> when { // VATA
                alignment >= 75 -> "Vata is well-nourished this week. Warm, oily, and grounding meals are bringing stability and calm to your system."
                alignment >= 50 -> "Vata alignment is moderate. Include more warm soups and ghee to soothe Vata during the $ritu season."
                else            -> "Vata may be unsettled. Focus on warm, moist, nourishing meals. Consistent meal timing is especially important now."
            }
        }
        val macroNote = when {
            protein < 20 -> " Consider adding more dal, legumes, or paneer to support tissue nourishment (Dhatu)."
            fat > 45     -> " Moderate your fat intake — focus on sattvic fats like ghee and coconut oil in small quantities."
            carbs > 65   -> " Balance your carbohydrate intake with more protein-rich foods for sustained Ojas."
            else         -> ""
        }
        return doshaAdvice + macroNote
    }

    private fun JsonObject.intOf(key: String): Int =
        this[key]?.jsonPrimitive?.content?.toIntOrNull() ?: 0

    private fun JsonObject.floatOf(key: String): Float =
        this[key]?.jsonPrimitive?.content?.toFloatOrNull() ?: 0f
}
