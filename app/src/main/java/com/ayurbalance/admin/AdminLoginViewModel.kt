package com.ayurbalance.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.AyurBalanceApp
import com.ayurbalance.admin.data.AdminSupabaseService
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class AdminLoginViewModel : ViewModel() {

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }

    private val _loginState = MutableLiveData<LoginState>(LoginState.Idle)
    val loginState: LiveData<LoginState> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient
                supabase.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }

                val role = AdminSupabaseService.verifyAdminRole(email)
                if (role != null && role in listOf("super_admin", "moderator", "admin")) {
                    _loginState.postValue(LoginState.Success)
                } else {
                    supabase.auth.signOut()
                    _loginState.postValue(LoginState.Error("Access denied. You do not have admin privileges."))
                }
            } catch (e: Exception) {
                val msg = parseError(e)
                _loginState.postValue(LoginState.Error(msg))
            }
        }
    }

    private fun parseError(e: Exception): String {
        val msg = e.message?.lowercase() ?: return "Login failed. Please try again."
        return when {
            "invalid" in msg && ("credentials" in msg || "password" in msg) -> "Invalid email or password."
            "not found" in msg -> "No account found with this email."
            "network" in msg || "timeout" in msg -> "Network error. Check your connection."
            else -> e.message ?: "Login failed. Please try again."
        }
    }
}
