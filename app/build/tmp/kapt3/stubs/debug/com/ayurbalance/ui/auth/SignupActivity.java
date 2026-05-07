package com.ayurbalance.ui.auth;

/**
 * Sign Up (Create Account) Screen.
 *
 * Features:
 *  - 4 input fields with inline validation
 *  - Terms checkbox with clickable links
 *  - Supabase Auth integration via [AuthViewModel]
 *  - Loading/error/success state management
 *  - Premium entry animations
 *
 * Navigation:
 *  Sign Up success → OTP Verification (future) / Dashboard
 *  Login link → Login screen (future)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\fH\u0002J\b\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0002J\b\u0010\u001a\u001a\u00020\fH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001d"}, d2 = {"Lcom/ayurbalance/ui/auth/SignupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivitySignupBinding;", "viewModel", "Lcom/ayurbalance/ui/auth/AuthViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/auth/AuthViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyWindowInsets", "", "clearErrors", "configureEdgeToEdge", "createClickableSpan", "Landroid/text/style/ClickableSpan;", "onClick", "Lkotlin/Function0;", "observeAuthState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupButtons", "setupInputFocusListeners", "setupTermsText", "startEntryAnimations", "validateInputs", "", "app_debug"})
public final class SignupActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivitySignupBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public SignupActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.auth.AuthViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void configureEdgeToEdge() {
    }
    
    private final void applyWindowInsets() {
    }
    
    private final void setupInputFocusListeners() {
    }
    
    private final void setupTermsText() {
    }
    
    private final android.text.style.ClickableSpan createClickableSpan(kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
        return null;
    }
    
    private final void setupButtons() {
    }
    
    private final boolean validateInputs() {
        return false;
    }
    
    private final void clearErrors() {
    }
    
    private final void observeAuthState() {
    }
    
    private final void startEntryAnimations() {
    }
}