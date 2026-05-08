package com.ayurbalance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: ReminderEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(reminders: List<ReminderEntity>)

    @Update
    suspend fun update(reminder: ReminderEntity)

    @Query("SELECT * FROM reminders ORDER BY scheduledHour ASC, scheduledMinute ASC")
    suspend fun getAll(): List<ReminderEntity>

    @Query("SELECT * FROM reminders WHERE type = :type")
    suspend fun getByType(type: String): List<ReminderEntity>

    @Query("UPDATE reminders SET isCompleted = 1 WHERE id = :id")
    suspend fun markComplete(id: Int)

    @Query("UPDATE reminders SET isEnabled = :enabled WHERE id = :id")
    suspend fun setEnabled(id: Int, enabled: Boolean)

    @Query("UPDATE reminders SET isCompleted = 0 WHERE repeatDaily = 1")
    suspend fun resetDailyCompletions()

    @Query("SELECT COUNT(*) FROM reminders WHERE type = 'VIKRITI' AND isCompleted = 1 AND createdAt >= :todayStartMs")
    suspend fun vikritiCheckedToday(todayStartMs: Long): Int

    @Query("DELETE FROM reminders WHERE id = :id")
    suspend fun delete(id: Int)
}
