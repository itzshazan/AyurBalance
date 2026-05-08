package com.ayurbalance.ui.reminders

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
import com.ayurbalance.databinding.ActivityReminderBinding
import com.ayurbalance.ui.analytics.AnalyticsActivity
import com.ayurbalance.ui.dashboard.DashboardActivity
import com.ayurbalance.ui.logfood.LogFoodActivity
import com.ayurbalance.ui.meals.AyurvedicMealEngine
import com.ayurbalance.ui.meals.MealPlanActivity
import com.ayurbalance.ui.profile.ProfileActivity
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class ReminderActivity : AppCompatActivity() {

    companion object {
        const val ACTION_OPEN_REMINDERS = "com.ayurbalance.action.OPEN_REMINDERS"
    }

    private lateinit var binding: ActivityReminderBinding
    private val viewModel: ReminderViewModel by viewModels()

    private val adapter = ReminderAdapter(
        onViewMeal    = { navigateToMeals() },
        onSnooze      = { /* snooze handled by WorkManager reschedule */ },
        onLogWater    = { viewModel.logWater() },
        onMarkDinacharya = { id -> viewModel.markComplete(id) },
        onStartCheckin = { navigateToDashboard() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = true
        }

        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.reminderRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.reminderHeader.setPadding(
                binding.reminderHeader.paddingLeft,
                bars.top + binding.reminderHeader.paddingTop.coerceAtMost(20),
                binding.reminderHeader.paddingRight,
                binding.reminderHeader.paddingBottom
            )
            v.setPadding(0, 0, 0, bars.bottom)
            insets
        }

        setupSubtitle()
        setupRecyclerView()
        setupNavigation()
        observeViewModel()
        animateEntrance()

        binding.ivBack.setOnClickListener { finish() }

        // Ensure notifications are scheduled
        ReminderScheduler.scheduleAll(this)
    }

    private fun setupSubtitle() {
        val today = LocalDate.now()
        val day   = today.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val ritu  = AyurvedicMealEngine.currentRitu()
        binding.tvReminderSubtitle.text = "$day · $ritu"
    }

    private fun setupRecyclerView() {
        binding.rvReminders.layoutManager = LinearLayoutManager(this)
        binding.rvReminders.adapter = adapter
        binding.rvReminders.itemAnimator = null
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            adapter.submitList(state.items)
        }
    }

    private fun animateEntrance() {
        val interp = DecelerateInterpolator(1.5f)

        binding.reminderHeader.alpha = 0f
        binding.reminderHeader.animate()
            .alpha(1f).setDuration(350).setInterpolator(interp).start()

        binding.rvReminders.alpha = 0f
        binding.rvReminders.translationY = 32f
        binding.rvReminders.animate()
            .alpha(1f).translationY(0f)
            .setStartDelay(150)
            .setDuration(400)
            .setInterpolator(interp)
            .start()
    }

    private fun setupNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> { navigateToDashboard(); true }
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

    private fun navigateToDashboard() {
        startActivity(
            Intent(this, DashboardActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        )
        finish()
    }

    private fun navigateToMeals() {
        startActivity(Intent(this, MealPlanActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
    }
}
