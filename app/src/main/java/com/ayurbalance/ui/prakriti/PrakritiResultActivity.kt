package com.ayurbalance.ui.prakriti

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.databinding.ActivityPrakritiResultBinding
import com.ayurbalance.ui.dashboard.DashboardActivity

class PrakritiResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrakritiResultBinding
    private val viewModel: ResultViewModel by viewModels()

    companion object {
        const val EXTRA_VATA = "VATA_SCORE"
        const val EXTRA_PITTA = "PITTA_SCORE"
        const val EXTRA_KAPHA = "KAPHA_SCORE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = true
        }

        binding = ActivityPrakritiResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.resultRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, bars.bottom)
            insets
        }

        val vata = intent.getIntExtra(EXTRA_VATA, 14)
        val pitta = intent.getIntExtra(EXTRA_PITTA, 14)
        val kapha = intent.getIntExtra(EXTRA_KAPHA, 14)

        viewModel.compute(vata, pitta, kapha)

        observeViewModel()
        setupButtons()
    }

    private fun observeViewModel() {
        viewModel.resultData.observe(this) { result ->
            bindResult(result)
            runEntryAnimations()
        }
    }

    private fun bindResult(result: ResultModel) {
        binding.tvDoshaType.text = result.doshaLabel
        binding.tvDoshaDescription.text = result.elementsLine
        binding.tvDoshaEmoji.text = result.doshaEmoji
        binding.tvVataPercent.text = "${result.vataPercent}%"
        binding.tvPittaPercent.text = "${result.pittaPercent}%"
        binding.tvKaphaPercent.text = "${result.kaphaPercent}%"
        binding.tvPhysicalInsight.text = result.insights.physical
        binding.tvMentalInsight.text = result.insights.mental
        binding.tvRisksInsight.text = result.insights.risks
        binding.tvRecsInsight.text = result.insights.recommendations
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnBeginJourney.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            overrideTransition()
        }
    }

    private fun runEntryAnimations() {
        val decelerate = DecelerateInterpolator(1.5f)
        val overshoot = OvershootInterpolator(1.2f)

        // ① Header fade-in
        binding.headerSection.animate()
            .alpha(1f)
            .setDuration(500)
            .setInterpolator(decelerate)
            .start()

        // ② Icon circle scale + fade
        binding.iconCircle.animate()
            .alpha(1f).scaleX(1f).scaleY(1f)
            .setStartDelay(350)
            .setDuration(500)
            .setInterpolator(overshoot)
            .start()

        // ③ Dosha cards slide up + fade (staggered)
        listOf(binding.cardVata, binding.cardPitta, binding.cardKapha)
            .forEachIndexed { i, card ->
                card.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(500L + i * 100L)
                    .setDuration(400)
                    .setInterpolator(decelerate)
                    .start()
            }

        // ④ Animate bars after cards appear
        binding.cardVata.postDelayed({
            animateBar(binding.fillVata, binding.trackVata, viewModel.resultData.value?.vataPercent ?: 0)
            animateBar(binding.fillPitta, binding.trackPitta, viewModel.resultData.value?.pittaPercent ?: 0)
            animateBar(binding.fillKapha, binding.trackKapha, viewModel.resultData.value?.kaphaPercent ?: 0)
        }, 700)

        // ⑤ Insights card fade in
        binding.blockPhysical.animate()
            .alpha(1f)
            .setStartDelay(850)
            .setDuration(400)
            .setInterpolator(decelerate)
            .start()

        // ⑥ CTA button
        binding.btnBeginJourney.animate()
            .alpha(1f)
            .setStartDelay(1250)
            .setDuration(400)
            .start()
    }

    private fun animateBar(fill: View, track: View, percent: Int) {
        track.post {
            val fullWidth = track.width.takeIf { it > 0 } ?: return@post
            val targetWidth = (fullWidth * percent / 100f).toInt()
            fill.animate()
                .setDuration(700)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    fill.layoutParams = fill.layoutParams.apply {
                        width = (targetWidth * it.animatedFraction).toInt()
                    }
                    fill.requestLayout()
                }.start()
        }
    }

    private fun overrideTransition() {
        @Suppress("DEPRECATION")
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
