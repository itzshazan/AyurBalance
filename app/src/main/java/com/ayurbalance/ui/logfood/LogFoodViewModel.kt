package com.ayurbalance.ui.logfood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class FoodPrediction(
    val name: String,
    val confidence: Int,       // 0-99
    val doshaTag: String,
    val caloriesPer100g: Int
)

data class LogFoodState(
    val phase: Phase = Phase.AWAITING,
    val modelReady: Boolean = false,
    val predictions: List<FoodPrediction> = emptyList(),
    val selectedIndex: Int = 0,
    val activeTab: Tab = Tab.CAMERA
) {
    enum class Phase {
        AWAITING,      // camera live, no confident result yet
        IDENTIFIED     // top prediction >= threshold
    }

    enum class Tab { CAMERA, SEARCH, BARCODE, VOICE }

    val selected: FoodPrediction? get() = predictions.getOrNull(selectedIndex)
}

class LogFoodViewModel : ViewModel() {

    private val _state = MutableLiveData(LogFoodState())
    val state: LiveData<LogFoodState> = _state

    fun setModelReady(ready: Boolean) {
        _state.value = _state.value?.copy(modelReady = ready)
    }

    fun updatePredictions(predictions: List<FoodPrediction>) {
        val phase = if (predictions.isNotEmpty() && predictions[0].confidence >= 40) {
            LogFoodState.Phase.IDENTIFIED
        } else {
            LogFoodState.Phase.AWAITING
        }
        _state.value = _state.value?.copy(
            phase = phase,
            predictions = predictions,
            selectedIndex = 0
        )
    }

    fun selectCandidate(index: Int) {
        _state.value = _state.value?.copy(selectedIndex = index)
    }

    fun setTab(tab: LogFoodState.Tab) {
        _state.value = _state.value?.copy(activeTab = tab)
    }

    fun reset() {
        _state.value = _state.value?.copy(
            phase = LogFoodState.Phase.AWAITING,
            predictions = emptyList(),
            selectedIndex = 0
        )
    }
}
