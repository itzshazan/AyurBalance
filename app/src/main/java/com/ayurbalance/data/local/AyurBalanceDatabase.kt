package com.ayurbalance.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CachedMealPlanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AyurBalanceDatabase : RoomDatabase() {

    abstract fun mealPlanDao(): MealPlanDao

    companion object {
        @Volatile private var INSTANCE: AyurBalanceDatabase? = null

        fun getInstance(context: Context): AyurBalanceDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AyurBalanceDatabase::class.java,
                    "ayurbalance_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
