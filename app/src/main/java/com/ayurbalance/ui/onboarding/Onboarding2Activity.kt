package com.ayurbalance.ui.onboarding

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityOnboarding2Binding

/**
 * Onboarding Screen 2 — "Smart Food Logging"
 *
 * Showcases AyurBalance's AI-powered food analysis feature
 * through a phone mockup with detection labels, grid overlay,
 * and dosha analysis results.
 *
 * Animation sequence:
 *   Phone scale-in → AI labels slide-in → Content slide-up →
 *   Buttons fade-in → Labels start scanning pulse
 *
 * Navigation:
 *   Next → Onboarding Screen 3
 *   Skip → Authentication / Dashboard
 */
class Onboarding2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboarding2Binding
    private val scanAnimators = mutableListOf<ObjectAnimator>()

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityOnboarding2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupButtons()
        startEntryAnimations()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up infinite animators to prevent leaks
        scanAnimators.forEach { it.cancel() }
        scanAnimators.clear()
    }

    // ──────────────────────────────────────────────
    //  Edge-to-Edge Window Configuration
    // ──────────────────────────────────────────────

    private fun configureEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true   // Dark icons on beige background
            isAppearanceLightNavigationBars = true
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.onboarding2Root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, Onboarding3Activity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btnSkip.setOnClickListener {
            // TODO: Navigate to Auth or Dashboard
            // val intent = Intent(this, AuthActivity::class.java).apply {
            //     flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // }
            // startActivity(intent)
        }
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.2f)

        with(binding) {

            // ① Phone mockup — scale-in with subtle overshoot
            phoneFrame.alpha = 0f
            phoneFrame.scaleX = 0.88f
            phoneFrame.scaleY = 0.88f
            phoneFrame.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(650)
                .setInterpolator(overshoot)
                .start()

            // ② AI detection labels — slide-in from left
            labelAvocado.alpha = 0f
            labelAvocado.translationX = -24f
            labelAvocado.animate()
                .alpha(1f)
                .translationX(0f)
                .setStartDelay(400)
                .setDuration(500)
                .setInterpolator(standard)
                .start()

            labelQuinoa.alpha = 0f
            labelQuinoa.translationX = -24f
            labelQuinoa.animate()
                .alpha(1f)
                .translationX(0f)
                .setStartDelay(550)
                .setDuration(500)
                .setInterpolator(standard)
                .start()

            // ③ Content below phone — sequential slide-up + fade
            val contentViews = listOf<View>(
                dotIndicators, tvVisionTag, tvHeadingLine1, tvHeadingLine2
            )
            contentViews.forEachIndexed { index, view ->
                view.alpha = 0f
                view.translationY = 25f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(600L + index * 100L)
                    .setDuration(450)
                    .setInterpolator(standard)
                    .start()
            }

            // ④ Buttons — fade-in
            btnNext.alpha = 0f
            btnNext.animate()
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(400)
                .start()

            btnSkip.alpha = 0f
            btnSkip.animate()
                .alpha(1f)
                .setStartDelay(1100)
                .setDuration(400)
                .start()
        }

        // ⑤ Start scanning pulse on AI labels after entry animations complete
        startScanningPulse()
    }

    // ──────────────────────────────────────────────
    //  AI Scanning Pulse Animation
    // ──────────────────────────────────────────────

    /**
     * Creates an infinite, subtle alpha pulse on both AI detection labels,
     * staggered so they pulse alternately — gives the feel of live AI
     * scanning/detection happening on the food items.
     */
    private fun startScanningPulse() {
        val smooth = AccelerateDecelerateInterpolator()

        // AVOCADO label — pulse alpha: 1.0 → 0.55 → 1.0
        val avocadoPulse = ObjectAnimator.ofFloat(
            binding.labelAvocado, "alpha", 1f, 0.55f, 1f
        ).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            interpolator = smooth
            startDelay = 1500  // Start after entry animations settle
        }
        avocadoPulse.start()
        scanAnimators.add(avocadoPulse)

        // QUINOA label — pulse offset by 1s so they alternate
        val quinoaPulse = ObjectAnimator.ofFloat(
            binding.labelQuinoa, "alpha", 1f, 0.55f, 1f
        ).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            interpolator = smooth
            startDelay = 2500  // Offset from avocado for alternating effect
        }
        quinoaPulse.start()
        scanAnimators.add(quinoaPulse)
    }
}
