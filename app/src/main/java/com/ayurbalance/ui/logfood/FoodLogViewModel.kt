package com.ayurbalance.ui.logfood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayurbalance.AyurBalanceApp
import com.ayurbalance.data.local.AyurBalanceDatabase
import com.ayurbalance.data.local.FoodLogEntity
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.util.Calendar

data class FoodLogState(
    val foodName: String = "",
    val mealType: String = "Lunch",
    val nutrition: FoodNutrition = FoodNutrition(0, 0f, 0f, 0f),
    val ayurveda: AyurvedicProfile = AyurvedicProfile("", "", "", ""),
    val dailyCaloriesConsumed: Int = 0,
    val dailyCaloriesGoal: Int = 2000,
    val saveSuccess: Boolean = false,
    val saveError: String? = null
)

class FoodLogViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AyurBalanceDatabase.getInstance(application)
    private val _state = MutableLiveData(FoodLogState())
    val state: LiveData<FoodLogState> = _state

    fun load(prediction: FoodPrediction, mealType: String) {
        val nutrition = FoodNutritionMapper.forServing(prediction.name)
        val ayurveda  = AyurvedicFoodMapper.get(prediction.name)
        _state.value = _state.value?.copy(
            foodName  = prediction.name,
            mealType  = mealType,
            nutrition = nutrition,
            ayurveda  = ayurveda
        )
        saveFoodLog(prediction, nutrition, mealType)
        fetchDailyProgress()
    }

    private fun saveFoodLog(
        prediction: FoodPrediction,
        nutrition: FoodNutrition,
        mealType: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            // Primary: save to local Room DB (always works, no auth needed)
            runCatching {
                db.foodLogDao().insert(
                    FoodLogEntity(
                        foodName = prediction.name,
                        mealType = mealType,
                        calories = nutrition.caloriesPer100g,
                        proteinG = nutrition.proteinG,
                        carbsG   = nutrition.carbsG,
                        fatG     = nutrition.fatG,
                        doshaTag = prediction.doshaTag
                    )
                )
                _state.postValue(_state.value?.copy(saveSuccess = true))
            }.onFailure { e ->
                _state.postValue(_state.value?.copy(saveError = e.message))
            }

            // Secondary: sync to Supabase when authenticated
            runCatching {
                val supabase = AyurBalanceApp.supabaseClient
                val userId = supabase.auth.currentUserOrNull()?.id ?: return@runCatching
                supabase.from("food_logs").insert(
                    buildJsonObject {
                        put("user_id",        userId)
                        put("food_name",      prediction.name)
                        put("meal_type",      mealType)
                        put("confidence_pct", prediction.confidence)
                        put("calories",       nutrition.caloriesPer100g)
                        put("protein_g",      nutrition.proteinG)
                        put("carbs_g",        nutrition.carbsG)
                        put("fat_g",          nutrition.fatG)
                        put("dosha_tag",      prediction.doshaTag)
                    }
                )
            }
        }
    }

    private fun fetchDailyProgress() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val startOfDay = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }.timeInMillis
                val total = db.foodLogDao().totalCaloriesSince(startOfDay) ?: 0
                _state.postValue(_state.value?.copy(dailyCaloriesConsumed = total))
            }
        }
    }
}
