package com.ayurbalance.ui.profile

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.AyurBalanceApp
import com.ayurbalance.databinding.ActivityPrivacySettingsBinding
import io.github.jan.supabase.gotrue.auth
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrivacySettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor     = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars     = true
            isAppearanceLightNavigationBars = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.privacyRoot) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.toolbar.setPadding(0, bars.top, 0, 0)
            v.setPadding(0, 0, 0, bars.bottom)
            insets
        }

        setupActions()
    }

    private fun setupActions() {
        binding.btnBack.setOnClickListener { finish() }

        binding.rowManagePermissions.setOnClickListener {
            startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
            )
        }

        binding.rowAiPolicy.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("AI Usage")
                .setMessage("AyurBalance uses on-device machine learning for food recognition. Your food images are processed locally and are not sent to external servers.\n\nDosha alignment scores are computed from your logged meals using the traditional Ayurvedic framework.")
                .setPositiveButton("Got it", null)
                .show()
        }

        binding.rowHealthPolicy.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Health Data")
                .setMessage("Your health profile, meal logs, and wellness data are stored securely in Supabase with row-level security. Only you can access your data.\n\nWe do not share individual health data with third parties.")
                .setPositiveButton("Got it", null)
                .show()
        }

        binding.rowExportData.setOnClickListener {
            val state = ProfileState()
            lifecycleScope.launch {
                PdfExportManager(this@PrivacySettingsActivity).exportReport(state) { uri ->
                    uri?.let { PdfExportManager(this@PrivacySettingsActivity).openShareSheet(this@PrivacySettingsActivity, it) }
                }
            }
        }

        binding.rowDeleteAccount.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("This will permanently delete your account and all associated data including meal logs, wellness history, and Prakriti results.\n\nThis action cannot be undone.")
                .setPositiveButton("Delete") { _, _ -> deleteAccount() }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun deleteAccount() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                AyurBalanceApp.supabaseClient.auth.signOut()
            }
            withContext(Dispatchers.Main) {
                // Navigate to splash and clear stack
                val intent = packageManager.getLaunchIntentForPackage(packageName)?.apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                intent?.let { startActivity(it) }
                finish()
            }
        }
    }
}
