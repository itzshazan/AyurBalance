package com.ayurbalance.ui.profile

import android.content.Context
import com.ayurbalance.AyurBalanceApp
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.time.LocalDate

class ProfileRepository(private val context: Context) {

    private val prefs = context.getSharedPreferences("ayurbalance_notifications", Context.MODE_PRIVATE)

    suspend fun loadProfile(): ProfileState = withContext(Dispatchers.IO) {
        val supabase = AyurBalanceApp.supabaseClient
        val user = supabase.auth.currentUserOrNull()
            ?: return@withContext ProfileState(isLoading = false, error = "Not signed in")

        val userId      = user.id
        val email       = user.email ?: ""
        val displayName = buildDisplayName(user.userMetadata, email)
        val profile     = fetchUserProfile(userId)

        val (vataPct, pittaPct, kaphaPct) = resolvePrakritiPercentages(userId, profile)
        val prakritiType      = profile?.get("prakriti_type")?.toString()?.trim('"') ?: ""
        val constitutionLabel = buildConstitutionLabel(vataPct, pittaPct, kaphaPct)

        ProfileState(
            userId            = userId,
            displayName       = displayName,
            email             = email,
            age               = profile?.intOf("age") ?: 0,
            gender            = profile?.strOf("gender") ?: "",
            prakritiType      = prakritiType,
            constitutionLabel = constitutionLabel,
            vataPct           = vataPct,
            pittaPct          = pittaPct,
            kaphaPct          = kaphaPct,
            dietType          = profile?.strOf("diet_type") ?: "",
            healthConditions  = parseConditions(profile?.get("health_conditions")?.toString()),
            goal              = profile?.strOf("goal") ?: "",
            activityLevel     = profile?.strOf("activity_level") ?: "",
            sleepHours        = profile?.get("sleep_hours")?.toString()?.trim('"')?.toDoubleOrNull() ?: 0.0,
            streakDays        = calculateStreak(userId),
            mealReminders     = prefs.getBoolean("meal_reminders", true),
            hydrationReminders = prefs.getBoolean("hydration_reminders", false),
            dinacharyaReminders = prefs.getBoolean("dinacharya_reminders", false),
            isLoading         = false,
            error             = null
        )
    }

    fun saveNotificationPref(key: String, enabled: Boolean) {
        prefs.edit().putBoolean(key, enabled).apply()
    }

    // ─── Private helpers ──────────────────────────────────────────────────────

    private fun buildDisplayName(metadata: JsonObject?, email: String): String {
        val fromMeta = metadata?.get("full_name")?.toString()?.trim('"')?.takeIf {
            it.isNotBlank() && it != "null"
        } ?: metadata?.get("name")?.toString()?.trim('"')?.takeIf {
            it.isNotBlank() && it != "null"
        }
        return fromMeta ?: email.substringBefore("@")
            .replace(".", " ")
            .replaceFirstChar { it.uppercase() }
    }

    private suspend fun fetchUserProfile(userId: String): JsonObject? = runCatching {
        AyurBalanceApp.supabaseClient.from("user_profiles")
            .select { filter { eq("user_id", userId) } }
            .decodeList<JsonObject>()
            .firstOrNull()
    }.getOrNull()

    private suspend fun resolvePrakritiPercentages(
        userId: String,
        profile: JsonObject?
    ): Triple<Int, Int, Int> {
        // Try user_profiles columns first (set by ResultViewModel after assessment)
        if (profile != null) {
            val v = profile.intOfOrNull("vata_pct")
            val p = profile.intOfOrNull("pitta_pct")
            val k = profile.intOfOrNull("kapha_pct")
            if (v != null && p != null && k != null && (v + p + k) > 0) {
                return Triple(v, p, k)
            }
        }
        // Try prakriti_results table
        val row = runCatching {
            AyurBalanceApp.supabaseClient.from("prakriti_results")
                .select { filter { eq("user_id", userId) } }
                .decodeList<JsonObject>()
                .lastOrNull()
        }.getOrNull()
        if (row != null) {
            val v = row.intOfOrNull("vata_pct")
            val p = row.intOfOrNull("pitta_pct")
            val k = row.intOfOrNull("kapha_pct")
            if (v != null && p != null && k != null && (v + p + k) > 0) {
                return Triple(v, p, k)
            }
        }
        return Triple(33, 33, 34)
    }

    private fun buildConstitutionLabel(vata: Int, pitta: Int, kapha: Int): String {
        val sorted = listOf("Vāta" to vata, "Pitta" to pitta, "Kapha" to kapha)
            .sortedByDescending { it.second }
        val primary   = sorted[0]
        val secondary = sorted[1]
        return if (primary.second - secondary.second < 20) {
            "${primary.first}–${secondary.first}"
        } else {
            primary.first
        }
    }

    private suspend fun calculateStreak(userId: String): Int = runCatching {
        val logs = AyurBalanceApp.supabaseClient.from("food_logs")
            .select { filter { eq("user_id", userId) } }
            .decodeList<JsonObject>()
        val logDates = logs.mapNotNull { row ->
            row["created_at"]?.jsonPrimitive?.content?.take(10)?.let {
                runCatching { LocalDate.parse(it) }.getOrNull()
            }
        }.toSet()
        var streak = 0
        var date = LocalDate.now()
        while (logDates.contains(date)) {
            streak++
            date = date.minusDays(1)
        }
        streak
    }.getOrDefault(0)

    private fun parseConditions(raw: String?): List<String> {
        if (raw.isNullOrBlank() || raw == "null" || raw == "[]") return emptyList()
        return runCatching {
            raw.trim('[', ']').split(",")
                .map { it.trim().trim('"') }
                .filter { it.isNotBlank() }
        }.getOrDefault(emptyList())
    }

    private fun JsonObject.intOf(key: String): Int =
        this[key]?.toString()?.trim('"')?.toIntOrNull() ?: 0

    private fun JsonObject.intOfOrNull(key: String): Int? =
        this[key]?.toString()?.trim('"')?.toIntOrNull()

    private fun JsonObject.strOf(key: String): String =
        this[key]?.toString()?.trim('"')?.takeIf { it != "null" } ?: ""
}
