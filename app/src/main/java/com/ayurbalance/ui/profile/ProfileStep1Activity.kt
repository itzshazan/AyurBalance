package com.ayurbalance.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityProfileStep1Binding

/**
 * Health Profile — Step 1: Foundation
 *
 * Collects:
 *   - Age
 *   - Biological gender
 *   - Height (CM/FT)
 *   - Weight (KG/LB)
 *
 * Navigation:
 *   Continue → Step 2 (Lifestyle)
 *   Back → Previous screen
 */
class ProfileStep1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileStep1Binding
    private val viewModel: ProfileViewModel by viewModels()

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityProfileStep1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupStepIndicator()
        setupDescription()
        setupInputListeners()
        setupGenderSelector()
        setupUnitToggles()
        setupButtons()
        observeValidation()
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
    //  Step Indicator + Progress
    // ──────────────────────────────────────────────

    private fun setupStepIndicator() {
        binding.tvStepIndicator.text = String.format("STEP 01 OF 04")
        binding.tvPercent.text = getString(R.string.step_1_percent)

        // Animate progress bar to 25%
        binding.progressFill.post {
            val fullWidth = (binding.progressFill.parent as View).width
            val targetWidth = (fullWidth * 0.25f).toInt()

            binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply {
                width = 0
            }
            binding.progressFill.requestLayout()

            binding.progressFill.animate()
                .setStartDelay(500)
                .setDuration(800)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    val progress = it.animatedFraction
                    binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply {
                        width = (targetWidth * progress).toInt()
                    }
                    binding.progressFill.requestLayout()
                }
                .start()
        }
    }

    // ──────────────────────────────────────────────
    //  Description with styled Prakriti
    // ──────────────────────────────────────────────

    private fun setupDescription() {
        val html = "To begin your Ayurvedic journey, we need to understand your unique physical vessel. These metrics help us calculate your baseline <b><i>Prakriti</i></b>."
        @Suppress("DEPRECATION")
        binding.tvDescription.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    }

    // ──────────────────────────────────────────────
    //  Input Listeners — sync to ViewModel
    // ──────────────────────────────────────────────

    private fun setupInputListeners() {
        // Focus state visuals
        val inputs = listOf(binding.etAge, binding.etHeight, binding.etWeight)
        inputs.forEach { editText ->
            editText.setOnFocusChangeListener { v, hasFocus ->
                v.setBackgroundResource(
                    if (hasFocus) R.drawable.bg_profile_input_focused
                    else R.drawable.bg_profile_input
                )
            }
        }

        // Age
        binding.etAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setAge(s?.toString()?.toIntOrNull())
            }
        })

        // Height
        binding.etHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setHeight(s?.toString()?.toDoubleOrNull())
            }
        })

        // Weight
        binding.etWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setWeight(s?.toString()?.toDoubleOrNull())
            }
        })
    }

    // ──────────────────────────────────────────────
    //  Gender Selector — pill toggle
    // ──────────────────────────────────────────────

    private fun setupGenderSelector() {
        val buttons = listOf(
            binding.btnFemale to "female",
            binding.btnMale to "male",
            binding.btnOther to "other"
        )

        buttons.forEach { (button, gender) ->
            button.setOnClickListener {
                viewModel.setGender(gender)
                updateGenderUI(gender)
            }
        }

        // Default selection
        updateGenderUI("female")
    }

    private fun updateGenderUI(selected: String) {
        val map = mapOf(
            "female" to binding.btnFemale,
            "male" to binding.btnMale,
            "other" to binding.btnOther
        )

        map.forEach { (gender, button) ->
            if (gender == selected) {
                button.setBackgroundResource(R.drawable.bg_gender_selected)
                button.setTextColor(getColor(R.color.white))
            } else {
                button.setBackgroundResource(R.drawable.bg_gender_unselected)
                button.setTextColor(getColor(R.color.text_body))
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Unit Toggles — CM/FT and KG/LB
    // ──────────────────────────────────────────────

    private fun setupUnitToggles() {
        // Height: CM / FT
        binding.btnCm.setOnClickListener {
            viewModel.setHeightUnit("cm")
            updateUnitToggle(binding.btnCm, binding.btnFt)
        }
        binding.btnFt.setOnClickListener {
            viewModel.setHeightUnit("ft")
            updateUnitToggle(binding.btnFt, binding.btnCm)
        }

        // Weight: KG / LB
        binding.btnKg.setOnClickListener {
            viewModel.setWeightUnit("kg")
            updateUnitToggle(binding.btnKg, binding.btnLb)
        }
        binding.btnLb.setOnClickListener {
            viewModel.setWeightUnit("lb")
            updateUnitToggle(binding.btnLb, binding.btnKg)
        }
    }

    private fun updateUnitToggle(selected: TextView, unselected: TextView) {
        selected.setBackgroundResource(R.drawable.bg_unit_selected)
        selected.setTextColor(getColor(R.color.white))
        unselected.setBackgroundResource(R.drawable.bg_unit_unselected)
        unselected.setTextColor(getColor(R.color.text_body))
    }

    // ──────────────────────────────────────────────
    //  Buttons
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnContinue.setOnClickListener {
            if (viewModel.isStep1Valid.value == true) {
                val intent = android.content.Intent(this, ProfileStep2Activity::class.java)
                startActivity(intent)
                @Suppress("DEPRECATION")
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }
    }

    // ──────────────────────────────────────────────
    //  Validation Observer
    // ──────────────────────────────────────────────

    private fun observeValidation() {
        viewModel.isStep1Valid.observe(this) { valid ->
            binding.btnContinue.alpha = if (valid) 1f else 0.5f
            binding.btnContinue.isEnabled = valid
        }

        // Start disabled
        binding.btnContinue.alpha = 0.5f
        binding.btnContinue.isEnabled = false
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.0f)

        with(binding) {

            // ① Top bar — fade-in
            listOf<View>(btnBack, tvBrand, profileFrame).forEach { v ->
                v.alpha = 0f
                v.animate().alpha(1f).setStartDelay(100).setDuration(400).start()
            }

            // ② Step indicator + title + progress — slide-down
            listOf<View>(tvStepIndicator).forEach { v ->
                v.alpha = 0f
                v.translationY = -10f
                v.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(200).setDuration(400)
                    .setInterpolator(standard).start()
            }

            tvStepTitle.alpha = 0f
            tvStepTitle.translationY = -10f
            tvStepTitle.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(280).setDuration(450)
                .setInterpolator(standard).start()

            // ③ Description — fade-in
            tvDescription.alpha = 0f
            tvDescription.animate()
                .alpha(1f).setStartDelay(400).setDuration(400).start()

            // ④ Form fields — staggered slide-up
            val formElements = listOf<View>(
                etAge, genderRow, etHeight, etWeight
            )
            formElements.forEachIndexed { i, v ->
                v.alpha = 0f
                v.translationY = 20f
                v.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(550L + i * 80L)
                    .setDuration(400)
                    .setInterpolator(standard)
                    .start()
            }

            // ⑤ Info card — scale-in
            infoCard.alpha = 0f
            infoCard.scaleX = 0.95f
            infoCard.scaleY = 0.95f
            infoCard.animate()
                .alpha(1f).scaleX(1f).scaleY(1f)
                .setStartDelay(900).setDuration(500)
                .setInterpolator(overshoot).start()

            // ⑥ Button + footer — fade-in
            btnContinue.alpha = 0f
            btnContinue.animate()
                .alpha(0.5f) // starts disabled
                .setStartDelay(1050).setDuration(400).start()

            tvFooter.alpha = 0f
            tvFooter.animate()
                .alpha(1f).setStartDelay(1150).setDuration(350).start()
        }
    }
}
