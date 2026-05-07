package com.ayurbalance.ui.meals

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityMealPlanBinding
import com.ayurbalance.data.models.MealItem
import com.ayurbalance.data.models.SeasonalProtocol
import com.ayurbalance.ui.analytics.AnalyticsActivity
import com.ayurbalance.ui.logfood.LogFoodActivity
import com.ayurbalance.ui.profile.ProfileActivity
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class MealPlanActivity : AppCompatActivity(), SwapMealBottomSheet.SwapListener {

    private lateinit var binding: ActivityMealPlanBinding
    private val viewModel: MealPlanViewModel by viewModels()
    private lateinit var mealAdapter: MealCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor     = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars     = true
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.mealPlanRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, bars.bottom)
            insets
        }

        setupRecyclerView()
        setupBottomNav()
        observeViewModel()
    }

    // ─── RecyclerView setup ──────────────────────────────────────────────────
    private fun setupRecyclerView() {
        mealAdapter = MealCardAdapter { meal ->
            val options = viewModel.getSwapOptions(meal)
            SwapMealBottomSheet()
                .bind(meal, options, this)
                .show(supportFragmentManager, SwapMealBottomSheet.TAG)
        }
        binding.rvMealCards.apply {
            layoutManager          = LinearLayoutManager(this@MealPlanActivity)
            adapter                = mealAdapter
            isNestedScrollingEnabled = false
        }
    }

    // ─── ViewModel observer ──────────────────────────────────────────────────
    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            binding.tvCurrentRitu.text = "THIS WEEK · ${state.ritu.uppercase()}"
            updateWeekStrip(state.currentWeek, state.selectedDate)

            binding.shimmerLayout.visibility   = if (state.isLoading) View.VISIBLE else View.GONE
            binding.rvMealCards.visibility     = if (state.isLoading) View.GONE    else View.VISIBLE
            binding.seasonalCard.visibility    = if (state.isLoading) View.GONE    else View.VISIBLE

            if (!state.isLoading && state.meals.isNotEmpty()) {
                bindMeals(state.meals)
            }

            state.seasonalProtocol?.let { bindSeasonalCard(it) }
        }
    }

    private fun bindMeals(meals: List<MealItem>) {
        mealAdapter.submitList(meals) {
            if (binding.rvMealCards.alpha == 0f) {
                binding.rvMealCards.alpha = 0f
                binding.rvMealCards.animate()
                    .alpha(1f).setDuration(300)
                    .setInterpolator(DecelerateInterpolator())
                    .start()
            }
        }
    }

    private fun bindSeasonalCard(protocol: SeasonalProtocol) {
        binding.tvSeasonalRitu.text     = protocol.displayName
        binding.tvSeasonalGuidance.text = protocol.guidance
        binding.tvSeasonalDesc.text     = protocol.shortDesc
    }

    // ─── Week strip ──────────────────────────────────────────────────────────
    private fun updateWeekStrip(week: List<LocalDate>, selected: LocalDate) {
        val container = binding.weekDayContainer
        if (container.childCount == week.size) {
            week.forEachIndexed { i, date ->
                val child      = container.getChildAt(i) as? LinearLayout ?: return@forEachIndexed
                val tvNumber   = child.getChildAt(1) as? TextView ?: return@forEachIndexed
                val isSelected = date == selected
                applyDayStyle(child, tvNumber, isSelected)
            }
            return
        }

        container.removeAllViews()
        week.forEach { date ->
            val isSelected = date == selected
            val dayView    = buildDayView(date, isSelected)
            dayView.setOnClickListener { viewModel.selectDate(date) }
            container.addView(dayView)
        }

        // Auto-scroll selected day into view
        val idx = week.indexOf(selected)
        if (idx >= 0) {
            binding.weekScrollView.post {
                val child = container.getChildAt(idx)
                child?.let { binding.weekScrollView.smoothScrollTo(it.left - 32, 0) }
            }
        }
    }

    private fun buildDayView(date: LocalDate, isSelected: Boolean): LinearLayout {
        val ctx      = this
        val dp       = resources.displayMetrics.density

        val dayName  = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase()
        val dayNum   = date.dayOfMonth.toString()

        val container = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            gravity     = Gravity.CENTER
            val padH    = (16 * dp).toInt()
            val padV    = (8  * dp).toInt()
            setPadding(padH, padV, padH, padV)
        }

        val tvDay = TextView(ctx).apply {
            text      = dayName
            textSize  = 10f
            setTextColor(if (isSelected) Color.parseColor("#2D5F1B") else Color.parseColor("#8A8279"))
            gravity   = Gravity.CENTER
        }

        val tvNum = TextView(ctx).apply {
            text      = dayNum
            textSize  = 18f
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            gravity   = Gravity.CENTER
            val size  = (40 * dp).toInt()
            layoutParams = LinearLayout.LayoutParams(size, size)
        }

        applyDayStyle(container, tvNum, isSelected)
        container.addView(tvDay)
        container.addView(tvNum)
        return container
    }

    private fun applyDayStyle(container: LinearLayout, tvNum: TextView, isSelected: Boolean) {
        if (isSelected) {
            container.background = null
            tvNum.setBackgroundResource(R.drawable.bg_week_day_selected)
            tvNum.setTextColor(Color.WHITE)
        } else {
            container.background = null
            tvNum.background = null
            tvNum.setTextColor(Color.parseColor("#2A2520"))
        }
    }

    // ─── SwapListener ────────────────────────────────────────────────────────
    override fun onMealSwapped(oldMeal: MealItem, newMeal: MealItem) {
        viewModel.swapMeal(oldMeal, newMeal)
    }

    // ─── Bottom Nav ──────────────────────────────────────────────────────────
    private fun setupBottomNav() {
        binding.bottomNav.selectedItemId = R.id.navMeals
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome    -> { finish(); true }
                R.id.navLogFood -> {
                    startActivity(Intent(this, LogFoodActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                R.id.navMeals     -> true
                R.id.navAnalytics -> {
                    startActivity(Intent(this, AnalyticsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                R.id.navProfile -> {
                    startActivity(Intent(this, ProfileActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
                    true
                }
                else -> true
            }
        }
    }
}
