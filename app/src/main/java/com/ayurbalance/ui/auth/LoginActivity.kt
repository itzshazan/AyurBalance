package com.ayurbalance.ui.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityLoginBinding

/**
 * Login Screen — "Welcome Back"
 *
 * Features:
 *   - Email + password login via Supabase
 *   - Password visibility toggle
 *   - Forgot Password link (placeholder)
 *   - Social login buttons (Google/Apple placeholders)
 *   - Create Account link → SignupActivity
 *   - Premium entry animations
 *
 * Navigation:
 *   Login success → Dashboard
 *   Create Account → SignupActivity
 *   Forgot Password → Reset flow (future)
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()
    private var isPasswordVisible = false

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupInputFocusListeners()
        setupPasswordToggle()
        setupButtons()
        observeAuthState()
        startEntryAnimations()

        // Handle OAuth deep link if returning from browser
        handleOAuthDeepLink(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleOAuthDeepLink(it) }
    }

    /**
     * Pass the deep link intent to Supabase so the SDK can extract
     * the access token from the callback URL and establish a session.
     */
    private fun handleOAuthDeepLink(intent: Intent) {
        val uri = intent.data ?: return
        if (uri.scheme == "ayurbalance" && uri.host == "login-callback") {
            // The OAuth token comes as a URL fragment (#access_token=...&refresh_token=...)
            // Parse it and let the ViewModel import the session
            val fragment = uri.fragment ?: uri.encodedFragment ?: ""
            viewModel.handleOAuthCallback(fragment)
        }
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.loginRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Input Focus States
    // ──────────────────────────────────────────────

    private fun setupInputFocusListeners() {
        val inputs = listOf(binding.etEmail, binding.etPassword)
        inputs.forEach { editText ->
            editText.setOnFocusChangeListener { v, hasFocus ->
                v.setBackgroundResource(
                    if (hasFocus) R.drawable.bg_input_field_focused
                    else R.drawable.bg_input_field
                )
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Password Visibility Toggle
    // ──────────────────────────────────────────────

    private fun setupPasswordToggle() {
        binding.ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                binding.etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.ivTogglePassword.setImageResource(R.drawable.ic_eye_off)
            } else {
                binding.etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.ivTogglePassword.setImageResource(R.drawable.ic_eye)
            }

            // Keep cursor at end
            binding.etPassword.setSelection(binding.etPassword.text?.length ?: 0)
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnLogin.setOnClickListener {
            clearErrors()
            if (validateInputs()) {
                viewModel.signIn(
                    email = binding.etEmail.text.toString().trim(),
                    password = binding.etPassword.text.toString()
                )
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            // TODO: Navigate to Forgot Password screen
            Toast.makeText(this, "Reset password flow coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.tvCreateAccount.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        binding.btnGoogle.setOnClickListener {
            viewModel.signInWithGoogle()
        }

        binding.btnMicrosoft.setOnClickListener {
            Toast.makeText(this, "Microsoft sign-in requires Azure setup", Toast.LENGTH_SHORT).show()
        }
    }

    // ──────────────────────────────────────────────
    //  Validation
    // ──────────────────────────────────────────────

    private fun validateInputs(): Boolean {
        var valid = true

        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tvEmailError.visibility = View.VISIBLE
            binding.etEmail.setBackgroundResource(R.drawable.bg_input_field_error)
            valid = false
        }

        if (binding.etPassword.text.isNullOrEmpty()) {
            binding.tvPasswordError.text = getString(R.string.error_password_short)
            binding.tvPasswordError.visibility = View.VISIBLE
            binding.etPassword.setBackgroundResource(R.drawable.bg_input_field_error)
            valid = false
        }

        return valid
    }

    private fun clearErrors() {
        binding.tvEmailError.visibility = View.GONE
        binding.tvPasswordError.visibility = View.GONE
        binding.etEmail.setBackgroundResource(R.drawable.bg_input_field)
        binding.etPassword.setBackgroundResource(R.drawable.bg_input_field)
    }

    // ──────────────────────────────────────────────
    //  Auth State Observer
    // ──────────────────────────────────────────────

    private fun observeAuthState() {
        viewModel.authState.observe(this) { state ->
            when (state) {
                is AuthViewModel.AuthState.Loading -> {
                    binding.btnLogin.isEnabled = false
                    binding.btnLogin.alpha = 0.6f
                    binding.progressBar.visibility = View.VISIBLE
                }
                is AuthViewModel.AuthState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.alpha = 1f
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()

                    // Navigate to Health Profile Setup
                    val intent = Intent(this, com.ayurbalance.ui.profile.ProfileStep1Activity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    @Suppress("DEPRECATION")
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
                is AuthViewModel.AuthState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.alpha = 1f
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                    viewModel.resetState()
                }
                is AuthViewModel.AuthState.Idle -> { /* no-op */ }
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.1f)

        with(binding) {

            // ① Brand — fade-in
            tvBrand.alpha = 0f
            tvBrand.animate()
                .alpha(1f)
                .setStartDelay(100)
                .setDuration(400)
                .start()

            // ② Heading — slide-down + fade
            headingRow.alpha = 0f
            headingRow.translationY = -15f
            headingRow.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(200)
                .setDuration(450)
                .setInterpolator(standard)
                .start()

            // ③ Subtitle — fade-in
            tvSubtitle.alpha = 0f
            tvSubtitle.animate()
                .alpha(1f)
                .setStartDelay(350)
                .setDuration(400)
                .start()

            // ④ Login card — slide-up + scale
            loginCard.alpha = 0f
            loginCard.translationY = 30f
            loginCard.scaleX = 0.97f
            loginCard.scaleY = 0.97f
            loginCard.animate()
                .alpha(1f)
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(450)
                .setDuration(600)
                .setInterpolator(overshoot)
                .start()

            // ⑤ Divider + social buttons — fade-in
            val socialViews = listOf<View>(dividerRow, socialRow)
            socialViews.forEachIndexed { i, view ->
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .setStartDelay(900L + i * 120L)
                    .setDuration(400)
                    .start()
            }

            // ⑥ Bottom text — fade-in
            bottomRow.alpha = 0f
            bottomRow.animate()
                .alpha(1f)
                .setStartDelay(1100)
                .setDuration(350)
                .start()
        }
    }
}
