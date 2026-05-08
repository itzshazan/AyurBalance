package com.ayurbalance.ui.profile

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.ayurbalance.R
import java.util.Calendar

class NotificationSettingsManager(private val context: Context) {

    companion object {
        const val CHANNEL_MEAL       = "ayur_meal_reminders"
        const val CHANNEL_HYDRATION  = "ayur_hydration_reminders"
        const val CHANNEL_DINACHARYA = "ayur_dinacharya_reminders"

        private const val REQ_MEAL_1       = 1001
        private const val REQ_MEAL_2       = 1002
        private const val REQ_MEAL_3       = 1003
        private const val REQ_HYDRATION    = 1010
        private const val REQ_DINACHARYA   = 1020

        const val EXTRA_TITLE   = "notif_title"
        const val EXTRA_MESSAGE = "notif_message"
        const val EXTRA_CHANNEL = "notif_channel"
    }

    private val notifManager: NotificationManager =
        context.getSystemService(NotificationManager::class.java)

    init {
        createChannels()
    }

    private fun createChannels() {
        listOf(
            NotificationChannel(
                CHANNEL_MEAL, "Meal Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Daily meal time reminders" },
            NotificationChannel(
                CHANNEL_HYDRATION, "Hydration Reminders",
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Regular water intake reminders" },
            NotificationChannel(
                CHANNEL_DINACHARYA, "Dinacharya Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Daily Ayurvedic routine reminders" }
        ).forEach { notifManager.createNotificationChannel(it) }
    }

    fun scheduleMealReminders(enabled: Boolean) {
        val am = context.getSystemService(AlarmManager::class.java)
        if (enabled) {
            scheduleDaily(am, REQ_MEAL_1, 8, 0,
                "Breakfast Time 🌅", "Your morning meal awaits. Eat warm, fresh food.", CHANNEL_MEAL)
            scheduleDaily(am, REQ_MEAL_2, 13, 0,
                "Lunch Reminder ☀️", "Midday is the best time for your largest meal.", CHANNEL_MEAL)
            scheduleDaily(am, REQ_MEAL_3, 19, 0,
                "Dinner Time 🌙", "Eat light in the evening for restful sleep.", CHANNEL_MEAL)
        } else {
            listOf(REQ_MEAL_1, REQ_MEAL_2, REQ_MEAL_3).forEach { cancel(am, it) }
        }
    }

    fun scheduleHydrationReminders(enabled: Boolean) {
        val am = context.getSystemService(AlarmManager::class.java)
        if (enabled) {
            scheduleRepeating(am, REQ_HYDRATION, 9, 0,
                "Stay Hydrated 💧", "Warm water or herbal tea supports Agni.", CHANNEL_HYDRATION,
                AlarmManager.INTERVAL_HOUR * 2)
        } else {
            cancel(am, REQ_HYDRATION)
        }
    }

    fun scheduleDinacharyaReminders(enabled: Boolean) {
        val am = context.getSystemService(AlarmManager::class.java)
        if (enabled) {
            scheduleDaily(am, REQ_DINACHARYA, 6, 0,
                "Morning Routine 🌿", "Begin your Dinacharya — oil pulling, yoga, and warm water.", CHANNEL_DINACHARYA)
        } else {
            cancel(am, REQ_DINACHARYA)
        }
    }

    private fun scheduleDaily(
        am: AlarmManager, requestCode: Int,
        hour: Int, minute: Int,
        title: String, message: String, channel: String
    ) {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) add(Calendar.DAY_OF_YEAR, 1)
        }
        am.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            cal.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            buildIntent(requestCode, title, message, channel)
        )
    }

    private fun scheduleRepeating(
        am: AlarmManager, requestCode: Int,
        hour: Int, minute: Int,
        title: String, message: String, channel: String, intervalMs: Long
    ) {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) add(Calendar.HOUR_OF_DAY, 1)
        }
        am.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, cal.timeInMillis, intervalMs,
            buildIntent(requestCode, title, message, channel)
        )
    }

    private fun cancel(am: AlarmManager, requestCode: Int) {
        am.cancel(buildIntent(requestCode, "", "", ""))
    }

    private fun buildIntent(requestCode: Int, title: String, message: String, channel: String): PendingIntent {
        val intent = Intent(context, NotificationBroadcastReceiver::class.java).apply {
            putExtra(EXTRA_TITLE, title)
            putExtra(EXTRA_MESSAGE, message)
            putExtra(EXTRA_CHANNEL, channel)
        }
        return PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title   = intent.getStringExtra(NotificationSettingsManager.EXTRA_TITLE) ?: return
        val message = intent.getStringExtra(NotificationSettingsManager.EXTRA_MESSAGE) ?: return
        val channel = intent.getStringExtra(NotificationSettingsManager.EXTRA_CHANNEL) ?: return

        val nm = context.getSystemService(NotificationManager::class.java)
        val notif = NotificationCompat.Builder(context, channel)
            .setSmallIcon(R.drawable.ic_vegan_leaf)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        nm.notify(System.currentTimeMillis().toInt(), notif)
    }
}
