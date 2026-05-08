package com.ayurbalance.ui.prakriti

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityPrakritiQuestionBinding

class PrakritiQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrakritiQuestionBinding
    private val viewModel: PrakritiViewModel by viewModels()

    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()
        binding = ActivityPrakritiQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupBackNavigation()
        setupOptionCards()
        setupNextButton()
        setupPrevButton()
        setupSaveExitButton()
        startEntryAnimations()

        // Check for saved progress BEFORE observing so the resume dialog
        // appears before any data is loaded into the UI.
        if (viewModel.hasSavedProgress.value == true) {
            showResumeDialog()
        } else {
            observeViewModel()
        }
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.prakritiRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Resume Dialog
    // ──────────────────────────────────────────────

    private fun showResumeDialog() {
        AlertDialog.Builder(this)
            .setTitle("Resume Assessment")
            .setMessage("You have a saved Prakriti assessment in progress. Would you like to continue where you left off?")
            .setCancelable(false)
            .setPositiveButton("Resume") { _, _ ->
                viewModel.resumeSavedState()
                isFirstLoad = false
                observeViewModel()
            }
            .setNegativeButton("Start Over") { _, _ ->
                viewModel.clearSavedState()
                observeViewModel()
            }
            .show()
    }

    // ──────────────────────────────────────────────
    //  Back Navigation
    // ──────────────────────────────────────────────

    private fun setupBackNavigation() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!viewModel.previousQuestion()) {
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // ──────────────────────────────────────────────
    //  Option Card Interactions
    // ──────────────────────────────────────────────

    private fun setupOptionCards() {
        binding.cardVata.setOnClickListener {
            viewModel.selectAnswer(DoshaType.VATA)
            animateCardTap(binding.cardVata)
        }
        binding.cardPitta.setOnClickListener {
            viewModel.selectAnswer(DoshaType.PITTA)
            animateCardTap(binding.cardPitta)
        }
        binding.cardKapha.setOnClickListener {
            viewModel.selectAnswer(DoshaType.KAPHA)
            animateCardTap(binding.cardKapha)
        }
    }

    private fun animateCardTap(card: View) {
        card.animate()
            .scaleX(0.97f).scaleY(0.97f)
            .setDuration(80)
            .withEndAction {
                card.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
            }.start()
    }

    // ──────────────────────────────────────────────
    //  Navigation Buttons
    // ──────────────────────────────────────────────

    private fun setupNextButton() {
        binding.btnNext.setOnClickListener {
            if (viewModel.selectedDosha.value != null) {
                viewModel.nextQuestion()
            } else {
                Toast.makeText(this, "Please select an option to continue.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupPrevButton() {
        binding.btnPrev.setOnClickListener {
            viewModel.previousQuestion()
        }
    }

    private fun setupSaveExitButton() {
        binding.btnSaveExit.setOnClickListener {
            navigateToPrakritiResult()
        }
    }

    private fun updateNextButtonState(enabled: Boolean) {
        binding.btnNext.isEnabled = enabled
        binding.btnNext.animate()
            .alpha(if (enabled) 1f else 0.45f)
            .setDuration(200)
            .start()
    }

    private fun updatePrevButtonVisibility(index: Int) {
        binding.btnPrev.visibility = if (index > 0) View.VISIBLE else View.GONE
    }

    // ──────────────────────────────────────────────
    //  ViewModel Observers
    // ──────────────────────────────────────────────

    private fun observeViewModel() {
        viewModel.currentIndex.observe(this) { index ->
            updatePrevButtonVisibility(index)
            if (isFirstLoad) {
                isFirstLoad = false
                loadQuestionDirect(index)
            } else {
                animateQuestionChange(index)
            }
        }

        viewModel.selectedDosha.observe(this) { dosha ->
            updateCardStates(dosha)
            updateNextButtonState(dosha != null)
        }

        viewModel.vataPercent.observe(this) { percent ->
            binding.tvVataPercent.text = "$percent%"
            animateDoshaBar(binding.barVata, binding.barVataTrack, percent)
        }

        viewModel.pittaPercent.observe(this) { percent ->
            binding.tvPittaPercent.text = "$percent%"
            animateDoshaBar(binding.barPitta, binding.barPittaTrack, percent)
        }

        viewModel.kaphaPercent.observe(this) { percent ->
            binding.tvKaphaPercent.text = "$percent%"
            animateDoshaBar(binding.barKapha, binding.barKaphaTrack, percent)
        }

        viewModel.constitutionNote.observe(this) { note ->
            binding.tvConstitutionNote.text = note
        }

        viewModel.assessmentComplete.observe(this) { complete ->
            if (complete) navigateToPrakritiResult()
        }
    }

    // ──────────────────────────────────────────────
    //  Question Loading (no animation — first load)
    // ──────────────────────────────────────────────

    private fun loadQuestionDirect(index: Int) {
        val question = viewModel.getCurrentQuestion()
        setQuestionContent(question, index)
        animateProgressBar(index)
        updateCardStates(viewModel.selectedDosha.value)
        updateNextButtonState(viewModel.selectedDosha.value != null)
        updatePrevButtonVisibility(index)

        val vPct = viewModel.vataPercent.value ?: 33
        val pPct = viewModel.pittaPercent.value ?: 34
        val kPct = viewModel.kaphaPercent.value ?: 33
        animateDoshaBar(binding.barVata, binding.barVataTrack, vPct)
        animateDoshaBar(binding.barPitta, binding.barPittaTrack, pPct)
        animateDoshaBar(binding.barKapha, binding.barKaphaTrack, kPct)
    }

    // ──────────────────────────────────────────────
    //  Question Transition (fade animation)
    // ──────────────────────────────────────────────

    private fun animateQuestionChange(index: Int) {
        binding.questionContent.animate()
            .alpha(0f)
            .setDuration(140)
            .withEndAction {
                val question = viewModel.getCurrentQuestion()
                setQuestionContent(question, index)
                animateProgressBar(index)
                updateCardStates(viewModel.selectedDosha.value)
                updateNextButtonState(viewModel.selectedDosha.value != null)
                updatePrevButtonVisibility(index)

                binding.questionContent.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start()
            }.start()
    }

    private fun setQuestionContent(question: PrakritiQuestion, index: Int) {
        binding.tvDimensionLabel.text = question.dimension
        binding.tvQuestionText.text = question.questionText
        binding.tvVataOption.text = question.vataOption
        binding.tvPittaOption.text = question.pittaOption
        binding.tvKaphaOption.text = question.kaphaOption
        binding.tvQuestionProgress.text = "Question ${index + 1} of 42"
        val pct = ((index + 1) * 100 / 42f).toInt()
        binding.tvProgressPercent.text = "$pct%"

        if (index == 41) {
            binding.btnNext.text = getString(R.string.btn_complete_assessment)
        } else {
            binding.btnNext.text = getString(R.string.btn_next_question)
        }
    }

    // ──────────────────────────────────────────────
    //  Card State Updates
    // ──────────────────────────────────────────────

    private fun updateCardStates(selected: DoshaType?) {
        val cardMap = mapOf(
            DoshaType.VATA to Pair(binding.cardVata, binding.radioVata),
            DoshaType.PITTA to Pair(binding.cardPitta, binding.radioPitta),
            DoshaType.KAPHA to Pair(binding.cardKapha, binding.radioKapha)
        )
        cardMap.forEach { (dosha, pair) ->
            val (card, radio) = pair
            if (dosha == selected) {
                card.setBackgroundResource(R.drawable.bg_activity_card_selected)
                radio.setImageResource(R.drawable.ic_radio_selected)
                radio.scaleX = 0.5f
                radio.scaleY = 0.5f
                radio.animate().scaleX(1f).scaleY(1f)
                    .setDuration(250).setInterpolator(OvershootInterpolator()).start()
                card.animate().scaleX(1.02f).scaleY(1.02f).setDuration(150).start()
            } else {
                card.setBackgroundResource(R.drawable.bg_activity_card)
                radio.setImageResource(R.drawable.ic_radio_unselected)
                card.animate().scaleX(1f).scaleY(1f).setDuration(150).start()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Progress Bar Animation
    // ──────────────────────────────────────────────

    private fun animateProgressBar(index: Int) {
        val target = (index + 1) / 42f
        binding.progressFill.post {
            val fullWidth = (binding.progressFill.parent as View).width
            val startWidth = binding.progressFill.layoutParams.width.coerceAtLeast(0)
            val targetWidth = (fullWidth * target).toInt()

            binding.progressFill.animate()
                .setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    binding.progressFill.layoutParams = binding.progressFill.layoutParams.apply {
                        width = (startWidth + (targetWidth - startWidth) * it.animatedFraction).toInt()
                    }
                    binding.progressFill.requestLayout()
                }.start()
        }
    }

    // ──────────────────────────────────────────────
    //  Dosha Bar Animations
    // ──────────────────────────────────────────────

    private fun animateDoshaBar(barView: View, trackLayout: View, percent: Int) {
        trackLayout.post {
            val fullWidth = trackLayout.width
            if (fullWidth == 0) return@post
            val startWidth = barView.layoutParams.width.coerceAtLeast(0)
            val targetWidth = (fullWidth * percent / 100f).toInt()

            barView.animate()
                .setDuration(600)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    barView.layoutParams = barView.layoutParams.apply {
                        width = (startWidth + (targetWidth - startWidth) * it.animatedFraction).toInt()
                    }
                    barView.requestLayout()
                }.start()
        }
    }

    // ──────────────────────────────────────────────
    //  Navigation
    // ──────────────────────────────────────────────

    private fun navigateToPrakritiResult() {
        viewModel.clearSavedState()
        val (vata, pitta, kapha) = viewModel.getScores()
        val intent = Intent(this, PrakritiResultActivity::class.java).apply {
            putExtra(PrakritiResultActivity.EXTRA_VATA, vata)
            putExtra(PrakritiResultActivity.EXTRA_PITTA, pitta)
            putExtra(PrakritiResultActivity.EXTRA_KAPHA, kapha)
        }
        startActivity(intent)
        @Suppress("DEPRECATION")
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.0f)

        with(binding) {
            // ① Top bar
            listOf<View>(btnBack, tvTitle, btnSaveExit).forEach { v ->
                v.alpha = 0f
                v.animate().alpha(1f).setStartDelay(80).setDuration(400).start()
            }

            // ② Question content — slide up
            questionContent.alpha = 0f
            questionContent.translationY = 16f
            questionContent.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(200).setDuration(500)
                .setInterpolator(standard).start()

            // ③ Live analysis card — scale-in
            liveAnalysisCard.alpha = 0f
            liveAnalysisCard.scaleX = 0.95f
            liveAnalysisCard.scaleY = 0.95f
            liveAnalysisCard.animate()
                .alpha(1f).scaleX(1f).scaleY(1f)
                .setStartDelay(650).setDuration(450)
                .setInterpolator(overshoot).start()

            // ④ Bottom nav buttons
            btnNext.alpha = 0f
            btnNext.animate()
                .alpha(if (viewModel.selectedDosha.value != null) 1f else 0.45f)
                .setStartDelay(850).setDuration(400).start()

            btnPrev.alpha = 0f
            btnPrev.animate()
                .alpha(1f).setStartDelay(850).setDuration(400).start()
        }
    }
}
