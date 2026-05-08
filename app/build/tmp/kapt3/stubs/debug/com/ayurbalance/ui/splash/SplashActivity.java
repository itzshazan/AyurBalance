package com.ayurbalance.ui.splash;

/**
 * AyurBalance Splash Screen
 *
 * Displays the AyurBalance brand identity on launch with a sequenced
 * animation choreography: badge scale-in → app name fade → tagline fade →
 * progress bar fill → status text reveal → tree icon.
 *
 * While animating, the activity initialises app resources and checks
 * authentication state via Supabase to decide the navigation target:
 *  - Existing session → DashboardActivity
 *  - No session       → OnboardingActivity
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\u0014\u0010\u000b\u001a\u00020\b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\u0012\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\bH\u0014J\b\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010\u0013\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/ayurbalance/ui/splash/SplashActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivitySplashBinding;", "handler", "Landroid/os/Handler;", "applyWindowInsets", "", "checkAuthAndNavigate", "configureEdgeToEdge", "navigateTo", "activityClass", "Ljava/lang/Class;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupAdminEntry", "startAnimationSequence", "app_debug"})
public final class SplashActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivitySplashBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    
    public SplashActivity() {
        super();
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
    
    private final void startAnimationSequence() {
    }
    
    private final void setupAdminEntry() {
    }
    
    private final void checkAuthAndNavigate() {
    }
    
    private final void navigateTo(java.lang.Class<?> activityClass) {
    }
}