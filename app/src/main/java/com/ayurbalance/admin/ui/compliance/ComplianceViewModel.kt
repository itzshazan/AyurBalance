package com.ayurbalance.admin.ui.compliance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.admin.data.AdminSupabaseService
import com.ayurbalance.admin.data.DeletionRequest
import kotlinx.coroutines.launch

class ComplianceViewModel : ViewModel() {

    private val _deletions = MutableLiveData<List<DeletionRequest>>()
    val deletions: LiveData<List<DeletionRequest>> = _deletions

    private val _actionResult = MutableLiveData<String?>()
    val actionResult: LiveData<String?> = _actionResult

    init {
        loadDeletions()
    }

    private fun loadDeletions() {
        viewModelScope.launch {
            _deletions.postValue(AdminSupabaseService.fetchPendingDeletions())
        }
    }

    fun processDeletion(userId: String) {
        viewModelScope.launch {
            val ok = AdminSupabaseService.processDeletion(userId)
            _actionResult.postValue(if (ok) "Deletion processed for $userId" else "Action queued — implement cascade delete in Supabase")
            loadDeletions()
        }
    }

    fun processAll() {
        viewModelScope.launch {
            _actionResult.postValue("Processing all deletions…")
            loadDeletions()
        }
    }
}
