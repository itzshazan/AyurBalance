package com.ayurbalance.ui.reminders

import android.content.Context
import android.content.SharedPreferences
import com.ayurbalance.data.local.AyurBalanceDatabase
import com.ayurbalance.data.local.ReminderEntity
import com.ayurbalance.ui.meals.AyurvedicMealEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

class ReminderRepository(private val context: Context) {

    private val db   = AyurBalanceDatabase.getInstance(context)
    private val dao  = db.reminderDao()
    private val pref: SharedPreferences =
        context.getSharedPreferences("reminder_prefs", Context.MODE_PRIVATE)

    suspend fun ensureDefaultReminders() = withContext(Dispatchers.IO) {
        if (dao.getAll().isEmpty()) {
            dao.insertAll(defaultReminders())
        }
    }

    suspend fun getAllReminders(): List<ReminderEntity> =
        withContext(Dispatchers.IO) { dao.getAll() }

    suspend fun markComplete(id: Int) = withContext(Dispatchers.IO) { dao.markComplete(id) }

    suspend fun setEnabled(id: Int, enabled: Boolean) =
        withContext(Dispatchers.IO) { dao.setEnabled(id, enabled) }

    suspend fun resetDailyCompletions() = withContext(Dispatchers.IO) { dao.resetDailyCompletions() }

    // ── Hydration ──────────────────────────────────────────────────────────────

    fun getHydrationCurrent(): Float = pref.getFloat("hydration_l", 0f)

    fun logWater(addLitres: Float) {
        val current = getHydrationCurrent() + addLitres
        pref.edit().putFloat("hydration_l", current).apply()
    }

    fun getHydrationGoal(dosha: String): Float = when (dosha.uppercase()) {
        "PITTA" -> 3.2f
        "KAPHA" -> 2.5f
        else    -> 3.0f
    }

    fun getDominantDosha(): String =
        pref.getString("dominant_dosha", "VATA") ?: "VATA"

    fun setDominantDosha(dosha: String) {
        pref.edit().putString("dominant_dosha", dosha).apply()
    }

    fun getStreakDays(): Int = pref.getInt("streak_days", 1)

    fun resetHydrationIfNewDay() {
        val lastReset = pref.getLong("hydration_reset_day", 0L)
        val todayStart = todayStartMs()
        if (lastReset < todayStart) {
            pref.edit()
                .putFloat("hydration_l", 0f)
                .putLong("hydration_reset_day", todayStart)
                .apply()
        }
    }

    // ── Seasonal insight ───────────────────────────────────────────────────────

    fun currentInsight(dosha: String): String {
        val ritu = AyurvedicMealEngine.currentRitu()
        return when {
            ritu.contains("Grishma", ignoreCase = true) ->
                "In Grishma (summer), favour cooling foods — cucumber, coriander, coconut water. Avoid heating spices to protect your Pitta."
            ritu.contains("Varsha", ignoreCase = true) ->
                "Varsha season weakens Agni. Choose light, freshly cooked meals. Avoid raw and fermented foods during the rains."
            ritu.contains("Sharad", ignoreCase = true) ->
                "Sharad aggravates Pitta. Begin transitioning to bitter, astringent, and sweet tastes. Avoid excess sour and salty foods."
            ritu.contains("Hemanta", ignoreCase = true) || ritu.contains("Shishira", ignoreCase = true) ->
                "Winter strengthens Agni. Favour warm, oily, and nourishing foods — ghee, sesame, warm soups — to build Ojas."
            else ->
                "Vasanta brings Kapha imbalance. Choose light, dry, and spiced foods. Morning exercise warms and activates your system."
        }
    }

    fun currentSeasonalTip(dosha: String): String {
        val ritu = AyurvedicMealEngine.currentRitu()
        return when (dosha.uppercase()) {
            "PITTA" -> "During $ritu, sip rose water or mint tea mid-morning to keep Pitta cool and your mind clear."
            "KAPHA" -> "During $ritu, start each morning with warm ginger-lemon water to kindle your Agni and energise Kapha."
            else    -> "During $ritu, apply warm sesame oil before bathing to nourish Vata and stabilise your nervous system."
        }
    }

    fun streakMessage(days: Int): String = when {
        days >= 30 -> "30-day streak! Your consistency is building deep Ojas."
        days >= 14 -> "Two weeks of balance. Your Agni is getting stronger every day."
        days >= 7  -> "One week strong! Daily practice is the heart of Dinacharya."
        days >= 3  -> "Three days of mindful living. Keep going — small steps, big shifts."
        else       -> "Consistency is care. Small mindful actions today, better balance tomorrow."
    }

    // ── Vikriti ───────────────────────────────────────────────────────────────

    suspend fun vikritiCheckedToday(): Boolean = withContext(Dispatchers.IO) {
        dao.vikritiCheckedToday(todayStartMs()) > 0
    }

    private fun todayStartMs(): Long {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    // ── Default seed data ──────────────────────────────────────────────────────

    private fun defaultReminders(): List<ReminderEntity> = listOf(
        ReminderEntity(type = "MEAL",        title = "Breakfast",         body = "Start your day with a nourishing meal.",           scheduledHour = 8,  scheduledMinute = 0),
        ReminderEntity(type = "MEAL",        title = "Lunch",             body = "Midday nourishment keeps your Agni strong.",       scheduledHour = 13, scheduledMinute = 0),
        ReminderEntity(type = "MEAL",        title = "Dinner",            body = "Light evening meal for restful digestion.",        scheduledHour = 19, scheduledMinute = 0),
        ReminderEntity(type = "HYDRATION",   title = "Time to hydrate",   body = "Drink a glass of warm water.",                     scheduledHour = 10, scheduledMinute = 0),
        ReminderEntity(type = "DINACHARYA",  title = "Morning routine",   body = "Tongue scraping, warm water, and morning light.",  scheduledHour = 6,  scheduledMinute = 30),
        ReminderEntity(type = "DINACHARYA",  title = "Wind-down routine", body = "Dim lights, avoid screens, prepare for sleep.",   scheduledHour = 21, scheduledMinute = 30),
        ReminderEntity(type = "VIKRITI",     title = "Daily Check-in",    body = "Log your Vikriti — 2 minutes.",                   scheduledHour = 20, scheduledMinute = 0)
    )
}
