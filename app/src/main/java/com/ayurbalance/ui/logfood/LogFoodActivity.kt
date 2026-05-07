package com.ayurbalance.ui.logfood

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityLogFoodBinding
import com.ayurbalance.ui.analytics.AnalyticsActivity
import com.ayurbalance.ui.meals.MealPlanActivity
import com.ayurbalance.ui.profile.ProfileActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LogFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogFoodBinding
    private val viewModel: LogFoodViewModel by viewModels()

    private lateinit var classifier: FoodClassifier
    private lateinit var cameraExecutor: ExecutorService

    // Throttle inference — run at most once per 900 ms
    private var lastInferenceMs = 0L
    private val inferenceIntervalMs = 900L

    // ────────────────────────────────────────
    //  Permission launcher
    // ────────────────────────────────────────
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startCamera()
        else showPermissionDenied()
    }

    // ────────────────────────────────────────
    //  Lifecycle
    // ────────────────────────────────────────
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

        cameraExecutor = Executors.newSingleThreadExecutor()
        classifier = FoodClassifier(this)

        // Load model off the main thread
        lifecycleScope.launch {
            val ready = withContext(Dispatchers.IO) { classifier.initialize() }
            viewModel.setModelReady(ready)
            if (!ready) {
                Snackbar.make(
                    binding.root,
                    "ML model not found — add food_model.tflite to assets/",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        checkCameraPermission()
        setupTabs()
        setupResultRows()
        setupConfirmButton()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        classifier.close()
    }

    // ────────────────────────────────────────
    //  Camera permission
    // ────────────────────────────────────────
    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED -> startCamera()

            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ->
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)

            else -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun showPermissionDenied() {
        Snackbar.make(binding.root, "Camera permission required", Snackbar.LENGTH_LONG)
            .setAction("Settings") {
                startActivity(
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", packageName, null)
                    }
                )
            }.show()
    }

    // ────────────────────────────────────────
    //  CameraX setup
    // ────────────────────────────────────────
    private fun startCamera() {
        val providerFuture = ProcessCameraProvider.getInstance(this)

        providerFuture.addListener({
            val provider = providerFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
            }

            val imageAnalysis = ImageAnalysis.Builder()
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { analysis ->
                    analysis.setAnalyzer(cameraExecutor, ::analyzeFrame)
                }

            try {
                provider.unbindAll()
                provider.bindToLifecycle(
                    this,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageAnalysis
                )
            } catch (e: Exception) {
                Log.e("LogFood", "Camera bind failed: ${e.message}")
            }

        }, ContextCompat.getMainExecutor(this))
    }

    // ────────────────────────────────────────
    //  Frame analysis + TFLite inference
    // ────────────────────────────────────────
    private fun analyzeFrame(imageProxy: ImageProxy) {
        val now = System.currentTimeMillis()
        if (now - lastInferenceMs < inferenceIntervalMs) {
            imageProxy.close()
            return
        }
        lastInferenceMs = now

        // Only run inference when model is ready
        if (!classifier.isReady()) {
            imageProxy.close()
            return
        }

        val bitmap = imageProxyToBitmap(imageProxy)
        imageProxy.close()  // close before running inference to free camera buffer

        val predictions = classifier.classify(bitmap)

        runOnUiThread {
            viewModel.updatePredictions(predictions)
        }
    }

    // CameraX RGBA_8888 → Bitmap (single-plane, no YUV conversion needed)
    private fun imageProxyToBitmap(imageProxy: ImageProxy): Bitmap {
        val plane = imageProxy.planes[0]
        val buffer = plane.buffer
        val bitmap = Bitmap.createBitmap(imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888)
        bitmap.copyPixelsFromBuffer(buffer)
        return bitmap
    }

    // ────────────────────────────────────────
    //  ViewModel observer
    // ────────────────────────────────────────
    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            updateTabHighlight(state.activeTab)

            when (state.phase) {
                LogFoodState.Phase.AWAITING -> {
                    binding.scanningOverlay.visibility = View.VISIBLE
                    binding.tvScanStatus.text = if (state.modelReady) "Scanning…" else "Loading model…"
                    if (binding.resultsContent.visibility == View.VISIBLE) {
                        binding.resultsContent.animate().alpha(0f).setDuration(200)
                            .withEndAction { binding.resultsContent.visibility = View.GONE }.start()
                    }
                }

                LogFoodState.Phase.IDENTIFIED -> {
                    binding.scanningOverlay.visibility = View.GONE
                    bindResults(state)
                }
            }
        }
    }

    private fun bindResults(state: LogFoodState) {
        val predictions = state.predictions
        if (predictions.isEmpty()) return

        // Animate results sheet in on first appearance
        if (binding.resultsContent.visibility != View.VISIBLE) {
            binding.resultsContent.alpha = 0f
            binding.resultsContent.visibility = View.VISIBLE
            binding.resultsContent.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(300)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }

        val top = predictions[0]
        binding.tvIdentifiedName.text = top.name
        binding.tvConfidenceBadge.text = "${top.confidence}% CONFIDENT"

        // Bind the 3 rows
        val rows  = listOf(binding.row0, binding.row1, binding.row2)
        val names = listOf(binding.tvName0, binding.tvName1, binding.tvName2)
        val confs = listOf(binding.tvConf0, binding.tvConf1, binding.tvConf2)

        rows.forEachIndexed { i, row ->
            val pred = predictions.getOrNull(i)
            if (pred == null) {
                row.visibility = View.GONE
                return@forEachIndexed
            }
            row.visibility = View.VISIBLE

            val isSelected = (i == state.selectedIndex)
            row.setBackgroundResource(
                if (isSelected) R.drawable.bg_food_result_selected
                else R.drawable.bg_food_result_unselected
            )
            val primaryColor = if (isSelected) Color.WHITE else Color.parseColor("#2A2520")
            val secondaryColor = if (isSelected) Color.WHITE else Color.parseColor("#8A8279")
            names[i].text = pred.name
            names[i].setTextColor(primaryColor)
            confs[i].text = "${pred.confidence}%"
            confs[i].setTextColor(secondaryColor)
        }

        // Update tags for selected prediction
        val selected = state.selected ?: top
        val (doshaBg, doshaColor, doshaEmoji) = when {
            "PITTA" in selected.doshaTag -> Triple(
                R.drawable.bg_tag_pitta, Color.parseColor("#C4572A"), "🔥"
            )
            "VATA" in selected.doshaTag -> Triple(
                R.drawable.bg_tag_neutral, Color.parseColor("#8866CC"), "💨"
            )
            else -> Triple(
                R.drawable.bg_tag_neutral, Color.parseColor("#2D5F1B"), "🌿"
            )
        }
        binding.tvDoshaTag.setBackgroundResource(doshaBg)
        binding.tvDoshaTag.setTextColor(doshaColor)
        binding.tvDoshaTag.text = "$doshaEmoji  ${selected.doshaTag}"
        binding.tvCalorieTag.text = "🌡  ${selected.caloriesPer100g} KCAL / 100G"
    }

    // ────────────────────────────────────────
    //  UI interactions
    // ────────────────────────────────────────
    private fun setupResultRows() {
        binding.row0.setOnClickListener { viewModel.selectCandidate(0) }
        binding.row1.setOnClickListener { viewModel.selectCandidate(1) }
        binding.row2.setOnClickListener { viewModel.selectCandidate(2) }
    }

    private fun setupConfirmButton() {
        binding.btnConfirmLog.setOnClickListener {
            val selected = viewModel.state.value?.selected ?: return@setOnClickListener
            val intent = Intent(this, FoodLoggedSuccessActivity::class.java).apply {
                putExtra(FoodLoggedSuccessActivity.EXTRA_FOOD_NAME,  selected.name)
                putExtra(FoodLoggedSuccessActivity.EXTRA_CONFIDENCE,  selected.confidence)
                putExtra(FoodLoggedSuccessActivity.EXTRA_DOSHA_TAG,   selected.doshaTag)
                putExtra(FoodLoggedSuccessActivity.EXTRA_CALORIES,    selected.caloriesPer100g)
                putExtra(FoodLoggedSuccessActivity.EXTRA_MEAL_TYPE,   detectMealType())
            }
            startActivity(intent)
            finish()
        }
    }

    private fun detectMealType(): String {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return when {
            hour < 10 -> "Breakfast"
            hour < 15 -> "Lunch"
            hour < 18 -> "Snack"
            else      -> "Dinner"
        }
    }

    private fun setupTabs() {
        binding.tabCamera.setOnClickListener  { viewModel.setTab(LogFoodState.Tab.CAMERA) }
        binding.tabSearch.setOnClickListener  { viewModel.setTab(LogFoodState.Tab.SEARCH) }
        binding.tabBarcode.setOnClickListener { viewModel.setTab(LogFoodState.Tab.BARCODE) }
        binding.tabVoice.setOnClickListener   { viewModel.setTab(LogFoodState.Tab.VOICE) }

        binding.bottomNav.selectedItemId = R.id.navLogFood
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome  -> { finish(); true }
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
                view.background = null
                view.setTextColor(Color.parseColor("#4A4540"))
            }
        }
    }
}
