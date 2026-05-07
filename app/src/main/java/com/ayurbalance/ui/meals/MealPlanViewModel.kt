package com.ayurbalance.ui.meals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayurbalance.data.models.MealItem
import com.ayurbalance.data.models.SeasonalProtocol
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class MealPlanState(
    val selectedDate: LocalDate        = LocalDate.now(),
    val currentWeek: List<LocalDate>   = emptyList(),
    val meals: List<MealItem>          = emptyList(),
    val prakriti: String               = "VATA",
    val ritu: String                   = "",
    val seasonalProtocol: SeasonalProtocol? = null,
    val isLoading: Boolean             = true,
    val error: String?                 = null
)

class MealPlanViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = MealPlanRepository(app)
    private val engine     = AyurvedicMealEngine

    private val _state = MutableLiveData(MealPlanState())
    val state: LiveData<MealPlanState> = _state

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            val ritu     = engine.currentRitu()
            val prakriti = repository.fetchPrakriti()
            val today    = LocalDate.now()
            val week     = buildWeek(today)
            val protocol = repository.getSeasonalProtocol(ritu)

            _state.value = _state.value?.copy(
                ritu             = ritu,
                prakriti         = prakriti,
                currentWeek      = week,
                seasonalProtocol = protocol,
                isLoading        = true
            )

            loadMealsForDate(today, prakriti, ritu)
        }
    }

    fun selectDate(date: LocalDate) {
        val current = _state.value ?: return
        _state.value = current.copy(selectedDate = date, isLoading = true)
        viewModelScope.launch {
            loadMealsForDate(date, current.prakriti, current.ritu)
        }
    }

    fun swapMeal(oldMeal: MealItem, newMeal: MealItem) {
        val current = _state.value ?: return
        val updated = current.meals.map { if (it.id == oldMeal.id) newMeal else it }
        _state.value = current.copy(meals = updated)
        viewModelScope.launch {
            repository.saveSwap(current.selectedDate, oldMeal, newMeal, current.prakriti, current.ritu)
        }
    }

    fun getSwapOptions(meal: MealItem): List<MealItem> {
        val current = _state.value ?: return emptyList()
        return repository.swapOptions(meal, current.prakriti, current.ritu)
    }

    private suspend fun loadMealsForDate(date: LocalDate, prakriti: String, ritu: String) {
        runCatching {
            val meals = repository.getMealsForDate(date, prakriti, ritu)
            _state.postValue(_state.value?.copy(
                meals     = meals,
                isLoading = false,
                error     = null
            ))
        }.onFailure { e ->
            _state.postValue(_state.value?.copy(
                isLoading = false,
                error     = e.message
            ))
        }
    }

    private fun buildWeek(anchor: LocalDate): List<LocalDate> {
        val monday = anchor.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        return (0..6).map { monday.plusDays(it.toLong()) }
    }
}
