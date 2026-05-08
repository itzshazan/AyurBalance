package com.ayurbalance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_logs_local")
data class FoodLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val foodName: String,
    val mealType: String,
    val calories: Int,
    val proteinG: Float,
    val carbsG: Float,
    val fatG: Float,
    val doshaTag: String,
    val createdAt: Long = System.currentTimeMillis()
)
