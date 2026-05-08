package com.ayurbalance.ui.dashboard

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
import android.content.Intent
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityDashboardBinding
import com.ayurbalance.ui.analytics.AnalyticsActivity
import com.ayurbalance.ui.logfood.LogFoodActivity
import com.ayurbalance.ui.meals.MealPlanActivity
import com.ayurbalance.ui.profile.ProfileActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: HomeDashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = true
        }

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.dashboardRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.headerSection.setPadding(
                binding.headerSection.paddingLeft,
                bars.top + binding.headerSection.paddingTop.coerceAtMost(16),
                binding.headerSection.paddingRight,
                binding.headerSection.paddingBottom
            )
            v.setPadding(0, 0, 0, bars.bottom)
            insets
        }

        binding.progressPrana.isDashed = true
        binding.progressPrana.trackColor = Color.parseColor("#D8CFC4")

        binding.fabCamera.setOnClickListener {
            startActivity(Intent(this, LogFoodActivity::class.java))
        }
        binding.btnCalm.setOnClickListener {
            binding.alertCard.animate()
                .alpha(0f).translationY(-8f)
                .setDuration(250)
                .withEndAction { binding.alertCard.visibility = View.GONE }
                .start()
        }

        observeViewModel()
        setupBottomNav()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            bind(state)
            animateBars(state)
            animateEntrance()
        }
    }

    private fun bind(state: DashboardState) {
        binding.tvGreeting.text = state.greeting
        binding.tvUserName.text = state.userName
        binding.tvDayRitu.text = state.dayRitu
        binding.tvStreak.text = state.streakDays.toString()

        loadAvatar(state.avatarUrl, state.userInitials)

        // Dosha percentages
        binding.tvVataPercent.text = "${state.vataPercent}%"
        binding.tvPittaPercent.text = "${state.pittaPercent}%${if (state.dominantDosha == "PITTA") "↑" else ""}"
        binding.tvKaphaPercent.text = "${state.kaphaPercent}%"

        val boldSize = 13f
        val normalSize = 12f
        binding.tvVataPercent.textSize  = if (state.dominantDosha == "VATA")  boldSize else normalSize
        binding.tvPittaPercent.textSize = if (state.dominantDosha == "PITTA") boldSize else normalSize
        binding.tvKaphaPercent.textSize = if (state.dominantDosha == "KAPHA") boldSize else normalSize

        // Imbalance alert
        val alert = state.imbalanceAlert
        if (alert != null) {
            binding.alertCard.visibility = View.VISIBLE
            binding.tvAlertTitle.text = alert.title
            binding.tvAlertDesc.text = alert.description
        } else {
            binding.alertCard.visibility = View.GONE
        }

        // Meal
        val meal = state.nextMeal
        binding.tvMealTimeLabel.text = "${meal.label.uppercase()} · ${meal.time}"
        binding.tvMealName.text = meal.name
        binding.tvMealInfo.text = "${meal.calories} kcal · ${meal.doshaNote}"
        binding.tvMealEmoji.text = meal.emoji
        binding.tvCarbs.text = "${meal.carbs}g Carbs"
        binding.tvProtein.text = "${meal.protein}g Protein"
        binding.tvFats.text = "${meal.fats}g Fats"

        // Wellness
        val hydrFraction = (state.hydrationCurrentL / state.hydrationGoalL).coerceIn(0f, 1f)
        binding.progressHydration.progress = hydrFraction
        binding.tvHydration.text = "%.1fL / %.0fL".format(state.hydrationCurrentL, state.hydrationGoalL)

        val pranaFraction = (state.pranaBreathsPerMin / 20f).coerceIn(0f, 1f)
        binding.progressPrana.progress = pranaFraction
        binding.tvPrana.text = "${state.pranaBreathsPerMin} / min"
    }

    private fun loadAvatar(url: String?, initials: String) {
        if (!url.isNullOrEmpty()) {
            binding.tvUserInitial.visibility = View.GONE
            binding.ivUserAvatar.visibility = View.VISIBLE
            Glide.with(this)
                .load(url)
                .apply(RequestOptions().transform(CircleCrop()))
                .into(binding.ivUserAvatar)
        } else {
            binding.ivUserAvatar.visibility = View.GONE
            binding.tvUserInitial.visibility = View.VISIBLE
            binding.tvUserInitial.text = initials
        }
    }

    private fun animateBars(state: DashboardState) {
        animateBar(binding.fillVata, binding.trackVata, state.vataPercent)
        animateBar(binding.fillPitta, binding.trackPitta, state.pittaPercent)
        animateBar(binding.fillKapha, binding.trackKapha, state.kaphaPercent)
    }

    private fun animateBar(fill: View, track: View, percent: Int) {
        track.post {
            val fullWidth = track.width.takeIf { it > 0 } ?: return@post
            val targetWidth = (fullWidth * percent / 100f).toInt()
            fill.animate()
                .setStartDelay(300)
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

    private fun animateEntrance() {
        val decelerate = DecelerateInterpolator(1.5f)

        binding.headerSection.alpha = 0f
        binding.headerSection.animate().alpha(1f).setDuration(400).setInterpolator(decelerate).start()

        listOf(binding.alertCard, binding.doshaCard, binding.mealCard, binding.wellnessRow)
            .forEachIndexed { i, v ->
                if (v.visibility == View.GONE) return@forEachIndexed
                v.alpha = 0f
                v.translationY = 24f
                v.animate().alpha(1f).translationY(0f)
                    .setStartDelay(200L + i * 80L)
                    .setDuration(400)
                    .setInterpolator(decelerate)
                    .start()
            }

        binding.fabCamera.scaleX = 0f
        binding.fabCamera.scaleY = 0f
        binding.fabCamera.animate()
            .scaleX(1f).scaleY(1f)
            .setStartDelay(600)
            .setDuration(350)
            .setInterpolator(OvershootInterpolator(1.2f))
            .start()
    }

    private fun setupBottomNav() {
        binding.bottomNav.selectedItemId = R.id.navHome
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome    -> true
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
}
