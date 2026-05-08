package com.ayurbalance.ui.reminders

import com.ayurbalance.data.local.ReminderEntity

sealed class ReminderItem {
    data class MealCard(
        val reminder: ReminderEntity,
        val mealName: String,
        val doshaNote: String,
        val calories: Int
    ) : ReminderItem()

    data class HydrationCard(
        val currentL: Float,
        val goalL: Float,
        val dosha: String
    ) : ReminderItem()

    data class DinacharyaCard(val reminder: ReminderEntity) : ReminderItem()

    data class VikritiCard(val isCompleted: Boolean) : ReminderItem()

    data class InsightCard(val text: String, val ritu: String) : ReminderItem()

    data class StreakCard(val days: Int, val message: String) : ReminderItem()

    data class SeasonalTipCard(val tip: String, val ritu: String) : ReminderItem()
}
