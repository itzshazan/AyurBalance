package com.ayurbalance.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.AyurBalanceApp
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Azure
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * ViewModel for authentication operations.
 *
 * Manages sign-up state with a sealed [AuthState] class,
 * using Supabase GoTrue for user registration.
 */
class AuthViewModel : ViewModel() {

    // ──────────────────────────────────────────────
    //  State
    // ──────────────────────────────────────────────

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val message: String) : AuthState()
        data class Error(val message: String) : AuthState()
    }

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState

    // ──────────────────────────────────────────────
    //  Sign Up
    // ──────────────────────────────────────────────

    /**
     * Register a new user with Supabase Auth.
     *
     * @param email User's email address (validated by caller)
     * @param password User's chosen password (min 6 chars)
     * @param fullName User's display name (stored in user metadata)
     */
    fun signUp(email: String, password: String, fullName: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient

                supabase.auth.signUpWith(Email) {
                    this.email = email
                    this.password = password
                    data = buildJsonObject {
                        put("full_name", fullName)
                    }
                }

                _authState.postValue(
                    AuthState.Success("Account created! Check your email to verify.")
                )
            } catch (e: Exception) {
                val userMessage = parseError(e)
                _authState.postValue(AuthState.Error(userMessage))
            }
        }
    }

    /**
     * Convert Supabase exceptions into user-friendly messages.
     */
    private fun parseError(e: Exception): String {
        val msg = e.message?.lowercase() ?: return "Something went wrong. Please try again."
        return when {
            "already registered" in msg || "already been registered" in msg ->
                "This email is already registered. Try logging in."
            "password" in msg && ("weak" in msg || "short" in msg || "length" in msg) ->
                "Password is too weak. Use at least 6 characters."
            "invalid" in msg && ("login" in msg || "credentials" in msg || "password" in msg) ->
                "Invalid email or password."
            "invalid" in msg && "email" in msg ->
                "Please enter a valid email address."
            "user not found" in msg || "no user" in msg ->
                "No account found with this email."
            "email not confirmed" in msg ->
                "Please verify your email before logging in."
            "rate" in msg || "limit" in msg ->
                "Too many attempts. Please wait a moment."
            "network" in msg || "connect" in msg || "timeout" in msg ->
                "Network error. Check your internet connection."
            else -> e.message ?: "Something went wrong. Please try again."
        }
    }

    // ──────────────────────────────────────────────
    //  Sign In
    // ──────────────────────────────────────────────

    /**
     * Log in an existing user with Supabase Auth.
     *
     * @param email User's email address (validated by caller)
     * @param password User's password
     */
    fun signIn(email: String, password: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient

                supabase.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }

                _authState.postValue(
                    AuthState.Success("Welcome back!")
                )
            } catch (e: Exception) {
                val userMessage = parseError(e)
                _authState.postValue(AuthState.Error(userMessage))
            }
        }
    }

    /**
     * Reset state back to Idle (e.g., after showing error).
     */
    fun resetState() {
        _authState.value = AuthState.Idle
    }

    // ──────────────────────────────────────────────
    //  OTP Verification
    // ──────────────────────────────────────────────

    /**
     * Verify a 6-digit OTP sent to the user's email.
     *
     * @param email The email address the OTP was sent to
     * @param token The 6-digit verification code
     */
    fun verifyOtp(email: String, token: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient

                supabase.auth.verifyEmailOtp(
                    type = io.github.jan.supabase.gotrue.OtpType.Email.SIGNUP,
                    email = email,
                    token = token
                )

                _authState.postValue(
                    AuthState.Success("Email verified! Welcome to AyurBalance.")
                )
            } catch (e: Exception) {
                val userMessage = parseOtpError(e)
                _authState.postValue(AuthState.Error(userMessage))
            }
        }
    }

    /**
     * Resend an OTP to the user's email address.
     *
     * @param email The email address to resend the code to
     */
    fun resendOtp(email: String) {
        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient
                supabase.auth.resendEmail(
                    type = io.github.jan.supabase.gotrue.OtpType.Email.SIGNUP,
                    email = email
                )
            } catch (_: Exception) {
                // Silently fail — UI already shows "code sent" message
            }
        }
    }

    /**
     * Convert OTP-specific exceptions into user-friendly messages.
     */
    private fun parseOtpError(e: Exception): String {
        val msg = e.message?.lowercase() ?: return "Verification failed. Please try again."
        return when {
            "invalid" in msg && "otp" in msg -> "Invalid verification code."
            "expired" in msg || "expire" in msg -> "Code has expired. Please request a new one."
            "token" in msg -> "Invalid verification code."
            "rate" in msg || "limit" in msg -> "Too many attempts. Please wait a moment."
            "network" in msg || "connect" in msg || "timeout" in msg ->
                "Network error. Check your internet connection."
            else -> e.message ?: "Verification failed. Please try again."
        }
    }

    // ──────────────────────────────────────────────
    //  OAuth — Google & Microsoft
    // ──────────────────────────────────────────────

    /**
     * Initiate Google OAuth sign-in.
     * Opens the browser for Google consent. After completing,
     * the browser redirects back via deep link.
     */
    fun signInWithGoogle() {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient
                supabase.auth.signInWith(Google)
                _authState.postValue(AuthState.Success("Welcome via Google!"))
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(
                    parseError(e)
                ))
            }
        }
    }

    /**
     * Initiate Microsoft (Azure) OAuth sign-in.
     * Opens the browser for Microsoft consent. After completing,
     * the browser redirects back via deep link.
     */
    fun signInWithMicrosoft() {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient
                supabase.auth.signInWith(Azure)
                _authState.postValue(AuthState.Success("Welcome via Microsoft!"))
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(
                    parseError(e)
                ))
            }
        }
    }

    /**
     * Handle the callback after an OAuth provider redirect.
     * Parses the URL fragment for access_token and refresh_token,
     * then imports the session into Supabase.
     *
     * @param fragment The URL fragment containing OAuth tokens
     */
    fun handleOAuthCallback(fragment: String = "") {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val supabase = AyurBalanceApp.supabaseClient

                if (fragment.isNotEmpty()) {
                    // Parse tokens from fragment: access_token=xxx&refresh_token=xxx&...
                    val params = fragment.split("&").associate {
                        val (key, value) = it.split("=", limit = 2)
                        key to value
                    }

                    val accessToken = params["access_token"]
                    val refreshToken = params["refresh_token"]

                    if (accessToken != null) {
                        // Import the session using the tokens
                        supabase.auth.importAuthToken(accessToken, refreshToken ?: "")
                        _authState.postValue(AuthState.Success("Welcome to AyurBalance!"))
                        return@launch
                    }
                }

                // Fallback: check if session already exists
                val session = supabase.auth.currentSessionOrNull()
                if (session != null) {
                    _authState.postValue(AuthState.Success("Welcome to AyurBalance!"))
                } else {
                    _authState.postValue(AuthState.Error("Sign-in was cancelled."))
                }
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(parseError(e)))
            }
        }
    }
}
