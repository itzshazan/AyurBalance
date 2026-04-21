package com.ayurbalance.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityOnboardingBinding

/**
 * Onboarding Screen 1 — "Your Personal Dosha Guide"
 *
 * First screen in the onboarding flow that introduces users to
 * AyurBalance's core value proposition: personalised Ayurvedic
 * diet guidance based on their unique body constitution (Prakriti).
 *
 * Animation sequence:
 *   Hero fade-in → Brand text → Floating card slide-in →
 *   Content slide-up → Buttons scale-in
 *
 * Navigation:
 *   Next → Onboarding Screen 2
 *   Skip → Authentication / Dashboard
 */
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        styleDescriptionText()
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
            isAppearanceLightStatusBars = false  // Light icons over dark hero image
            isAppearanceLightNavigationBars = true
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.onboardingRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Bottom padding for navigation bar
            view.setPadding(0, 0, 0, insets.bottom)

            // Shift brand text below status bar
            val brandParams = binding.tvBrandName.layoutParams
                as androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            brandParams.topMargin = insets.top + (16 * resources.displayMetrics.density).toInt()
            binding.tvBrandName.layoutParams = brandParams

            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Styled Text
    // ──────────────────────────────────────────────

    private fun styleDescriptionText() {
        val fullText = getString(R.string.onboarding1_description)
        val highlight = "(Prakriti)"
        val start = fullText.indexOf(highlight)

        if (start >= 0) {
            val spannable = SpannableString(fullText)
            val end = start + highlight.length

            // Bold + golden-brown colour for "(Prakriti)"
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(this, R.color.text_status_brown)),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            binding.tvDescription.text = spannable
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, Onboarding2Activity::class.java)
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

        with(binding) {

            // ① Hero image — fade-in
            heroImage.alpha = 0f
            heroImage.animate()
                .alpha(1f)
                .setDuration(600)
                .setInterpolator(standard)
                .start()

            // ② Brand name — fade-in (slightly transparent final state)
            tvBrandName.alpha = 0f
            tvBrandName.animate()
                .alpha(0.85f)
                .setStartDelay(200)
                .setDuration(500)
                .start()

            // ③ Floating card — slide-in from right + fade
            floatingCard.alpha = 0f
            floatingCard.translationX = 40f
            floatingCard.animate()
                .alpha(1f)
                .translationX(0f)
                .setStartDelay(400)
                .setDuration(500)
                .setInterpolator(standard)
                .start()

            // ④ Bottom content — sequential slide-up + fade
            val contentViews = listOf<View>(
                dotIndicators, tvHeadingLine1, tvHeadingLine2, tvDescription
            )
            contentViews.forEachIndexed { index, view ->
                view.alpha = 0f
                view.translationY = 30f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(500L + index * 100L)
                    .setDuration(500)
                    .setInterpolator(standard)
                    .start()
            }

            // ⑤ Next button — scale-in + fade
            btnNext.alpha = 0f
            btnNext.scaleX = 0.95f
            btnNext.scaleY = 0.95f
            btnNext.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(900)
                .setDuration(500)
                .setInterpolator(standard)
                .start()

            // ⑥ Skip button — fade-in
            btnSkip.alpha = 0f
            btnSkip.animate()
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(400)
                .start()
        }
    }
}
