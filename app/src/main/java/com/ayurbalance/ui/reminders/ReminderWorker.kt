package com.ayurbalance.ui.reminders

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ReminderWorker(
    private val ctx: Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    companion object {
        const val KEY_TYPE    = "reminder_type"
        const val KEY_TITLE   = "reminder_title"
        const val KEY_BODY    = "reminder_body"
        const val KEY_CHANNEL = "reminder_channel"
        const val KEY_NOTIF_ID = "notification_id"
    }

    override suspend fun doWork(): Result {
        val type    = inputData.getString(KEY_TYPE)    ?: return Result.failure()
        val title   = inputData.getString(KEY_TITLE)   ?: return Result.failure()
        val body    = inputData.getString(KEY_BODY)    ?: return Result.failure()
        val channel = inputData.getString(KEY_CHANNEL) ?: ReminderNotificationHelper.CHANNEL_WELLNESS
        val notifId = inputData.getInt(KEY_NOTIF_ID, type.hashCode())

        ReminderNotificationHelper.showNotification(ctx, notifId, channel, title, body)
        return Result.success()
    }
}
