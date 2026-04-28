package com.ayurbalance.admin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.databinding.ActivityAdminLoginBinding

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminLoginBinding
    private val viewModel: AdminLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }

        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButton()
        observeViewModel()
    }

    private fun setupButton() {
        binding.btnAdminLogin.setOnClickListener {
            val email = binding.etAdminEmail.text.toString().trim()
            val password = binding.etAdminPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showError("Please enter email and password.")
                return@setOnClickListener
            }
            binding.tvLoginError.visibility = View.GONE
            viewModel.login(email, password)
        }
    }

    private fun observeViewModel() {
        viewModel.loginState.observe(this) { state ->
            when (state) {
                is AdminLoginViewModel.LoginState.Loading -> {
                    binding.progressLogin.visibility = View.VISIBLE
                    binding.btnAdminLogin.alpha = 0.6f
                    binding.btnAdminLogin.isEnabled = false
                }
                is AdminLoginViewModel.LoginState.Success -> {
                    startActivity(Intent(this, AdminDashboardActivity::class.java))
                    finish()
                }
                is AdminLoginViewModel.LoginState.Error -> {
                    binding.progressLogin.visibility = View.GONE
                    binding.btnAdminLogin.alpha = 1f
                    binding.btnAdminLogin.isEnabled = true
                    showError(state.message)
                }
                is AdminLoginViewModel.LoginState.Idle -> {
                    binding.progressLogin.visibility = View.GONE
                    binding.btnAdminLogin.alpha = 1f
                    binding.btnAdminLogin.isEnabled = true
                }
            }
        }
    }

    private fun showError(msg: String) {
        binding.tvLoginError.text = msg
        binding.tvLoginError.visibility = View.VISIBLE
    }
}
