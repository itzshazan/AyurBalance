package com.ayurbalance.ui.profile

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
import com.ayurbalance.databinding.ActivityProfileStep3Binding

/**
 * Health Profile — Step 3: Diet & Health
 *
 * Collects:
 *   - Dietary Identity (Vegetarian / Vegan / Non-Veg)
 *   - Health Profile (Multi-select conditions)
 *
 * Navigation:
 *   Continue → Step 4 (Goals)
 *   Back → Step 2 (Lifestyle & Habits)
 */
class ProfileStep3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileStep3Binding
    private val viewModel: ProfileViewModel by viewModels()

    // Map chips to their corresponding condition keys
    private val healthChipMap by lazy {
        mapOf(
            binding.chipDiabetes to "diabetes",
            binding.chipHypertension to "hypertension",
            binding.chipPCOS to "pcos",
            binding.chipThyroid to "thyroid",
            binding.chipDigestive to "digestive_issues",
            binding.chipInsomnia to "insomnia"
        )
    }

    private val healthDotMap by lazy {
        mapOf(
            "diabetes" to binding.dotDiabetes,
            "hypertension" to binding.dotHypertension,
            "pcos" to binding.dotPCOS,
            "thyroid" to binding.dotThyroid,
            "digestive_issues" to binding.dotDigestive,
            "insomnia" to binding.dotInsomnia
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()
        binding = ActivityProfileStep3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        animateProgress()
        setupDietCards()
        setupHealthChips()
        setupButtons()
        startEntryAnimations()

        // Observe state changes
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
    //  Progress Bar Animation (75%)
    // ──────────────────────────────────────────────

    private fun animateProgress() {
        binding.progressFill.post {
            val fullWidth = (binding.progressFill.parent as View).width
            val targetWidth = (fullWidth * 0.75f).toInt() // 75% complete

            // Start from 50% visually if we wanted, but starting from 0 gives a nice sweep
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
    //  Dietary Cards single-selection logic
    // ──────────────────────────────────────────────

    private fun setupDietCards() {
        val cards = mapOf(
            binding.cardVegetarian to "vegetarian",
            binding.cardVegan to "vegan",
            binding.cardNonVeg to "non_veg"
        )

        cards.forEach { (card, type) ->
            card.setOnClickListener {
                viewModel.setDietType(type)
            }
        }
    }

    private fun updateDietUI(selectedType: String) {
        val uiMap = mapOf(
            "vegetarian" to Triple(binding.cardVegetarian, binding.iconVegCheck, binding.iconVeg),
            "vegan" to Triple(binding.cardVegan, binding.iconVeganCheck, binding.iconVegan),
            "non_veg" to Triple(binding.cardNonVeg, binding.iconNonVegCheck, binding.iconNonVeg)
        )

        uiMap.forEach { (type, views) ->
            val (card, check, icon) = views
            if (type == selectedType) {
                card.setBackgroundResource(R.drawable.bg_diet_card_selected)
                check.visibility = View.VISIBLE
                
                // Entrance pop animation for selection
                check.scaleX = 0f
                check.scaleY = 0f
                check.animate().scaleX(1f).scaleY(1f).setDuration(250).setInterpolator(OvershootInterpolator()).start()
                
                card.animate().scaleX(1.02f).scaleY(1.02f).setDuration(150).start()
            } else {
                card.setBackgroundResource(R.drawable.bg_activity_card)
                check.visibility = View.GONE
                card.animate().scaleX(1f).scaleY(1f).setDuration(150).start()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Health Profile Chips multi-selection logic
    // ──────────────────────────────────────────────

    private fun setupHealthChips() {
        healthChipMap.forEach { (chip, condition) ->
            chip.setOnClickListener {
                viewModel.toggleHealthCondition(condition)
                
                // Small click animation
                chip.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction {
                    chip.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                }.start()
            }
        }
    }

    private fun updateHealthChipsUI(selectedConditions: Set<String>) {
        healthChipMap.forEach { (chip, condition) ->
            val dot = healthDotMap[condition]
            
            if (selectedConditions.contains(condition)) {
                chip.setBackgroundResource(R.drawable.bg_health_chip_selected)
                dot?.setBackgroundResource(R.drawable.bg_chip_bullet_selected)
            } else {
                chip.setBackgroundResource(R.drawable.bg_health_chip)
                dot?.setBackgroundResource(R.drawable.bg_chip_bullet)
            }
        }
    }

    // ──────────────────────────────────────────────
    //  ViewModel Observers
    // ──────────────────────────────────────────────
    
    private fun observeViewModel() {
        viewModel.dietType.observe(this) { type ->
            updateDietUI(type)
        }
        
        viewModel.healthConditions.observe(this) { conditions ->
            updateHealthChipsUI(conditions)
        }
    }

    // ──────────────────────────────────────────────
    //  Buttons
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnContinue.setOnClickListener {
            if (viewModel.isStep3Valid.value == true) {
                val intent = android.content.Intent(this, ProfileStep4Activity::class.java)
                startActivity(intent)
                @Suppress("DEPRECATION")
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            } else {
                Toast.makeText(this, "Please select your dietary identity.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPrevious.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }
        
        binding.btnSaveExit.setOnClickListener {
            Toast.makeText(this, "Progress saved!", Toast.LENGTH_SHORT).show()
            finishAffinity() // Or navigate to dashboard if applicable
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
            listOf<View>(ivLogo, tvBrand, btnSaveExit).forEach { v ->
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

            // ③ Diet cards — staggered slide-up
            val cards = listOf<View>(cardVegetarian, cardVegan, cardNonVeg)
            cards.forEachIndexed { i, v ->
                v.alpha = 0f
                v.translationY = 20f
                v.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(450L + i * 100L)
                    .setDuration(400)
                    .setInterpolator(standard).start()
            }
            
            // ④ Health Profile Section — fade up
            listOf<View>(chipDiabetes, chipHypertension, chipPCOS, chipThyroid, chipDigestive, chipInsomnia).forEachIndexed { i, v ->
                v.alpha = 0f
                v.translationY = 20f
                v.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(750L + i * 50L)
                    .setDuration(400)
                    .setInterpolator(standard).start()
            }

            // ⑤ Info card — scale-in
            infoCard.alpha = 0f
            infoCard.scaleX = 0.95f
            infoCard.scaleY = 0.95f
            infoCard.animate()
                .alpha(1f).scaleX(1f).scaleY(1f)
                .setStartDelay(1050).setDuration(500)
                .setInterpolator(overshoot).start()

            // ⑥ Buttons
            btnContinue.alpha = 0f
            btnContinue.animate()
                .alpha(1f).setStartDelay(1250).setDuration(400).start()

            btnPrevious.alpha = 0f
            btnPrevious.animate()
                .alpha(1f).setStartDelay(1350).setDuration(350).start()
        }
    }
}
