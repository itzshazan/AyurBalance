package com.ayurbalance.ui.logfood

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityFoodLoggedSuccessBinding
import com.ayurbalance.ui.dashboard.DashboardActivity
import com.ayurbalance.ui.meals.MealPlanActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FoodLoggedSuccessActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FOOD_NAME   = "food_name"
        const val EXTRA_CONFIDENCE  = "confidence"
        const val EXTRA_DOSHA_TAG   = "dosha_tag"
        const val EXTRA_CALORIES    = "calories_per_100g"
        const val EXTRA_MEAL_TYPE   = "meal_type"
    }

    private lateinit var binding: ActivityFoodLoggedSuccessBinding
    private val viewModel: FoodLogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodLoggedSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.successRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, bars.bottom)
            insets
        }

        val prediction = FoodPrediction(
            name            = intent.getStringExtra(EXTRA_FOOD_NAME) ?: "Unknown",
            confidence      = intent.getIntExtra(EXTRA_CONFIDENCE, 0),
            doshaTag        = intent.getStringExtra(EXTRA_DOSHA_TAG) ?: "",
            caloriesPer100g = intent.getIntExtra(EXTRA_CALORIES, 0)
        )
        val mealType = intent.getStringExtra(EXTRA_MEAL_TYPE) ?: "Lunch"

        viewModel.load(prediction, mealType)
        observeViewModel()
        setupNavigation()
        animateEntrance()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            binding.tvSuccessFoodName.text = state.foodName
            binding.tvMealType.text        = state.mealType
            binding.tvLogTime.text         = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date())

            val n = state.nutrition
            binding.tvCalories.text = "${n.caloriesPer100g}"
            binding.tvProtein.text  = "%.1f".format(n.proteinG)
            binding.tvCarbs.text    = "%.1f".format(n.carbsG)
            binding.tvFat.text      = "%.1f".format(n.fatG)

            val a = state.ayurveda
            binding.chipRasa.text         = "Rasa: ${a.rasa}"
            binding.chipGuna.text         = "Guna: ${a.guna}"
            binding.chipDoshaEffect.text  = a.doshaEffect
            binding.tvAyurvedicInsight.text = a.insight

            val consumed = state.dailyCaloriesConsumed
            val goal     = state.dailyCaloriesGoal
            binding.tvDailyConsumed.text = "$consumed kcal consumed"
            binding.tvDailyGoal.text     = "of $goal kcal goal"

            val progress = if (goal > 0) (consumed.toFloat() / goal).coerceIn(0f, 1f) else 0f
            binding.dailyProgressCircle.progress = progress
        }
    }

    private fun setupNavigation() {
        binding.btnViewJournal.setOnClickListener {
            navigateToDashboard()
        }
        binding.tvBackToDashboard.setOnClickListener {
            navigateToDashboard()
        }
        binding.bottomNav.selectedItemId = R.id.navLogFood
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome  -> { navigateToDashboard(); true }
                R.id.navMeals -> {
                    startActivity(Intent(this, MealPlanActivity::class.java)
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

    private fun animateEntrance() {
        // Check circle overshoot scale-in
        binding.checkCircle.scaleX = 0f
        binding.checkCircle.scaleY = 0f
        binding.checkCircle.animate()
            .scaleX(1f).scaleY(1f)
            .setDuration(500)
            .setInterpolator(OvershootInterpolator(2f))
            .start()

        // Nutrition cards stagger fade-in
        val cards = listOf(
            binding.cardCalories,
            binding.cardProtein,
            binding.cardCarbs,
            binding.cardFat
        )
        cards.forEachIndexed { i, card ->
            card.alpha = 0f
            card.translationY = 40f
            card.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(300L + i * 80L)
                .setDuration(300)
                .start()
        }

        // Ayurvedic section
        binding.ayurvedicSection.alpha = 0f
        binding.ayurvedicSection.translationY = 40f
        binding.ayurvedicSection.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(680L)
            .setDuration(300)
            .start()

        // Daily progress card
        binding.dailyProgressCard.alpha = 0f
        binding.dailyProgressCard.translationY = 40f
        binding.dailyProgressCard.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(780L)
            .setDuration(300)
            .start()
    }
}
