package com.ayurbalance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: CachedMealPlanEntity)

    @Query("SELECT * FROM meal_plan_cache WHERE dateKey = :dateKey LIMIT 1")
    suspend fun getPlanForDate(dateKey: String): CachedMealPlanEntity?

    @Query("SELECT * FROM meal_plan_cache WHERE dateKey BETWEEN :from AND :to ORDER BY dateKey")
    suspend fun getPlansForRange(from: String, to: String): List<CachedMealPlanEntity>

    @Query("DELETE FROM meal_plan_cache WHERE generatedAt < :olderThanMs")
    suspend fun deleteStale(olderThanMs: Long)
}
