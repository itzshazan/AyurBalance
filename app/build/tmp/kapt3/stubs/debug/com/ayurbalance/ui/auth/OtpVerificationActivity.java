package com.ayurbalance.ui.auth;

/**
 * OTP Verification Screen — "Verify your spirit."
 *
 * Features:
 *  - 6-digit OTP entry with auto-advance & backspace handling
 *  - Full paste support
 *  - Supabase OTP verification
 *  - Countdown timer for resend (3 minutes)
 *  - Premium entry animations
 *
 * Navigation:
 *  Verify success → Dashboard
 *  Back → Signup screen
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u000bH\u0002J\b\u0010\u0018\u001a\u00020\u000bH\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\b\u0010 \u001a\u00020\u0013H\u0002J\b\u0010!\u001a\u00020\u0013H\u0002J\b\u0010\"\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006$"}, d2 = {"Lcom/ayurbalance/ui/auth/OtpVerificationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityOtpVerificationBinding;", "countdownTimer", "Landroid/os/CountDownTimer;", "otpFields", "", "Landroid/widget/EditText;", "userEmail", "", "viewModel", "Lcom/ayurbalance/ui/auth/AuthViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/auth/AuthViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyWindowInsets", "", "clearOtpFields", "configureEdgeToEdge", "distributePastedCode", "code", "getOtpCode", "observeAuthState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupButtons", "setupOtpInputs", "showOtpError", "startCountdownTimer", "startEntryAnimations", "Companion", "app_debug"})
public final class OtpVerificationActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_EMAIL = "extra_email";
    private static final long COUNTDOWN_MILLIS = 180000L;
    private static final long TICK_INTERVAL = 1000L;
    private com.ayurbalance.databinding.ActivityOtpVerificationBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private java.util.List<? extends android.widget.EditText> otpFields;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countdownTimer;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userEmail = "";
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.auth.OtpVerificationActivity.Companion Companion = null;
    
    public OtpVerificationActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.auth.AuthViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void configureEdgeToEdge() {
    }
    
    private final void applyWindowInsets() {
    }
    
    private final void setupOtpInputs() {
    }
    
    /**
     * Distributes a pasted code across all 6 OTP input fields.
     */
    private final void distributePastedCode(java.lang.String code) {
    }
    
    /**
     * Collects the 6-digit code from all fields.
     */
    private final java.lang.String getOtpCode() {
        return null;
    }
    
    /**
     * Resets all OTP fields and error states.
     */
    private final void clearOtpFields() {
    }
    
    /**
     * Shows error state on all OTP boxes.
     */
    private final void showOtpError() {
    }
    
    private final void setupButtons() {
    }
    
    private final void observeAuthState() {
    }
    
    private final void startCountdownTimer() {
    }
    
    private final void startEntryAnimations() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/ayurbalance/ui/auth/OtpVerificationActivity$Companion;", "", "()V", "COUNTDOWN_MILLIS", "", "EXTRA_EMAIL", "", "TICK_INTERVAL", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}