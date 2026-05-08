package com.ayurbalance.ui.reminders

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object ReminderScheduler {

    fun scheduleAll(context: Context) {
        scheduleMealReminder(context, "meal_breakfast", "Breakfast reminder",
            "Start your day with a nourishing Ayurvedic breakfast.", 8, 0,
            ReminderNotificationHelper.CHANNEL_MEAL, 101)

        scheduleMealReminder(context, "meal_lunch", "Lunch reminder",
            "It's time for your midday meal. Balance your Pitta with a warm, calming lunch.", 13, 0,
            ReminderNotificationHelper.CHANNEL_MEAL, 102)

        scheduleMealReminder(context, "meal_dinner", "Dinner reminder",
            "Evening meal time. Choose light, easily digestible foods for restful digestion.", 19, 0,
            ReminderNotificationHelper.CHANNEL_MEAL, 103)

        scheduleMealReminder(context, "dinacharya_morning", "Morning Dinacharya",
            "Begin your day with tongue scraping and warm water to awaken Agni.", 6, 30,
            ReminderNotificationHelper.CHANNEL_DINACHARYA, 201)

        scheduleMealReminder(context, "dinacharya_evening", "Wind-down routine",
            "Dim the lights, avoid screens. Let your nervous system settle before sleep.", 21, 30,
            ReminderNotificationHelper.CHANNEL_DINACHARYA, 202)

        scheduleMealReminder(context, "vikriti_checkin", "Daily Check-in",
            "How are you feeling today? Log your Vikriti to track your balance — just 2 minutes.", 20, 0,
            ReminderNotificationHelper.CHANNEL_VIKRITI, 301)

        scheduleHydrationChecks(context)
    }

    private fun scheduleMealReminder(
        context: Context,
        tag: String,
        title: String,
        body: String,
        hour: Int,
        minute: Int,
        channel: String,
        notifId: Int
    ) {
        val delay = initialDelayMs(hour, minute)
        val data = Data.Builder()
            .putString(ReminderWorker.KEY_TYPE, tag)
            .putString(ReminderWorker.KEY_TITLE, title)
            .putString(ReminderWorker.KEY_BODY, body)
            .putString(ReminderWorker.KEY_CHANNEL, channel)
            .putInt(ReminderWorker.KEY_NOTIF_ID, notifId)
            .build()

        val request = PeriodicWorkRequestBuilder<ReminderWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .addTag(tag)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            tag, ExistingPeriodicWorkPolicy.KEEP, request
        )
    }

    private fun scheduleHydrationChecks(context: Context) {
        val data = Data.Builder()
            .putString(ReminderWorker.KEY_TYPE, "hydration")
            .putString(ReminderWorker.KEY_TITLE, "Time to hydrate")
            .putString(ReminderWorker.KEY_BODY, "Stay balanced — drink a glass of warm water to support your Agni.")
            .putString(ReminderWorker.KEY_CHANNEL, ReminderNotificationHelper.CHANNEL_HYDRATION)
            .putInt(ReminderWorker.KEY_NOTIF_ID, 401)
            .build()

        val request = PeriodicWorkRequestBuilder<ReminderWorker>(2, TimeUnit.HOURS)
            .setInputData(data)
            .addTag("hydration_periodic")
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "hydration_periodic", ExistingPeriodicWorkPolicy.KEEP, request
        )
    }

    fun cancelAll(context: Context) {
        WorkManager.getInstance(context).cancelAllWork()
    }

    fun cancelTag(context: Context, tag: String) {
        WorkManager.getInstance(context).cancelAllWorkByTag(tag)
    }

    private fun initialDelayMs(targetHour: Int, targetMinute: Int): Long {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, targetHour)
            set(Calendar.MINUTE, targetMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (target.before(now)) target.add(Calendar.DAY_OF_YEAR, 1)
        return target.timeInMillis - now.timeInMillis
    }
}
