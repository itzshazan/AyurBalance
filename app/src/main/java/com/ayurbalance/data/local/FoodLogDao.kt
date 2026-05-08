package com.ayurbalance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FoodLogEntity)

    @Query("SELECT * FROM food_logs_local WHERE createdAt >= :since ORDER BY createdAt DESC")
    suspend fun getSince(since: Long): List<FoodLogEntity>

    @Query("SELECT SUM(calories) FROM food_logs_local WHERE createdAt >= :since")
    suspend fun totalCaloriesSince(since: Long): Int?
}
