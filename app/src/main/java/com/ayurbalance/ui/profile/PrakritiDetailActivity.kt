package com.ayurbalance.ui.profile

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.databinding.ActivityPrakritiDetailBinding
import com.ayurbalance.ui.prakriti.PrakritiQuestionActivity

class PrakritiDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VATA  = "extra_vata_pct"
        const val EXTRA_PITTA = "extra_pitta_pct"
        const val EXTRA_KAPHA = "extra_kapha_pct"
        const val EXTRA_TYPE  = "extra_prakriti_type"
    }

    private lateinit var binding: ActivityPrakritiDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrakritiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor     = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars     = false
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.prakritiDetailRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.headerSection.setPadding(0, bars.top, 0, 0)
            v.setPadding(0, 0, 0, bars.bottom)
            insets
        }

        val vataPct  = intent.getIntExtra(EXTRA_VATA,  33)
        val pittaPct = intent.getIntExtra(EXTRA_PITTA, 33)
        val kaphaPct = intent.getIntExtra(EXTRA_KAPHA, 34)
        val type     = intent.getStringExtra(EXTRA_TYPE) ?: ""

        bindData(vataPct, pittaPct, kaphaPct, type)
        setupButtons()
        animateEntrance()
    }

    private fun bindData(vata: Int, pitta: Int, kapha: Int, type: String) {
        // Header
        val dominant = when {
            pitta >= vata && pitta >= kapha -> "Pitta"
            vata  >= pitta && vata >= kapha -> "Vāta"
            else                            -> "Kapha"
        }
        binding.tvDoshaEmoji.text      = when (dominant) { "Pitta" -> "🔥"; "Kapha" -> "🌿"; else -> "💨" }
        binding.tvDominantDosha.text   = "$dominant Constitution"
        binding.tvDoshaElements.text   = when (dominant) {
            "Pitta" -> "Fire · Water"
            "Kapha" -> "Earth · Water"
            else    -> "Air · Ether"
        }

        // Percentages + bars
        binding.tvVataPct.text  = "$vata%"
        binding.tvPittaPct.text = "$pitta%"
        binding.tvKaphaPct.text = "$kapha%"

        listOf(
            binding.fillVata  to vata,
            binding.fillPitta to pitta,
            binding.fillKapha to kapha
        ).forEachIndexed { i, (fill, pct) ->
            val track = when (i) {
                0 -> binding.trackVata; 1 -> binding.trackPitta; else -> binding.trackKapha
            }
            track.post {
                val fullW = track.width.takeIf { it > 0 } ?: return@post
                ValueAnimator.ofInt(0, (fullW * pct / 100f).toInt()).apply {
                    duration    = 800
                    startDelay  = 300L + i * 100L
                    interpolator = DecelerateInterpolator()
                    addUpdateListener { anim ->
                        fill.layoutParams = fill.layoutParams.apply { width = anim.animatedValue as Int }
                        fill.requestLayout()
                    }
                    start()
                }
            }
        }

        // Trait descriptions
        binding.tvVataTraits.text  = "Creative, quick, light. Governed by Air & Ether. Balanced Vata brings vitality and enthusiasm; imbalanced brings anxiety and dryness."
        binding.tvPittaTraits.text = "Sharp, focused, warm. Governed by Fire & Water. Balanced Pitta brings intelligence and courage; imbalanced brings irritability and inflammation."
        binding.tvKaphaTraits.text = "Steady, nurturing, strong. Governed by Earth & Water. Balanced Kapha brings love and endurance; imbalanced brings lethargy and attachment."

        // Recommendations
        binding.tvRecommendations.text = when (dominant) {
            "Pitta"  -> "• Favour cooling foods: cucumber, coriander, coconut\n• Avoid excess spicy, salty, or sour foods\n• Practice moderate exercise — avoid overheating\n• Meditation and moonlit walks pacify Pitta\n• Sleep by 10 PM to protect digestive fire"
            "Kapha"  -> "• Favour light, dry, spicy foods: ginger, pepper, mustard\n• Avoid heavy dairy, fried food, and excess sweets\n• Vigorous morning exercise awakens Kapha energy\n• Dry brushing (Garshana) stimulates lymphatic flow\n• Rise before 6 AM to avoid Kapha sluggishness"
            else     -> "• Favour warm, oily, grounding foods: ghee, sesame, root vegetables\n• Avoid cold, raw, or dry foods especially in winter\n• Consistent meal and sleep timing stabilises Vata\n• Abhyanga (oil massage) soothes the nervous system\n• Gentle yoga and slow walking ground Vata energy"
        }
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnRetake.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Retake Assessment")
                .setMessage("Retaking will archive your current results. Ready to continue?")
                .setPositiveButton("Retake") { _, _ ->
                    startActivity(Intent(this, PrakritiQuestionActivity::class.java))
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun animateEntrance() {
        val decelerate = DecelerateInterpolator(1.5f)
        listOf(
            binding.doshaCard,
            binding.traitsCard,
            binding.recommendationsCard,
            binding.btnRetake
        ).forEachIndexed { i, v ->
            v.alpha        = 0f
            v.translationY = 28f
            v.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(100L + i * 100L)
                .setDuration(380)
                .setInterpolator(decelerate)
                .start()
        }
    }
}
