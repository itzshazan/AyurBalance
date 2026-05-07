package com.ayurbalance.ui.analytics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AnalyticsViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = AnalyticsRepository(app)

    private val _state = MutableLiveData(AnalyticsState())
    val state: LiveData<AnalyticsState> = _state

    init {
        load()
    }

    fun load() {
        _state.value = AnalyticsState(isLoading = true)
        viewModelScope.launch {
            _state.value = repository.loadAnalytics()
        }
    }
}
