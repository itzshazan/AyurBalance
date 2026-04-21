package com.ayurbalance.ui.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
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
import com.ayurbalance.databinding.ActivitySignupBinding

/**
 * Sign Up (Create Account) Screen.
 *
 * Features:
 *   - 4 input fields with inline validation
 *   - Terms checkbox with clickable links
 *   - Supabase Auth integration via [AuthViewModel]
 *   - Loading/error/success state management
 *   - Premium entry animations
 *
 * Navigation:
 *   Sign Up success → OTP Verification (future) / Dashboard
 *   Login link → Login screen (future)
 */
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel: AuthViewModel by viewModels()

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupInputFocusListeners()
        setupTermsText()
        setupButtons()
        observeAuthState()
        startEntryAnimations()
    }

    // ──────────────────────────────────────────────
    //  Edge-to-Edge Window Configuration
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.signupRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  Input Focus — visual feedback
    // ──────────────────────────────────────────────

    private fun setupInputFocusListeners() {
        val inputs = listOf(
            binding.etFullName, binding.etEmail,
            binding.etPassword, binding.etConfirmPassword
        )

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
    //  Terms — clickable spans
    // ──────────────────────────────────────────────

    private fun setupTermsText() {
        val full = getString(R.string.terms_agreement)
        val spannable = SpannableString(full)

        // "Terms of Service"
        val termsStart = full.indexOf("Terms of Service")
        if (termsStart >= 0) {
            val termsEnd = termsStart + "Terms of Service".length
            spannable.setSpan(
                createClickableSpan { /* Open Terms URL */ },
                termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        // "Privacy Policy"
        val privacyStart = full.indexOf("Privacy Policy")
        if (privacyStart >= 0) {
            val privacyEnd = privacyStart + "Privacy Policy".length
            spannable.setSpan(
                createClickableSpan { /* Open Privacy URL */ },
                privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.tvTermsText.text = spannable
        binding.tvTermsText.movementMethod = LinkMovementMethod.getInstance()
        binding.tvTermsText.highlightColor = Color.TRANSPARENT
    }

    private fun createClickableSpan(onClick: () -> Unit): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) = onClick()
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = getColor(R.color.text_status_brown)
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnSignUp.setOnClickListener {
            clearErrors()
            if (validateInputs()) {
                viewModel.signUp(
                    email = binding.etEmail.text.toString().trim(),
                    password = binding.etPassword.text.toString(),
                    fullName = binding.etFullName.text.toString().trim()
                )
            }
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    // ──────────────────────────────────────────────
    //  Validation
    // ──────────────────────────────────────────────

    private fun validateInputs(): Boolean {
        var valid = true

        // Name
        if (binding.etFullName.text.isNullOrBlank()) {
            binding.tvNameError.visibility = View.VISIBLE
            binding.etFullName.setBackgroundResource(R.drawable.bg_input_field_error)
            valid = false
        }

        // Email
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tvEmailError.visibility = View.VISIBLE
            binding.etEmail.setBackgroundResource(R.drawable.bg_input_field_error)
            valid = false
        }

        // Password length
        val password = binding.etPassword.text.toString()
        if (password.length < 6) {
            binding.tvPasswordError.visibility = View.VISIBLE
            binding.etPassword.setBackgroundResource(R.drawable.bg_input_field_error)
            valid = false
        }

        // Confirm match
        val confirm = binding.etConfirmPassword.text.toString()
        if (confirm != password) {
            binding.tvConfirmError.visibility = View.VISIBLE
            binding.etConfirmPassword.setBackgroundResource(R.drawable.bg_input_field_error)
            valid = false
        }

        // Terms
        if (!binding.cbTerms.isChecked) {
            binding.tvTermsError.visibility = View.VISIBLE
            valid = false
        }

        return valid
    }

    private fun clearErrors() {
        binding.tvNameError.visibility = View.GONE
        binding.tvEmailError.visibility = View.GONE
        binding.tvPasswordError.visibility = View.GONE
        binding.tvConfirmError.visibility = View.GONE
        binding.tvTermsError.visibility = View.GONE

        val fields = listOf(
            binding.etFullName, binding.etEmail,
            binding.etPassword, binding.etConfirmPassword
        )
        fields.forEach { it.setBackgroundResource(R.drawable.bg_input_field) }
    }

    // ──────────────────────────────────────────────
    //  Auth State Observer
    // ──────────────────────────────────────────────

    private fun observeAuthState() {
        viewModel.authState.observe(this) { state ->
            when (state) {
                is AuthViewModel.AuthState.Loading -> {
                    binding.btnSignUp.isEnabled = false
                    binding.btnSignUp.alpha = 0.6f
                    binding.progressBar.visibility = View.VISIBLE
                }
                is AuthViewModel.AuthState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignUp.isEnabled = true
                    binding.btnSignUp.alpha = 1f
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()

                    // Navigate to OTP Verification screen
                    val intent = Intent(this, OtpVerificationActivity::class.java).apply {
                        putExtra(OtpVerificationActivity.EXTRA_EMAIL,
                            binding.etEmail.text.toString().trim())
                    }
                    startActivity(intent)
                    @Suppress("DEPRECATION")
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
                is AuthViewModel.AuthState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignUp.isEnabled = true
                    binding.btnSignUp.alpha = 1f
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

            // ① Brand row — fade-in
            brandRow.alpha = 0f
            brandRow.animate()
                .alpha(1f)
                .setStartDelay(100)
                .setDuration(400)
                .start()

            // ② Heading + subtitle — slide-down + fade
            val headViews = listOf<View>(tvHeading, tvSubtitle)
            headViews.forEachIndexed { i, view ->
                view.alpha = 0f
                view.translationY = -15f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(200L + i * 100L)
                    .setDuration(450)
                    .setInterpolator(standard)
                    .start()
            }

            // ③ Input fields — staggered slide-up + fade
            val fieldGroups = listOf<View>(
                tvNameLabel, etFullName,
                tvEmailLabel, etEmail,
                tvPasswordLabel, etPassword,
                tvConfirmLabel, etConfirmPassword
            )
            fieldGroups.forEachIndexed { i, view ->
                view.alpha = 0f
                view.translationY = 20f
                view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(380L + i * 60L)
                    .setDuration(400)
                    .setInterpolator(standard)
                    .start()
            }

            // ④ Terms checkbox — fade-in
            termsRow.alpha = 0f
            termsRow.animate()
                .alpha(1f)
                .setStartDelay(900)
                .setDuration(350)
                .start()

            // ⑤ Sign Up button — scale-in
            btnSignUp.alpha = 0f
            btnSignUp.scaleX = 0.95f
            btnSignUp.scaleY = 0.95f
            btnSignUp.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(1000)
                .setDuration(400)
                .setInterpolator(overshoot)
                .start()

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
