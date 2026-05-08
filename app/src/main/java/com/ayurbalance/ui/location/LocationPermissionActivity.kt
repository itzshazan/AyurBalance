package com.ayurbalance.ui.location

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.databinding.ActivityLocationPermissionBinding
import com.ayurbalance.ui.location.model.RegionData
import com.ayurbalance.ui.prakriti.PrakritiQuestionActivity
import com.google.android.gms.location.LocationServices

/**
 * Sits between Profile Setup Step 4 and Prakriti Assessment.
 * Resolves the user's climate zone (used by the Ritucharya seasonal engine).
 * Two states: permission request UI and manual region picker fallback.
 */
class LocationPermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationPermissionBinding
    private val viewModel: LocationPermissionViewModel by viewModels()

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onPermissionGranted()
            fetchDeviceLocation()
        } else {
            viewModel.onPermissionDenied()
            showManualFallback()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If region is already determined (e.g. returning user), skip ahead
        if (viewModel.isRegionAlreadySaved()) {
            navigateToPrakriti()
            return
        }

        configureEdgeToEdge()
        binding = ActivityLocationPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupButtons()
        observeViewModel()
        startEntryAnimations()
    }

    // ──────────────────────────────────────────────
    //  Edge-to-Edge
    // ──────────────────────────────────────────────

    private fun configureEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.locationRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Buttons
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnAllowLocation.setOnClickListener {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        binding.btnSkipLocation.setOnClickListener {
            viewModel.onPermissionDenied()
            showManualFallback()
        }

        binding.btnConfirmRegion.setOnClickListener {
            val selected = binding.regionDropdown.selectedItem as? RegionData
            if (selected != null) {
                viewModel.saveManualRegion(selected)
            } else {
                navigateToPrakriti()
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Location Fetch
    // ──────────────────────────────────────────────

    private fun fetchDeviceLocation() {
        val fusedClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        fusedClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    viewModel.resolveLocationToRegion(location.latitude, location.longitude)
                } else {
                    // lastLocation null on fresh device → graceful fallback
                    showManualFallback()
                }
            }
            .addOnFailureListener { showManualFallback() }
    }

    // ──────────────────────────────────────────────
    //  UI State Transitions
    // ──────────────────────────────────────────────

    private fun showManualFallback() {
        binding.layoutPermissionRequest.animate()
            .alpha(0f).setDuration(200).withEndAction {
                binding.layoutPermissionRequest.visibility = View.GONE
                binding.layoutManualFallback.visibility = View.VISIBLE
                binding.layoutManualFallback.alpha = 0f
                setupRegionDropdown()
                binding.layoutManualFallback.animate()
                    .alpha(1f).setDuration(300).start()
            }.start()
    }

    private fun setupRegionDropdown() {
        val regions = RegionData.allRegions()
        val adapter = RegionDropdownAdapter(this, regions)
        binding.regionDropdown.adapter = adapter

        binding.regionDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val region = regions[pos]
                showClimatePreview(region)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Show preview for first item immediately
        if (regions.isNotEmpty()) showClimatePreview(regions[0])
    }

    private fun showClimatePreview(region: RegionData) {
        val zoneLabel = region.climateZone.replaceFirstChar { it.uppercase() }
        binding.tvClimateZonePreview.text = "$zoneLabel zone · seasonal Ritucharya protocol active"
        if (binding.climatePreviewCard.visibility != View.VISIBLE) {
            binding.climatePreviewCard.visibility = View.VISIBLE
            binding.climatePreviewCard.alpha = 0f
            binding.climatePreviewCard.animate().alpha(1f).setDuration(250).start()
        }
    }

    // ──────────────────────────────────────────────
    //  ViewModel Observers
    // ──────────────────────────────────────────────

    private fun observeViewModel() {
        viewModel.navigateNext.observe(this) { shouldNavigate ->
            if (shouldNavigate == true) navigateToPrakriti()
        }
    }

    // ──────────────────────────────────────────────
    //  Navigation
    // ──────────────────────────────────────────────

    private fun navigateToPrakriti() {
        val intent = Intent(this, PrakritiQuestionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        @Suppress("DEPRECATION")
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.2f)

        with(binding) {
            // ① Top bar
            listOf<View>(btnBack, tvBrand).forEach { v ->
                v.alpha = 0f
                v.animate().alpha(1f).setStartDelay(80).setDuration(400).start()
            }

            // ② Illustration — scale-up from center
            val illustration = layoutPermissionRequest.getChildAt(0)
            illustration?.let {
                it.alpha = 0f
                it.scaleX = 0.7f
                it.scaleY = 0.7f
                it.animate().alpha(1f).scaleX(1f).scaleY(1f)
                    .setStartDelay(180).setDuration(550)
                    .setInterpolator(overshoot).start()
            }

            // ③ Text content — staggered slide-up
            for (i in 1 until layoutPermissionRequest.childCount) {
                val child = layoutPermissionRequest.getChildAt(i)
                child.alpha = 0f
                child.translationY = 18f
                child.animate()
                    .alpha(1f).translationY(0f)
                    .setStartDelay(350L + i * 80L)
                    .setDuration(400)
                    .setInterpolator(standard).start()
            }
        }
    }
}
