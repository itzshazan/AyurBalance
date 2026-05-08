package com.ayurbalance.ui.auth;

/**
 * ViewModel for authentication operations.
 *
 * Manages sign-up state with a sealed [AuthState] class,
 * using Supabase GoTrue for user registration.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ\u0014\u0010\u000e\u001a\u00020\r2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0002J\u0014\u0010\u0012\u001a\u00020\r2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0002J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rJ\u0006\u0010\u0018\u001a\u00020\u000bJ\u0006\u0010\u0019\u001a\u00020\u000bJ\u001e\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rJ\u0016\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u001f"}, d2 = {"Lcom/ayurbalance/ui/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_authState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState;", "authState", "Landroidx/lifecycle/LiveData;", "getAuthState", "()Landroidx/lifecycle/LiveData;", "handleOAuthCallback", "", "fragment", "", "parseError", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "parseOtpError", "resendOtp", "email", "resetState", "signIn", "password", "signInWithGoogle", "signInWithMicrosoft", "signUp", "fullName", "verifyOtp", "token", "AuthState", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ayurbalance.ui.auth.AuthViewModel.AuthState> _authState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ayurbalance.ui.auth.AuthViewModel.AuthState> authState = null;
    
    public AuthViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ayurbalance.ui.auth.AuthViewModel.AuthState> getAuthState() {
        return null;
    }
    
    /**
     * Register a new user with Supabase Auth.
     *
     * @param email User's email address (validated by caller)
     * @param password User's chosen password (min 6 chars)
     * @param fullName User's display name (stored in user metadata)
     */
    public final void signUp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName) {
    }
    
    /**
     * Convert Supabase exceptions into user-friendly messages.
     */
    private final java.lang.String parseError(java.lang.Exception e) {
        return null;
    }
    
    /**
     * Log in an existing user with Supabase Auth.
     *
     * @param email User's email address (validated by caller)
     * @param password User's password
     */
    public final void signIn(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    /**
     * Reset state back to Idle (e.g., after showing error).
     */
    public final void resetState() {
    }
    
    /**
     * Verify a 6-digit OTP sent to the user's email.
     *
     * @param email The email address the OTP was sent to
     * @param token The 6-digit verification code
     */
    public final void verifyOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    /**
     * Resend an OTP to the user's email address.
     *
     * @param email The email address to resend the code to
     */
    public final void resendOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    /**
     * Convert OTP-specific exceptions into user-friendly messages.
     */
    private final java.lang.String parseOtpError(java.lang.Exception e) {
        return null;
    }
    
    /**
     * Initiate Google OAuth sign-in.
     * Opens the browser for Google consent. After completing,
     * the browser redirects back via deep link.
     */
    public final void signInWithGoogle() {
    }
    
    /**
     * Initiate Microsoft (Azure) OAuth sign-in.
     * Opens the browser for Microsoft consent. After completing,
     * the browser redirects back via deep link.
     */
    public final void signInWithMicrosoft() {
    }
    
    /**
     * Handle the callback after an OAuth provider redirect.
     * Parses the URL fragment for access_token and refresh_token,
     * then imports the session into Supabase.
     *
     * @param fragment The URL fragment containing OAuth tokens
     */
    public final void handleOAuthCallback(@org.jetbrains.annotations.NotNull()
    java.lang.String fragment) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState;", "", "()V", "Error", "Idle", "Loading", "Success", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Error;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Idle;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Loading;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Success;", "app_debug"})
    public static abstract class AuthState {
        
        private AuthState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Error;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.ayurbalance.ui.auth.AuthViewModel.AuthState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ayurbalance.ui.auth.AuthViewModel.AuthState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Idle;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState;", "()V", "app_debug"})
        public static final class Idle extends com.ayurbalance.ui.auth.AuthViewModel.AuthState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ayurbalance.ui.auth.AuthViewModel.AuthState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Loading;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState;", "()V", "app_debug"})
        public static final class Loading extends com.ayurbalance.ui.auth.AuthViewModel.AuthState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ayurbalance.ui.auth.AuthViewModel.AuthState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState$Success;", "Lcom/ayurbalance/ui/auth/AuthViewModel$AuthState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Success extends com.ayurbalance.ui.auth.AuthViewModel.AuthState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ayurbalance.ui.auth.AuthViewModel.AuthState.Success copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
}