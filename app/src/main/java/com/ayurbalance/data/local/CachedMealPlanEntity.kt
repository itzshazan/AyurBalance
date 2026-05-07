package com.ayurbalance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_plan_cache")
data class CachedMealPlanEntity(
    @PrimaryKey val dateKey: String,   // "yyyy-MM-dd"
    val mealsJson: String,             // JSON of List<MealItem>
    val ritu: String,
    val prakriti: String,
    val generatedAt: Long = System.currentTimeMillis()
)
