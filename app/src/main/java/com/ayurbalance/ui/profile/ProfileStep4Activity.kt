package com.ayurbalance.ui.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityProfileStep4Binding

/**
 * Health Profile — Step 4: Goals & Focus
 *
 * Collects:
 *   - Primary dietary/health goal
 *
 * Navigation:
 *   Continue → Submits to Supabase -> Next flow (Prakriti Assessment)
 *   Back → Step 3 (Diet & Health)
 */
class ProfileStep4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileStep4Binding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()
        binding = ActivityProfileStep4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        animateProgress()
        setupGoalCards()
        setupButtons()
        startEntryAnimations()

        observeViewModel()
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
    //  Animations
    // ──────────────────────────────────────────────

    private fun animateProgress() {
        binding.progressFill.post {
            val fullWidth = (binding.progressFill.parent as View).width

            binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply { width = 0 }
            binding.progressFill.requestLayout()

            binding.progressFill.animate()
                .setStartDelay(400)
                .setDuration(900)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply {
                        width = (fullWidth * it.animatedFraction).toInt()
                    }
                    binding.progressFill.requestLayout()
                }
                .start()
        }
    }

    // ──────────────────────────────────────────────
    //  Goal Selection
    // ──────────────────────────────────────────────

    private fun setupGoalCards() {
        val goals = mapOf(
            binding.cardWeightLoss to "weight_loss",
            binding.cardFitness to "fitness",
            binding.cardStress to "stress_balance",
            binding.cardWellness to "general_wellness"
        )

        goals.forEach { (card, goalId) ->
            card.setOnClickListener {
                viewModel.setGoal(goalId)
            }
        }
    }

    private fun updateGoalUI(selectedGoal: String) {
        val cardsMap = mapOf(
            "weight_loss" to Pair(binding.cardWeightLoss, binding.radioWeightLoss),
            "fitness" to Pair(binding.cardFitness, binding.radioFitness),
            "stress_balance" to Pair(binding.cardStress, binding.radioStress),
            "general_wellness" to Pair(binding.cardWellness, binding.radioWellness)
        )

        cardsMap.forEach { (goalId, views) ->
            val (card, radio) = views
            if (goalId == selectedGoal) {
                card.setBackgroundResource(R.drawable.bg_activity_card_selected)
                radio.setImageResource(R.drawable.ic_radio_selected)
                
                // Pop animation
                radio.scaleX = 0.5f
                radio.scaleY = 0.5f
                radio.animate().scaleX(1f).scaleY(1f).setDuration(250)
                    .setInterpolator(OvershootInterpolator()).start()
                    
                card.animate().scaleX(1.02f).scaleY(1.02f).setDuration(150).start()
            } else {
                card.setBackgroundResource(R.drawable.bg_activity_card)
                radio.setImageResource(R.drawable.ic_radio_unselected)
                card.animate().scaleX(1f).scaleY(1f).setDuration(150).start()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  ViewModel & Submission
    // ──────────────────────────────────────────────
    
    private fun observeViewModel() {
        viewModel.goal.observe(this) { goal ->
            updateGoalUI(goal)
        }

        viewModel.submissionState.observe(this) { state ->
            when (state) {
                is ProfileViewModel.SubmissionState.Loading -> {
                    binding.btnFinish.text = ""
                    binding.progressSubmitting.visibility = View.VISIBLE
                    binding.btnFinish.isEnabled = false
                    setCardsEnabled(false)
                }
                is ProfileViewModel.SubmissionState.Success -> {
                    // Navigate to next module (Prakriti Assessment)
                    binding.progressSubmitting.visibility = View.GONE
                    binding.btnFinish.text = getString(R.string.btn_finish_setup)
                    Toast.makeText(this, "Profile Saved Successfully!", Toast.LENGTH_SHORT).show()
                    
                    // TODO: Intent to Prakriti Assessment
                    finish() 
                }
                is ProfileViewModel.SubmissionState.Error -> {
                    binding.progressSubmitting.visibility = View.GONE
                    binding.btnFinish.text = getString(R.string.btn_finish_setup)
                    binding.btnFinish.isEnabled = true
                    setCardsEnabled(true)
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
                else -> {
                    binding.progressSubmitting.visibility = View.GONE
                    binding.btnFinish.text = getString(R.string.btn_finish_setup)
                    binding.btnFinish.isEnabled = true
                    setCardsEnabled(true)
                }
            }
        }
    }
    
    private fun setCardsEnabled(enabled: Boolean) {
        binding.cardWeightLoss.isEnabled = enabled
        binding.cardFitness.isEnabled = enabled
        binding.cardStress.isEnabled = enabled
        binding.cardWellness.isEnabled = enabled
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        binding.btnFinish.setOnClickListener {
            if (viewModel.isStep4Valid.value == true) {
                viewModel.submitProfile()
            } else {
                Toast.makeText(this, "Please select your primary focus.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.0f)

        with(binding) {
            // Top bar
            listOf<View>(btnBack, tvBrand, profileFrame).forEach { v ->
                v.alpha = 0f
                v.animate().alpha(1f).setStartDelay(100).setDuration(400).start()
            }

            // Step header
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

            // Goal cards — staggered slide-up
            val cards = listOf<View>(cardWeightLoss, cardFitness, cardStress, cardWellness)
            cards.forEachIndexed { i, v ->
                v.alpha = 0f
                v.translationY = 20f
                v.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(450L + i * 100L)
                    .setDuration(400)
                    .setInterpolator(standard).start()
            }

            // Info card — scale-in
            infoCard.alpha = 0f
            infoCard.scaleX = 0.95f
            infoCard.scaleY = 0.95f
            infoCard.animate()
                .alpha(1f).scaleX(1f).scaleY(1f)
                .setStartDelay(950).setDuration(500)
                .setInterpolator(overshoot).start()

            // Buttons
            btnFinish.alpha = 0f
            btnFinish.animate()
                .alpha(1f).setStartDelay(1150).setDuration(400).start()
                
            tvNextPrakriti.alpha = 0f
            tvNextPrakriti.animate()
                .alpha(1f).setStartDelay(1250).setDuration(400).start()
                
            btnNeedHelp.alpha = 0f
            btnNeedHelp.animate()
                .alpha(1f).setStartDelay(1350).setDuration(400).start()
        }
    }
}
