package com.ayurbalance.admin.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.admin.data.AdminSupabaseService
import com.ayurbalance.admin.data.UserRecord
import kotlinx.coroutines.launch

class UserManagementViewModel : ViewModel() {

    private val _users = MutableLiveData<List<UserRecord>>()
    val users: LiveData<List<UserRecord>> = _users

    private val allUsers = mutableListOf<UserRecord>()

    private val _actionResult = MutableLiveData<String?>()
    val actionResult: LiveData<String?> = _actionResult

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            val result = AdminSupabaseService.fetchUsers()
            allUsers.clear()
            allUsers.addAll(result)
            _users.postValue(result)
        }
    }

    fun filterUsers(query: String, status: String = "all") {
        val filtered = allUsers.filter { user ->
            val matchesQuery = query.isEmpty() ||
                user.fullName.contains(query, ignoreCase = true) ||
                user.email.contains(query, ignoreCase = true) ||
                user.id.contains(query, ignoreCase = true)
            val matchesStatus = status == "all" || user.status == status
            matchesQuery && matchesStatus
        }
        _users.value = filtered
    }

    fun suspendUser(userId: String) {
        viewModelScope.launch {
            val success = AdminSupabaseService.updateUserStatus(userId, "inactive")
            _actionResult.postValue(if (success) "User suspended" else "Action failed")
            if (success) loadUsers()
        }
    }
}
