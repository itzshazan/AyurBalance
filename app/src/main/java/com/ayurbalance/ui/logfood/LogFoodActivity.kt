package com.ayurbalance.ui.logfood

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityLogFoodBinding

class LogFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogFoodBinding
    private val viewModel: LogFoodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = true
        }

        binding = ActivityLogFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.logFoodRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, bars.bottom)
            insets
        }

        setupTabs()
        setupResultRows()
        setupButtons()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            when (state.phase) {
                LogFoodState.Phase.SCANNING -> {
                    binding.scanningOverlay.visibility = View.VISIBLE
                    binding.resultsContent.visibility = View.GONE
                }
                LogFoodState.Phase.IDENTIFIED -> {
                    binding.scanningOverlay.visibility = View.GONE
                    showResults(state)
                }
            }

            updateTabHighlight(state.activeTab)
        }
    }

    private fun showResults(state: LogFoodState) {
        if (binding.resultsContent.visibility == View.GONE) {
            binding.resultsContent.visibility = View.VISIBLE
            binding.resultsContent.alpha = 0f
            binding.resultsContent.translationY = 20f
            binding.resultsContent.animate()
                .alpha(1f).translationY(0f)
                .setDuration(350)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }

        val candidates = state.candidates
        if (candidates.isEmpty()) return

        val selected = state.selectedIndex
        val top = candidates[0]

        binding.tvIdentifiedName.text = top.name
        binding.tvConfidenceBadge.text = "${top.confidence}% CONFIDENT"

        // Row 0
        binding.tvName0.text = candidates.getOrNull(0)?.name ?: ""
        binding.tvConf0.text = "${candidates.getOrNull(0)?.confidence ?: 0}%"

        // Row 1
        binding.tvName1.text = candidates.getOrNull(1)?.name ?: ""
        binding.tvConf1.text = "${candidates.getOrNull(1)?.confidence ?: 0}%"

        // Row 2
        binding.tvName2.text = candidates.getOrNull(2)?.name ?: ""
        binding.tvConf2.text = "${candidates.getOrNull(2)?.confidence ?: 0}%"

        // Highlight selected row
        val rows = listOf(binding.row0, binding.row1, binding.row2)
        val nameViews = listOf(binding.tvName0, binding.tvName1, binding.tvName2)
        val confViews = listOf(binding.tvConf0, binding.tvConf1, binding.tvConf2)

        rows.forEachIndexed { i, row ->
            val isSelected = (i == selected)
            row.setBackgroundResource(
                if (isSelected) R.drawable.bg_food_result_selected
                else R.drawable.bg_food_result_unselected
            )
            val textColor = if (isSelected) Color.WHITE else Color.parseColor("#2A2520")
            nameViews[i].setTextColor(textColor)
            confViews[i].setTextColor(if (isSelected) Color.WHITE else Color.parseColor("#8A8279"))
        }

        // Tags for the selected candidate
        val pick = candidates.getOrNull(selected) ?: return
        val doshaEmoji = when {
            "PITTA" in pick.doshaTag -> "🔥"
            "VATA"  in pick.doshaTag -> "💨"
            else                     -> "🌿"
        }
        val doshaColor = when {
            "PITTA" in pick.doshaTag -> {
                binding.tvDoshaTag.setBackgroundResource(R.drawable.bg_tag_pitta)
                Color.parseColor("#C4572A")
            }
            "VATA"  in pick.doshaTag -> {
                binding.tvDoshaTag.setBackgroundResource(R.drawable.bg_tag_neutral)
                Color.parseColor("#8866CC")
            }
            else -> {
                binding.tvDoshaTag.setBackgroundResource(R.drawable.bg_tag_neutral)
                Color.parseColor("#2D5F1B")
            }
        }
        binding.tvDoshaTag.text = "$doshaEmoji  ${pick.doshaTag}"
        binding.tvDoshaTag.setTextColor(doshaColor)
        binding.tvCalorieTag.text = "🌡  ${pick.caloriesPer100g} KCAL / 100G"
    }

    private fun setupResultRows() {
        binding.row0.setOnClickListener { viewModel.selectCandidate(0) }
        binding.row1.setOnClickListener { viewModel.selectCandidate(1) }
        binding.row2.setOnClickListener { viewModel.selectCandidate(2) }
    }

    private fun setupButtons() {
        binding.btnConfirmLog.setOnClickListener {
            // Stub: log the selected food
            finish()
        }
    }

    private fun setupTabs() {
        binding.tabCamera.setOnClickListener  { viewModel.setTab(LogFoodState.Tab.CAMERA) }
        binding.tabSearch.setOnClickListener  { viewModel.setTab(LogFoodState.Tab.SEARCH) }
        binding.tabBarcode.setOnClickListener { viewModel.setTab(LogFoodState.Tab.BARCODE) }
        binding.tabVoice.setOnClickListener   { viewModel.setTab(LogFoodState.Tab.VOICE) }

        binding.bottomNav.selectedItemId = R.id.navLogFood
        binding.bottomNav.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.navHome) {
                finish()
                true
            } else {
                true
            }
        }
    }

    private fun updateTabHighlight(activeTab: LogFoodState.Tab) {
        val tabs = mapOf(
            LogFoodState.Tab.CAMERA  to binding.tabCamera,
            LogFoodState.Tab.SEARCH  to binding.tabSearch,
            LogFoodState.Tab.BARCODE to binding.tabBarcode,
            LogFoodState.Tab.VOICE   to binding.tabVoice
        )
        tabs.forEach { (tab, view) ->
            if (tab == activeTab) {
                view.setBackgroundResource(R.drawable.bg_food_tab_selected)
                view.setTextColor(Color.WHITE)
            } else {
                view.setBackgroundResource(0)
                view.setTextColor(Color.parseColor("#4A4540"))
            }
        }
    }
}
