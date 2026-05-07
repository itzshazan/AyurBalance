package com.ayurbalance.ui.meals

import android.content.Context
import com.ayurbalance.AyurBalanceApp
import com.ayurbalance.data.local.AyurBalanceDatabase
import com.ayurbalance.data.local.CachedMealPlanEntity
import com.ayurbalance.data.models.MealItem
import com.ayurbalance.data.models.SeasonalProtocol
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MealPlanRepository(private val context: Context) {

    private val db  by lazy { AyurBalanceDatabase.getInstance(context) }
    private val dao by lazy { db.mealPlanDao() }
    private val fmt    = DateTimeFormatter.ISO_LOCAL_DATE
    private val engine = AyurvedicMealEngine

    // Cache TTL: 12 hours
    private val cacheTtlMs = 12 * 60 * 60 * 1000L

    suspend fun getMealsForDate(date: LocalDate, prakriti: String, ritu: String): List<MealItem> =
        withContext(Dispatchers.IO) {
            // Always generate fresh meals — use cache as an optimisation if Room is available
            val generated = engine.generateDayPlan(date, prakriti, ritu)

            runCatching {
                val key    = date.format(fmt)
                val cached = dao.getPlanForDate(key)

                if (cached != null &&
                    cached.prakriti == prakriti &&
                    System.currentTimeMillis() - cached.generatedAt < cacheTtlMs) {
                    return@withContext Json.decodeFromString<List<MealItem>>(cached.mealsJson)
                }

                dao.insertPlan(
                    CachedMealPlanEntity(
                        dateKey  = key,
                        mealsJson = Json.encodeToString(generated),
                        ritu     = ritu,
                        prakriti = prakriti
                    )
                )
            } // Room failure is silent — we fall through to the generated plan

            syncToSupabase(date, generated, ritu)
            generated
        }

    suspend fun getSeasonalProtocol(ritu: String): SeasonalProtocol =
        engine.getSeasonalProtocol(ritu)

    suspend fun fetchPrakriti(): String = withContext(Dispatchers.IO) {
        runCatching {
            val supabase = AyurBalanceApp.supabaseClient
            val userId = supabase.auth.currentUserOrNull()?.id ?: return@runCatching "VATA"
            val rows = supabase.from("user_profiles")
                .select { filter { eq("user_id", userId) } }
                .decodeList<kotlinx.serialization.json.JsonObject>()
            rows.firstOrNull()?.get("prakriti_type")
                ?.let { v -> v.toString().trim('"') }
                ?: "VATA"
        }.getOrDefault("VATA")
    }

    fun swapOptions(meal: MealItem, prakriti: String, ritu: String): List<MealItem> =
        engine.getSwapOptions(meal, prakriti, ritu)

    suspend fun saveSwap(date: LocalDate, oldMeal: MealItem, newMeal: MealItem, prakriti: String, ritu: String) =
        withContext(Dispatchers.IO) {
            runCatching {
                val key    = date.format(fmt)
                val cached = dao.getPlanForDate(key) ?: return@runCatching
                val meals  = Json.decodeFromString<List<MealItem>>(cached.mealsJson)
                    .map { if (it.id == oldMeal.id) newMeal else it }
                dao.insertPlan(cached.copy(mealsJson = Json.encodeToString(meals)))
            }
        }

    private suspend fun syncToSupabase(date: LocalDate, meals: List<MealItem>, ritu: String) {
        runCatching {
            val supabase = AyurBalanceApp.supabaseClient
            val userId = supabase.auth.currentUserOrNull()?.id ?: return
            supabase.from("meal_plans").upsert(
                value = buildJsonObject {
                    put("user_id", userId)
                    put("date", date.format(fmt))
                    put("ritu", ritu)
                    put("meal_count", meals.size)
                },
                onConflict = "user_id,date"
            )
        }
    }
}
