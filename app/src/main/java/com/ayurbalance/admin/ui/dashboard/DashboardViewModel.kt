package com.ayurbalance.admin.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayurbalance.admin.data.ActivityLogEntry
import com.ayurbalance.admin.data.DashboardMetrics
import com.ayurbalance.admin.data.LogType

class DashboardViewModel : ViewModel() {

    private val _metrics = MutableLiveData(DashboardMetrics())
    val metrics: LiveData<DashboardMetrics> = _metrics

    private val _alerts = MutableLiveData<List<ActivityLogEntry>>()
    val alerts: LiveData<List<ActivityLogEntry>> = _alerts

    init {
        loadAlerts()
    }

    private fun loadAlerts() {
        _alerts.value = listOf(
            ActivityLogEntry("09:31", LogType.WARN, "TFLite model confidence avg dropped to 71% — review training set"),
            ActivityLogEntry("08:55", LogType.ERROR, "3 data deletion requests pending >48 h — DPDP breach risk"),
            ActivityLogEntry("07:12", LogType.INFO, "Supabase RLS audit passed — no policy violations found"),
            ActivityLogEntry("00:04", LogType.INFO, "Nightly image cleanup — 1,204 food images purged (24 h rule)")
        )
    }
}
