package com.ayurbalance.ui.profile

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityProfileBinding
import com.ayurbalance.ui.analytics.AnalyticsActivity
import com.ayurbalance.ui.dashboard.DashboardActivity
import com.ayurbalance.ui.logfood.LogFoodActivity
import com.ayurbalance.ui.meals.MealPlanActivity
import com.ayurbalance.ui.prakriti.PrakritiQuestionActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileScreenViewModel by viewModels()

    private lateinit var notifManager: NotificationSettingsManager
    private lateinit var pdfExporter: PdfExportManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor     = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars     = false
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.profileRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.headerSection.setPadding(0, bars.top, 0, 0)
            v.setPadding(0, 0, 0, bars.bottom)
            insets
        }

        notifManager = NotificationSettingsManager(this)
        pdfExporter  = PdfExportManager(this)

        observeViewModel()
        setupActions()
        setupBottomNav()
    }

    // ─── ViewModel ────────────────────────────────────────────────────────────

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            if (state.isLoading) {
                showSkeleton()
                return@observe
            }
            if (state.isEmpty()) {
                showEmpty()
                return@observe
            }
            bindState(state)
        }
    }

    private fun bindState(state: ProfileState) {
        hideSkeleton()

        // Header
        binding.tvUserInitials.text = state.initials
        binding.tvDisplayName.text  = state.displayName
        val subLine = buildList {
            if (state.constitutionLabel.isNotBlank()) add(state.constitutionLabel)
            if (state.age > 0) add("${state.age}")
        }.joinToString(" · ")
        binding.tvProfileSub.text = subLine.ifBlank { state.email }
        binding.streakBadge.text  = "🔥 ${state.streakDays} day streak"
        binding.streakBadge.visibility = if (state.streakDays > 0) View.VISIBLE else View.GONE

        // Prakriti card
        bindDoshaBar(binding.fillVata, binding.trackVata, state.vataPct)
        bindDoshaBar(binding.fillPitta, binding.trackPitta, state.pittaPct)
        bindDoshaBar(binding.fillKapha, binding.trackKapha, state.kaphaPct)
        binding.tvVataPct.text   = "${state.vataPct}%"
        binding.tvPittaPct.text  = "${state.pittaPct}%"
        binding.tvKaphaPct.text  = "${state.kaphaPct}%"
        binding.tvConstitution.text = state.constitutionLabel.ifBlank { state.prakritiType }

        // Diet card
        val dietLabel = state.dietType.replace("_", " ").replaceFirstChar { it.uppercase() }
        binding.tvDietType.text = dietLabel.ifBlank { "Not set" }
        val conditionsText = state.healthConditions.joinToString(", ") {
            it.replace("_", " ").replaceFirstChar { c -> c.uppercase() }
        }
        binding.tvConditions.text     = conditionsText.ifBlank { "None noted" }
        binding.tvGoal.text           = state.goal.replace("_", " ").replaceFirstChar { it.uppercase() }.ifBlank { "—" }
        binding.tvActivityLevel.text  = state.activityLevel.replace("_", " ").replaceFirstChar { it.uppercase() }.ifBlank { "—" }

        // Notification switches (set without triggering listener)
        binding.switchMeals.setOnCheckedChangeListener(null)
        binding.switchHydration.setOnCheckedChangeListener(null)
        binding.switchDinacharya.setOnCheckedChangeListener(null)

        binding.switchMeals.isChecked     = state.mealReminders
        binding.switchHydration.isChecked = state.hydrationReminders
        binding.switchDinacharya.isChecked = state.dinacharyaReminders

        binding.switchMeals.setOnCheckedChangeListener { _, checked ->
            viewModel.setMealReminders(checked)
            notifManager.scheduleMealReminders(checked)
        }
        binding.switchHydration.setOnCheckedChangeListener { _, checked ->
            viewModel.setHydrationReminders(checked)
            notifManager.scheduleHydrationReminders(checked)
        }
        binding.switchDinacharya.setOnCheckedChangeListener { _, checked ->
            viewModel.setDinacharyaReminders(checked)
            notifManager.scheduleDinacharyaReminders(checked)
        }

        animateEntrance()
    }

    private fun bindDoshaBar(fill: View, track: View, pct: Int) {
        track.post {
            val fullW = track.width.takeIf { it > 0 } ?: return@post
            val target = (fullW * pct / 100f).toInt()
            ValueAnimator.ofInt(0, target).apply {
                duration    = 800
                startDelay  = 200
                interpolator = DecelerateInterpolator()
                addUpdateListener { anim ->
                    val lp = fill.layoutParams
                    lp.width = anim.animatedValue as Int
                    fill.layoutParams = lp
                }
                start()
            }
        }
    }

    // ─── Actions ──────────────────────────────────────────────────────────────

    private fun setupActions() {
        // Prakriti card → PrakritiDetailActivity
        binding.cardPrakriti.setOnClickListener {
            val state = viewModel.state.value ?: return@setOnClickListener
            val intent = Intent(this, PrakritiDetailActivity::class.java).apply {
                putExtra(PrakritiDetailActivity.EXTRA_VATA,  state.vataPct)
                putExtra(PrakritiDetailActivity.EXTRA_PITTA, state.pittaPct)
                putExtra(PrakritiDetailActivity.EXTRA_KAPHA, state.kaphaPct)
                putExtra(PrakritiDetailActivity.EXTRA_TYPE,  state.prakritiType)
            }
            startActivity(intent)
        }

        // Diet card → edit preferences (reuse profile step 3)
        binding.cardDiet.setOnClickListener {
            // Future: DietPreferencesActivity
        }

        // Privacy & Data
        binding.rowPrivacy.setOnClickListener {
            startActivity(Intent(this, PrivacySettingsActivity::class.java))
        }

        // Export PDF
        binding.btnExportPdf.setOnClickListener {
            val state = viewModel.state.value ?: return@setOnClickListener
            binding.btnExportPdf.isEnabled = false
            lifecycleScope.launch {
                pdfExporter.exportReport(state) { uri ->
                    binding.btnExportPdf.isEnabled = true
                    uri?.let { pdfExporter.openShareSheet(this@ProfileActivity, it) }
                }
            }
        }

        // Retake assessment
        binding.rowRetakeAssessment.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Retake Prakriti Assessment")
                .setMessage("Your current assessment will be archived and a new one will begin. Continue?")
                .setPositiveButton("Retake") { _, _ ->
                    startActivity(Intent(this, PrakritiQuestionActivity::class.java))
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        // Sign out
        binding.rowSignOut.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Sign Out") { _, _ -> signOut() }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun signOut() {
        // Navigate to splash/onboarding and clear stack
        startActivity(
            Intent(this, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        )
    }

    // ─── Animations ───────────────────────────────────────────────────────────

    private fun animateEntrance() {
        val decelerate = DecelerateInterpolator(1.5f)

        // Avatar pop
        binding.avatarCircle.scaleX = 0f
        binding.avatarCircle.scaleY = 0f
        binding.avatarCircle.animate()
            .scaleX(1f).scaleY(1f)
            .setDuration(400).setInterpolator(OvershootInterpolator(1.5f))
            .start()

        // Cards stagger
        listOf(
            binding.cardPrakriti,
            binding.cardDiet,
            binding.cardNotifications,
            binding.dividerActions,
            binding.rowPrivacy,
            binding.btnExportPdf,
            binding.rowRetakeAssessment,
            binding.rowSignOut
        ).forEachIndexed { i, v ->
            v.alpha        = 0f
            v.translationY = 24f
            v.animate()
                .alpha(1f).translationY(0f)
                .setStartDelay(120L + i * 80L)
                .setDuration(380)
                .setInterpolator(decelerate)
                .start()
        }
    }

    private fun showSkeleton() {
        binding.loadingGroup.visibility = View.VISIBLE
        binding.contentGroup.visibility = View.GONE
    }

    private fun hideSkeleton() {
        binding.loadingGroup.visibility = View.GONE
        binding.contentGroup.visibility = View.VISIBLE
    }

    private fun showEmpty() {
        hideSkeleton()
        binding.emptyState.visibility = View.VISIBLE
    }

    // ─── Bottom Nav ───────────────────────────────────────────────────────────

    private fun setupBottomNav() {
        binding.bottomNav.selectedItemId = R.id.navProfile
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> {
                    startActivity(Intent(this, DashboardActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)); true
                }
                R.id.navLogFood -> {
                    startActivity(Intent(this, LogFoodActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)); true
                }
                R.id.navMeals -> {
                    startActivity(Intent(this, MealPlanActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)); true
                }
                R.id.navAnalytics -> {
                    startActivity(Intent(this, AnalyticsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)); true
                }
                R.id.navProfile -> true
                else -> true
            }
        }
    }
}

private fun ProfileState.isEmpty() = displayName.isBlank() && email.isBlank()
