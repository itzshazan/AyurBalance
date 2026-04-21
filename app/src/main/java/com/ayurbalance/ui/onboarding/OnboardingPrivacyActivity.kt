package com.ayurbalance.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityOnboardingPrivacyBinding

/**
 * Onboarding Privacy Screen — "Your Data is Sacred."
 *
 * Final onboarding screen before authentication establishing trust:
 *   - Hero visual (lock/encryption imagery)
 *   - Privacy messaging
 *   - Trust highlights (HIPAA, encryption, no selling)
 *   - Trust badge with social proof
 *
 * Animation sequence:
 *   Top bar fade → Hero scale-in → Badges pop →
 *   Text slide-up → Trust badge slide → Button fade
 *
 * Navigation:
 *   Get Started → Authentication (Supabase)
 */
class OnboardingPrivacyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingPrivacyBinding

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityOnboardingPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupButtons()
        startEntryAnimations()
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.privacyRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnGetStarted.setOnClickListener {
            navigateToAuth()
        }
    }

    /**
     * Navigates to the Sign Up screen, clearing the
     * onboarding back-stack so the user can't swipe back.
     */
    private fun navigateToAuth() {
        val intent = Intent(this, com.ayurbalance.ui.auth.SignupActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        @Suppress("DEPRECATION")
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.2f)

        with(binding) {

            // ① Top bar — fade-in
            val topViews = listOf<View>(tvAppName, dotsTop)
            topViews.forEach { view ->
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .setStartDelay(100)
                    .setDuration(400)
                    .start()
            }

            // ② Hero card — scale-in from 0.92 with slight overshoot
            heroCard.alpha = 0f
            heroCard.scaleX = 0.92f
            heroCard.scaleY = 0.92f
            heroCard.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(250)
                .setDuration(650)
                .setInterpolator(overshoot)
                .start()

            // ③ Badge circles — pop-in with stagger
            val badges = listOf<View>(badgeLock, badgeShield)
            badges.forEachIndexed { index, view ->
                view.scaleX = 0f
                view.scaleY = 0f
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setStartDelay(600L + index * 150L)
                    .setDuration(500)
                    .setInterpolator(overshoot)
                    .start()
            }

            // ④ Text content — slide-up + fade, staggered
            val textViews = listOf<View>(
                tvHeading1, tvHeading2,
                tvDescription, tvTrustHighlights
            )
            textViews.forEachIndexed { index, view ->
                view.alpha = 0f
                view.translationY = 25f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(800L + index * 120L)
                    .setDuration(500)
                    .setInterpolator(standard)
                    .start()
            }

            // ⑤ Trust badge — slide-up + fade
            trustBadge.alpha = 0f
            trustBadge.translationY = 20f
            trustBadge.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(1250)
                .setDuration(450)
                .setInterpolator(standard)
                .start()

            // ⑥ Button — fade-in with subtle scale
            btnGetStarted.alpha = 0f
            btnGetStarted.scaleX = 0.95f
            btnGetStarted.scaleY = 0.95f
            btnGetStarted.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(1400)
                .setDuration(400)
                .setInterpolator(standard)
                .start()
        }
    }
}
