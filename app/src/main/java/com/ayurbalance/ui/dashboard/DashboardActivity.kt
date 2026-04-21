package com.ayurbalance.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayurbalance.databinding.ActivityDashboardBinding

/**
 * Dashboard Activity — Placeholder
 *
 * This will host the main app experience:
 * - Daily Vikriti check-in
 * - Food logging (AI camera, manual, barcode)
 * - Nutrient tracking & dosha alignment scores
 * - Meal plan viewer
 * - Health analytics charts
 *
 * TODO: Implement with BottomNavigationView + NavHostFragment
 */
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
