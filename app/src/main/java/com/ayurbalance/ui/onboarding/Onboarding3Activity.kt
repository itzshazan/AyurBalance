package com.ayurbalance.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Animatable
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
import com.ayurbalance.databinding.ActivityOnboarding3Binding

/**
 * Onboarding Screen 3 — "Track Your Journey"
 *
 * Final onboarding screen showcasing AyurBalance's health tracking
 * and analytics features through dashboard-style preview cards:
 *   - Current State (dosha visualization)
 *   - Energy & Digestion metrics
 *   - Vitality Trends graph (animated line draw)
 *
 * Animation sequence:
 *   Header slide-down → Cards stagger-in → Graph fade + line draw →
 *   Buttons scale-in
 *
 * Navigation:
 *   Complete Setup → Authentication (Supabase)
 *   Skip for now  → Authentication (Supabase)
 */
class Onboarding3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboarding3Binding

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityOnboarding3Binding.inflate(layoutInflater)
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
            isAppearanceLightStatusBars = true   // Dark icons on beige background
            isAppearanceLightNavigationBars = true
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.onboarding3Root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnComplete.setOnClickListener {
            navigateToPrivacy()
        }

        binding.btnSkipNow.setOnClickListener {
            navigateToPrivacy()
        }
    }

    /**
     * Navigates to the Privacy/Trust screen — the final onboarding
     * step before authentication.
     */
    private fun navigateToPrivacy() {
        val intent = Intent(this, OnboardingPrivacyActivity::class.java)
        startActivity(intent)
        @Suppress("DEPRECATION")
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.1f)
        val doshaOvershoot = OvershootInterpolator(1.4f)

        with(binding) {

            // ① Header content — fade-in + slide-down from above
            val headerViews = listOf<View>(tvStepPill, tvHeading, tvDescription)
            headerViews.forEachIndexed { index, view ->
                view.alpha = 0f
                view.translationY = -20f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(100L + index * 120L)
                    .setDuration(500)
                    .setInterpolator(standard)
                    .start()
            }

            // ② Current State card — scale-in with slight overshoot
            cardCurrentState.alpha = 0f
            cardCurrentState.scaleX = 0.93f
            cardCurrentState.scaleY = 0.93f
            cardCurrentState.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(450)
                .setDuration(600)
                .setInterpolator(overshoot)
                .start()

            // ②b Dosha mounds — grow from bottom with stagger
            val doshaViews = listOf(tvDoshaPit, tvDoshaVata, tvDoshaKap)
            doshaViews.forEachIndexed { index, view ->
                view.scaleY = 0f
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .scaleY(1f)
                    .setStartDelay(700L + index * 150L)
                    .setDuration(600)
                    .setInterpolator(doshaOvershoot)
                    .start()
            }

            // ③ Metric cards — staggered slide-up + fade
            val metricCards = listOf<View>(cardEnergy, cardDigestion)
            metricCards.forEachIndexed { index, view ->
                view.alpha = 0f
                view.translationY = 30f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(750L + index * 150L)
                    .setDuration(500)
                    .setInterpolator(standard)
                    .start()
            }

            // ③b Metric values — scale-in pop effect
            val metricValues = listOf(tvEnergyValue, tvDigestionValue)
            metricValues.forEachIndexed { index, view ->
                view.scaleX = 0f
                view.scaleY = 0f
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setStartDelay(950L + index * 150L)
                    .setDuration(500)
                    .setInterpolator(overshoot)
                    .start()
            }

            // ③c Progress bars — fill from left to target width
            progressEnergyFill.scaleX = 0f
            progressEnergyFill.animate()
                .scaleX(0.60f)
                .setStartDelay(1100)
                .setDuration(700)
                .setInterpolator(standard)
                .start()

            progressDigestionFill.scaleX = 0f
            progressDigestionFill.animate()
                .scaleX(0.84f)
                .setStartDelay(1250)
                .setDuration(700)
                .setInterpolator(standard)
                .start()

            // ④ Graph card — fade-in + subtle slide-up
            cardGraph.alpha = 0f
            cardGraph.translationY = 25f
            cardGraph.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(1050)
                .setDuration(550)
                .setInterpolator(standard)
                .start()

            // ⑤ Graph line-draw animation (AVD)
            // Triggered after graph card is mostly visible
            ivGraph.postDelayed({
                (ivGraph.drawable as? Animatable)?.start()
            }, 1300)

            // ⑥ Bottom section — sequential fade-in
            val bottomViews = listOf<View>(btnComplete, btnSkipNow, dotIndicators)
            bottomViews.forEachIndexed { index, view ->
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .setStartDelay(1300L + index * 100L)
                    .setDuration(400)
                    .start()
            }
        }
    }
}
