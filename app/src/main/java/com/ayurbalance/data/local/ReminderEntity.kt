package com.ayurbalance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val title: String,
    val body: String,
    val scheduledHour: Int,
    val scheduledMinute: Int,
    val isEnabled: Boolean = true,
    val isCompleted: Boolean = false,
    val repeatDaily: Boolean = true,
    val snoozeMinutes: Int = 15,
    val createdAt: Long = System.currentTimeMillis()
)
