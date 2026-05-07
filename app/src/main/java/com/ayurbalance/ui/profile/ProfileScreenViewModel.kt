package com.ayurbalance.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileScreenViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = ProfileRepository(app)

    private val _state = MutableLiveData(ProfileState())
    val state: LiveData<ProfileState> = _state

    init {
        load()
    }

    fun load() {
        _state.value = ProfileState(isLoading = true)
        viewModelScope.launch {
            _state.value = repository.loadProfile()
        }
    }

    fun setMealReminders(enabled: Boolean) {
        repository.saveNotificationPref("meal_reminders", enabled)
        _state.value = _state.value?.copy(mealReminders = enabled)
    }

    fun setHydrationReminders(enabled: Boolean) {
        repository.saveNotificationPref("hydration_reminders", enabled)
        _state.value = _state.value?.copy(hydrationReminders = enabled)
    }

    fun setDinacharyaReminders(enabled: Boolean) {
        repository.saveNotificationPref("dinacharya_reminders", enabled)
        _state.value = _state.value?.copy(dinacharyaReminders = enabled)
    }
}
