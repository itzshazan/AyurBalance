package com.ayurbalance.ui.analytics

import android.content.Intent
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityAnalyticsBinding
import com.ayurbalance.ui.dashboard.DashboardActivity
import com.ayurbalance.ui.logfood.LogFoodActivity
import com.ayurbalance.ui.meals.MealPlanActivity
import com.ayurbalance.ui.profile.ProfileActivity

class AnalyticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalyticsBinding
    private val viewModel: AnalyticsViewModel by viewModels()
    private lateinit var macroAdapter: MacroProgressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor     = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars     = false
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.analyticsRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.headerSection.setPadding(0, bars.top, 0, 0)
            v.setPadding(0, 0, 0, bars.bottom)
            insets
        }

        setupRecyclerView()
        observeViewModel()
        setupBottomNav()
    }

    private fun setupRecyclerView() {
        macroAdapter = MacroProgressAdapter()
        binding.rvMacros.apply {
            layoutManager = LinearLayoutManager(this@AnalyticsActivity)
            adapter = macroAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            when {
                state.isLoading -> return@observe
                state.isEmpty   -> showEmpty()
                else            -> showContent(state)
            }
        }
    }

    private fun showEmpty() {
        binding.cardAvgKcal.visibility        = View.GONE
        binding.cardDoshaAlignment.visibility = View.GONE
        binding.chartCard.visibility          = View.GONE
        binding.macroCard.visibility          = View.GONE
        binding.insightCard.visibility        = View.GONE
        binding.emptyState.visibility         = View.VISIBLE
    }

    private fun showContent(state: AnalyticsState) {
        binding.emptyState.visibility = View.GONE

        // Summary cards
        binding.tvAvgKcal.text      = state.avgDailyKcal.toString()
        binding.tvKcalDelta.text    = formatDeltaPercent(state.kcalDeltaPercent)
        binding.tvDoshaAlignment.text = "${state.doshaAlignmentPercent}%"
        binding.tvAlignmentDelta.text = formatDeltaPoints(state.doshaAlignmentDelta)

        // Chart
        if (state.chartBars.isNotEmpty()) {
            binding.chartView.setBars(state.chartBars)
        }

        // Macros
        macroAdapter.submitList(listOf(
            MacroItem("Carbohydrates", state.carbsPercent,   Color.parseColor("#E8A75A")),
            MacroItem("Protein",       state.proteinPercent, Color.parseColor("#2D5F1B")),
            MacroItem("Fat",           state.fatPercent,     Color.parseColor("#C4572A"))
        ))

        // Insight
        if (state.insightText.isNotEmpty()) {
            binding.tvInsight.text         = state.insightText
            binding.insightCard.visibility = View.VISIBLE
        } else {
            binding.insightCard.visibility = View.GONE
        }

        animateEntrance()
    }

    private fun formatDeltaPercent(delta: Float): String = when {
        delta > 0.5f  -> "↑ +${"%.0f".format(delta)}% vs last week"
        delta < -0.5f -> "↓ ${"%.0f".format(delta)}% vs last week"
        else          -> "≈ same as last week"
    }

    private fun formatDeltaPoints(delta: Float): String = when {
        delta > 0.5f  -> "↑ +${"%.0f".format(delta)}pt vs last week"
        delta < -0.5f -> "↓ ${"%.0f".format(delta)}pt vs last week"
        else          -> "≈ same as last week"
    }

    private fun animateEntrance() {
        val decelerate = DecelerateInterpolator(1.5f)
        listOf(
            binding.cardAvgKcal,
            binding.cardDoshaAlignment,
            binding.chartCard,
            binding.macroCard,
            binding.insightCard
        ).forEachIndexed { i, v ->
            v.alpha       = 0f
            v.translationY = 32f
            v.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(80L + i * 100L)
                .setDuration(400)
                .setInterpolator(decelerate)
                .start()
        }
    }

    private fun setupBottomNav() {
        binding.bottomNav.selectedItemId = R.id.navAnalytics
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> {
                    startActivity(Intent(this, DashboardActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                R.id.navLogFood -> {
                    startActivity(Intent(this, LogFoodActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                R.id.navMeals -> {
                    startActivity(Intent(this, MealPlanActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                R.id.navAnalytics -> true
                R.id.navProfile -> {
                    startActivity(Intent(this, ProfileActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                else              -> true
            }
        }
    }
}
