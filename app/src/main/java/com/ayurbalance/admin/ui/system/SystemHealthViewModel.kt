package com.ayurbalance.admin.ui.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayurbalance.admin.data.ServiceStatus
import com.ayurbalance.admin.data.SystemService

class SystemHealthViewModel : ViewModel() {

    private val _services = MutableLiveData(sampleServices())
    val services: LiveData<List<SystemService>> = _services

    private val _uptime = MutableLiveData("99.87%")
    val uptime: LiveData<String> = _uptime

    private val _avgLatency = MutableLiveData("142 ms")
    val avgLatency: LiveData<String> = _avgLatency

    private val _errorRate = MutableLiveData("0.4%")
    val errorRate: LiveData<String> = _errorRate

    private val _dbConnections = MutableLiveData("38/100")
    val dbConnections: LiveData<String> = _dbConnections

    private fun sampleServices() = listOf(
        SystemService("Supabase Auth", ServiceStatus.OPERATIONAL),
        SystemService("Postgrest API", ServiceStatus.OPERATIONAL),
        SystemService("Storage (food images)", ServiceStatus.OPERATIONAL),
        SystemService("ML Inference Service", ServiceStatus.DEGRADED),
        SystemService("Push Notifications (FCM)", ServiceStatus.OPERATIONAL),
        SystemService("Edge Functions", ServiceStatus.OPERATIONAL),
        SystemService("Realtime Subscriptions", ServiceStatus.DEGRADED),
        SystemService("Backup Service", ServiceStatus.OPERATIONAL)
    )
}
