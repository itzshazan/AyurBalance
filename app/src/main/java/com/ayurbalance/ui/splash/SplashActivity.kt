package com.ayurbalance.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.admin.AdminLoginActivity
import com.ayurbalance.databinding.ActivitySplashBinding
import com.ayurbalance.ui.onboarding.OnboardingActivity

/**
 * AyurBalance Splash Screen
 *
 * Displays the AyurBalance brand identity on launch with a sequenced
 * animation choreography: badge scale-in → app name fade → tagline fade →
 * progress bar fill → status text reveal → tree icon.
 *
 * While animating, the activity initialises app resources and checks
 * authentication state via Supabase to decide the navigation target:
 *   - Existing session → DashboardActivity
 *   - No session       → OnboardingActivity
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        startAnimationSequence()
        setupAdminEntry()

        // Navigate after the animation completes
        handler.postDelayed({ checkAuthAndNavigate() }, 4000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    // ──────────────────────────────────────────────
    //  Edge-to-Edge Window Configuration
    // ──────────────────────────────────────────────

    private fun configureEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.splashRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Only add bottom padding so content doesn't hide behind the nav bar.
            // Top content already has sufficient margin to clear the status bar.
            view.setPadding(0, 0, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Animation Choreography
    // ──────────────────────────────────────────────

    private fun startAnimationSequence() {
        val decelerate = DecelerateInterpolator(1.5f)
        val standard = DecelerateInterpolator()

        with(binding) {

            // ① Badge — fade-in + scale from 0.8 → 1.0
            badgeContainer.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(700)
                .setInterpolator(decelerate)
                .start()

            // ② App name — fade-in + slide up
            tvAppName.translationY = 20f
            tvAppName.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(400)
                .setDuration(600)
                .setInterpolator(standard)
                .start()

            // ③ Tagline — fade-in + slide up
            tvTagline.translationY = 15f
            tvTagline.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(700)
                .setDuration(600)
                .setInterpolator(standard)
                .start()

            // ④ Progress track — fade-in
            progressTrack.animate()
                .alpha(1f)
                .setStartDelay(1100)
                .setDuration(400)
                .start()

            // ⑤ Progress fill — smooth scaleX from 0 → 1 (left-to-right)
            progressFill.postDelayed({
                progressFill.visibility = View.VISIBLE
                progressFill.animate()
                    .scaleX(1f)
                    .setDuration(2200)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }, 1200)

            // ⑥ Status text — fade-in
            tvStatus.animate()
                .alpha(1f)
                .setStartDelay(1200)
                .setDuration(500)
                .setInterpolator(standard)
                .start()

            // ⑦ Subtext — fade-in
            tvSubtext.animate()
                .alpha(1f)
                .setStartDelay(1500)
                .setDuration(500)
                .setInterpolator(standard)
                .start()

            // ⑧ Tree icon — fade-in
            iconTreeBottom.animate()
                .alpha(1f)
                .setStartDelay(1800)
                .setDuration(500)
                .setInterpolator(standard)
                .start()
        }
    }

    // ──────────────────────────────────────────────
    //  Hidden Admin Entry — long-press app logo
    // ──────────────────────────────────────────────

    private fun setupAdminEntry() {
        binding.badgeContainer.setOnLongClickListener {
            handler.removeCallbacksAndMessages(null)
            val intent = Intent(this, AdminLoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
            true
        }
    }

    // ──────────────────────────────────────────────
    //  Auth Check & Navigation
    // ──────────────────────────────────────────────

    private fun checkAuthAndNavigate() {
        // ┌─────────────────────────────────────────┐
        // │  TODO: Replace with Supabase auth check  │
        // │                                          │
        // │  val app = application as AyurBalanceApp  │
        // │  val session = app.supabaseClient         │
        // │      .auth.currentSessionOrNull()         │
        // │                                          │
        // │  if (session != null) {                   │
        // │      navigateTo(DashboardActivity::class) │
        // │  } else {                                │
        // │      navigateTo(OnboardingActivity::class) │
        // │  }                                       │
        // └─────────────────────────────────────────┘

        // Default: navigate to onboarding
        navigateTo(OnboardingActivity::class.java)
    }

    private fun navigateTo(activityClass: Class<*>) {
        val intent = Intent(this, activityClass).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)

        // Smooth fade transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_OPEN,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        } else {
            @Suppress("DEPRECATION")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        finish()
    }
}
