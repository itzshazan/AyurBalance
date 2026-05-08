package com.ayurbalance.ui.reminders

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.ayurbalance.R

object ReminderNotificationHelper {

    const val CHANNEL_MEAL       = "ayurbalance_meal"
    const val CHANNEL_HYDRATION  = "ayurbalance_hydration"
    const val CHANNEL_DINACHARYA = "ayurbalance_dinacharya"
    const val CHANNEL_VIKRITI    = "ayurbalance_vikriti"
    const val CHANNEL_WELLNESS   = "ayurbalance_wellness"

    fun createChannels(context: Context) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channels = listOf(
            NotificationChannel(CHANNEL_MEAL,       "Meal Reminders",       NotificationManager.IMPORTANCE_DEFAULT).apply { description = "Personalized meal timing alerts" },
            NotificationChannel(CHANNEL_HYDRATION,  "Hydration Reminders",  NotificationManager.IMPORTANCE_LOW).apply    { description = "Gentle hydration nudges" },
            NotificationChannel(CHANNEL_DINACHARYA, "Dinacharya Routines",  NotificationManager.IMPORTANCE_DEFAULT).apply { description = "Daily Ayurvedic routine reminders" },
            NotificationChannel(CHANNEL_VIKRITI,    "Daily Check-in",       NotificationManager.IMPORTANCE_DEFAULT).apply { description = "Vikriti imbalance check-in reminders" },
            NotificationChannel(CHANNEL_WELLNESS,   "Wellness Insights",    NotificationManager.IMPORTANCE_LOW).apply    { description = "Seasonal tips and insights" }
        )
        channels.forEach { nm.createNotificationChannel(it) }
    }

    fun showNotification(
        context: Context,
        notificationId: Int,
        channelId: String,
        title: String,
        body: String,
        deepLinkAction: String = ReminderActivity.ACTION_OPEN_REMINDERS
    ) {
        val intent = Intent(context, ReminderActivity::class.java).apply {
            action = deepLinkAction
            flags  = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pi = PendingIntent.getActivity(
            context, notificationId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_leaf_small_green)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setContentIntent(pi)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(notificationId, notification)
    }
}
