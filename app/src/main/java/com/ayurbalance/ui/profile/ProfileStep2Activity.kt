package com.ayurbalance.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityProfileStep2Binding

/**
 * Health Profile — Step 2: Lifestyle & Habits
 *
 * Collects:
 *   - Activity level (Sedentary / Moderate / Very Active)
 *   - Sleep duration (slider, 4–12 hrs)
 *   - Sleep quality (Restless / Average / Deep)
 *
 * Navigation:
 *   Continue → Step 3 (Nutrition)
 *   Back → Step 1 (Foundation)
 */
class ProfileStep2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileStep2Binding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()
        binding = ActivityProfileStep2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        animateProgress()
        setupActivityCards()
        setupSleepSlider()
        setupSleepQuality()
        setupButtons()
        startEntryAnimations()
    }

    // ──────────────────────────────────────────────
    //  Edge-to-Edge
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.profileRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Progress Bar Animation (50%)
    // ──────────────────────────────────────────────

    private fun animateProgress() {
        binding.progressFill.post {
            val fullWidth = (binding.progressFill.parent as View).width
            val targetWidth = (fullWidth * 0.50f).toInt()

            binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply { width = 0 }
            binding.progressFill.requestLayout()

            binding.progressFill.animate()
                .setStartDelay(400)
                .setDuration(900)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply {
                        width = (targetWidth * it.animatedFraction).toInt()
                    }
                    binding.progressFill.requestLayout()
                }
                .start()
        }
    }

    // ──────────────────────────────────────────────
    //  Activity Level Cards
    // ──────────────────────────────────────────────

    private fun setupActivityCards() {
        val cards = mapOf(
            binding.cardSedentary to "sedentary",
            binding.cardModerate to "moderate",
            binding.cardVeryActive to "very_active"
        )

        cards.forEach { (card, level) ->
            card.setOnClickListener {
                viewModel.setActivityLevel(level)
                updateActivityUI(level)
            }
        }

        // Default
        updateActivityUI("sedentary")
    }

    private fun updateActivityUI(selected: String) {
        val map = mapOf(
            "sedentary" to binding.cardSedentary,
            "moderate" to binding.cardModerate,
            "very_active" to binding.cardVeryActive
        )

        map.forEach { (level, card) ->
            if (level == selected) {
                card.setBackgroundResource(R.drawable.bg_activity_card_selected)
                // Subtle scale animation
                card.animate().scaleX(1.02f).scaleY(1.02f).setDuration(150).start()
            } else {
                card.setBackgroundResource(R.drawable.bg_activity_card)
                card.animate().scaleX(1f).scaleY(1f).setDuration(150).start()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Sleep Duration Slider
    // ──────────────────────────────────────────────

    private fun setupSleepSlider() {
        // Range: 4.0 to 12.0 => seekbar 0..120 maps to 4.0..12.0
        // Default: 7.5 hrs => progress = (7.5 - 4.0) * (120/8.0) = 52.5 ≈ 53
        binding.seekSleep.max = 120
        binding.seekSleep.progress = 53

        updateSleepLabel(7.5)

        binding.seekSleep.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val hours = 4.0 + (progress / 120.0) * 8.0
                val rounded = Math.round(hours * 2) / 2.0 // round to 0.5
                viewModel.setSleepHours(rounded)
                updateSleepLabel(rounded)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateSleepLabel(hours: Double) {
        binding.tvSleepHours.text = String.format("%.1f hrs", hours)
    }

    // ──────────────────────────────────────────────
    //  Sleep Quality Emoji Selector
    // ──────────────────────────────────────────────

    private fun setupSleepQuality() {
        val options = mapOf(
            binding.btnRestless to "restless",
            binding.btnAverage to "average",
            binding.btnDeep to "deep"
        )

        options.forEach { (btn, quality) ->
            btn.setOnClickListener {
                viewModel.setSleepQuality(quality)
                updateSleepQualityUI(quality)
            }
        }

        // Default
        updateSleepQualityUI("average")
    }

    private fun updateSleepQualityUI(selected: String) {
        val map = mapOf(
            "restless" to binding.emojiRestlessBg,
            "average" to binding.emojiAverageBg,
            "deep" to binding.emojiDeepBg
        )

        map.forEach { (quality, bgView) ->
            if (quality == selected) {
                bgView.setBackgroundResource(R.drawable.bg_emoji_circle_selected)
                bgView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
            } else {
                bgView.setBackgroundResource(R.drawable.bg_emoji_circle)
                bgView.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Buttons
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnContinue.setOnClickListener {
            val intent = android.content.Intent(this, ProfileStep3Activity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btnSaveForLater.setOnClickListener {
            Toast.makeText(this, "Progress saved!", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnBack.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.0f)

        with(binding) {
            // ① Top bar
            listOf<View>(btnBack, tvBrand, profileFrame).forEach { v ->
                v.alpha = 0f
                v.animate().alpha(1f).setStartDelay(100).setDuration(400).start()
            }

            // ② Step header
            tvStepIndicator.alpha = 0f
            tvStepIndicator.translationY = -10f
            tvStepIndicator.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(200).setDuration(400)
                .setInterpolator(standard).start()

            tvStepTitle.alpha = 0f
            tvStepTitle.translationY = -10f
            tvStepTitle.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(280).setDuration(450)
                .setInterpolator(standard).start()

            // ③ Activity cards — staggered slide-up
            val cards = listOf<View>(cardSedentary, cardModerate, cardVeryActive)
            cards.forEachIndexed { i, v ->
                v.alpha = 0f
                v.translationY = 20f
                v.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(500L + i * 100L)
                    .setDuration(400)
                    .setInterpolator(standard).start()
            }

            // ④ Sleep card — scale-in
            sleepCard.alpha = 0f
            sleepCard.scaleX = 0.95f
            sleepCard.scaleY = 0.95f
            sleepCard.animate()
                .alpha(1f).scaleX(1f).scaleY(1f)
                .setStartDelay(850).setDuration(500)
                .setInterpolator(overshoot).start()

            // ⑤ Info card
            infoCard.alpha = 0f
            infoCard.animate()
                .alpha(1f).setStartDelay(1100).setDuration(400).start()

            // ⑥ Button
            btnContinue.alpha = 0f
            btnContinue.animate()
                .alpha(1f).setStartDelay(1250).setDuration(400).start()

            btnSaveForLater.alpha = 0f
            btnSaveForLater.animate()
                .alpha(1f).setStartDelay(1350).setDuration(350).start()
        }
    }
}
