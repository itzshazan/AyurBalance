package com.ayurbalance.admin.ui.ml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayurbalance.admin.data.MLMetrics

class MLMonitorViewModel : ViewModel() {

    private val _metrics = MutableLiveData(MLMetrics())
    val metrics: LiveData<MLMetrics> = _metrics
}
