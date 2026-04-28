package com.ayurbalance.ui.logfood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class FoodCandidate(
    val name: String,
    val confidence: Int,
    val doshaTag: String,
    val caloriesPer100g: Int
)

data class LogFoodState(
    val phase: Phase = Phase.SCANNING,
    val candidates: List<FoodCandidate> = emptyList(),
    val selectedIndex: Int = 0,
    val activeTab: Tab = Tab.CAMERA
) {
    enum class Phase { SCANNING, IDENTIFIED }
    enum class Tab { CAMERA, SEARCH, BARCODE, VOICE }

    val selected: FoodCandidate? get() = candidates.getOrNull(selectedIndex)
}

class LogFoodViewModel : ViewModel() {

    private val _state = MutableLiveData<LogFoodState>()
    val state: LiveData<LogFoodState> = _state

    init {
        startScan()
    }

    fun startScan() {
        _state.value = LogFoodState(phase = LogFoodState.Phase.SCANNING)
        viewModelScope.launch {
            delay(1800)
            _state.postValue(
                LogFoodState(
                    phase = LogFoodState.Phase.IDENTIFIED,
                    candidates = listOf(
                        FoodCandidate("Dal Makhani",  87, "PITTA HEAVY",    420),
                        FoodCandidate("Rajma curry",   9, "PITTA MODERATE", 310),
                        FoodCandidate("Kali dal",      4, "KAPHA LIGHT",    180)
                    ),
                    selectedIndex = 0
                )
            )
        }
    }

    fun selectCandidate(index: Int) {
        _state.value = _state.value?.copy(selectedIndex = index)
    }

    fun setTab(tab: LogFoodState.Tab) {
        _state.value = _state.value?.copy(activeTab = tab)
    }
}
