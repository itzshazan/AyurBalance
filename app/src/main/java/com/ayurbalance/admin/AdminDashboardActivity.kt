package com.ayurbalance.admin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ayurbalance.R
import com.ayurbalance.admin.data.AdminSupabaseService
import com.ayurbalance.admin.ui.activity.LiveActivityFragment
import com.ayurbalance.admin.ui.compliance.ComplianceFragment
import com.ayurbalance.admin.ui.dashboard.DashboardFragment
import com.ayurbalance.admin.ui.fooddb.FoodDatabaseFragment
import com.ayurbalance.admin.ui.ml.MLMonitorFragment
import com.ayurbalance.admin.ui.recipes.RecipesFragment
import com.ayurbalance.admin.ui.system.SystemHealthFragment
import com.ayurbalance.admin.ui.users.UserManagementFragment
import com.ayurbalance.databinding.ActivityAdminDashboardBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class AdminDashboardActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigationView()
        setupTopBar()

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(DashboardFragment(), "Dashboard")
            binding.navView.setCheckedItem(R.id.navDashboard)
        }
    }

    private fun setupNavigationView() {
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun setupTopBar() {
        binding.btnMenuToggle.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        binding.btnSignOut.setOnClickListener {
            lifecycleScope.launch {
                AdminSupabaseService.signOut()
                val intent = Intent(this@AdminDashboardActivity, AdminLoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        val (fragment, title) = when (item.itemId) {
            R.id.navDashboard -> Pair(DashboardFragment(), "Dashboard")
            R.id.navActivity -> Pair(LiveActivityFragment(), "Live activity")
            R.id.navUsers -> Pair(UserManagementFragment(), "User management")
            R.id.navCompliance -> Pair(ComplianceFragment(), "Compliance")
            R.id.navFoodDb -> Pair(FoodDatabaseFragment(), "Food database")
            R.id.navRecipes -> Pair(RecipesFragment(), "Recipes & plans")
            R.id.navMl -> Pair(MLMonitorFragment(), "ML model")
            R.id.navSystem -> Pair(SystemHealthFragment(), "System health")
            else -> return false
        }

        loadFragment(fragment, title)
        return true
    }

    private fun loadFragment(fragment: Fragment, title: String) {
        binding.tvAdminPageTitle.text = title
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.adminFragmentContainer, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }
}
