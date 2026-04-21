package com.ayurbalance.ui.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ayurbalance.R
import com.ayurbalance.databinding.ActivityOtpVerificationBinding

/**
 * OTP Verification Screen — "Verify your spirit."
 *
 * Features:
 *   - 6-digit OTP entry with auto-advance & backspace handling
 *   - Full paste support
 *   - Supabase OTP verification
 *   - Countdown timer for resend (3 minutes)
 *   - Premium entry animations
 *
 * Navigation:
 *   Verify success → Dashboard
 *   Back → Signup screen
 */
class OtpVerificationActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EMAIL = "extra_email"
        private const val COUNTDOWN_MILLIS = 180_000L // 3 minutes
        private const val TICK_INTERVAL = 1_000L
    }

    private lateinit var binding: ActivityOtpVerificationBinding
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var otpFields: List<EditText>
    private var countdownTimer: CountDownTimer? = null
    private var userEmail: String = ""

    // ──────────────────────────────────────────────
    //  Lifecycle
    // ──────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureEdgeToEdge()

        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userEmail = intent.getStringExtra(EXTRA_EMAIL) ?: ""

        otpFields = listOf(
            binding.etOtp1, binding.etOtp2, binding.etOtp3,
            binding.etOtp4, binding.etOtp5, binding.etOtp6,
            binding.etOtp7, binding.etOtp8
        )

        applyWindowInsets()
        setupOtpInputs()
        setupButtons()
        observeAuthState()
        startCountdownTimer()
        startEntryAnimations()
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer?.cancel()
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.otpRoot) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, insets.bottom)
            windowInsets
        }
    }

    // ──────────────────────────────────────────────
    //  OTP Input Logic
    // ──────────────────────────────────────────────

    private fun setupOtpInputs() {
        otpFields.forEachIndexed { index, editText ->
            // Focus state — green highlight
            editText.setOnFocusChangeListener { v, hasFocus ->
                v.setBackgroundResource(
                    if (hasFocus) R.drawable.bg_otp_box_focused
                    else R.drawable.bg_otp_box
                )
            }

            // Auto-advance to next field
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
                override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.length == 1 && index < otpFields.lastIndex) {
                        otpFields[index + 1].requestFocus()
                    }
                    // Handle paste: user pastes full 6-digit code into first field
                    if (s != null && s.length > 1 && index == 0) {
                        val code = s.toString().take(8)
                        distributePastedCode(code)
                    }
                }
            })

            // Backspace — move to previous field
            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL &&
                    event.action == KeyEvent.ACTION_DOWN &&
                    editText.text.isNullOrEmpty() &&
                    index > 0
                ) {
                    otpFields[index - 1].apply {
                        requestFocus()
                        setText("")
                    }
                    return@setOnKeyListener true
                }
                false
            }
        }

        // Auto-focus first field
        otpFields[0].requestFocus()
    }

    /**
     * Distributes a pasted code across all 6 OTP input fields.
     */
    private fun distributePastedCode(code: String) {
        code.forEachIndexed { i, char ->
            if (i < otpFields.size) {
                otpFields[i].setText(char.toString())
            }
        }
        // Focus last filled field
        val focusIndex = (code.length - 1).coerceIn(0, otpFields.lastIndex)
        otpFields[focusIndex].requestFocus()
        otpFields[focusIndex].setSelection(1)
    }

    /**
     * Collects the 6-digit code from all fields.
     */
    private fun getOtpCode(): String {
        return otpFields.joinToString("") { it.text.toString() }
    }

    /**
     * Resets all OTP fields and error states.
     */
    private fun clearOtpFields() {
        otpFields.forEach {
            it.setText("")
            it.setBackgroundResource(R.drawable.bg_otp_box)
        }
        otpFields[0].requestFocus()
    }

    /**
     * Shows error state on all OTP boxes.
     */
    private fun showOtpError() {
        otpFields.forEach {
            it.setBackgroundResource(R.drawable.bg_otp_box_error)
        }
    }

    // ──────────────────────────────────────────────
    //  Button Handlers
    // ──────────────────────────────────────────────

    private fun setupButtons() {
        binding.btnVerify.setOnClickListener {
            val code = getOtpCode()
            if (code.length < 8) {
                Toast.makeText(this, "Please enter the full 8-digit code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.verifyOtp(userEmail, code)
        }

        binding.tvResend.setOnClickListener {
            if (binding.tvResend.isEnabled) {
                viewModel.resendOtp(userEmail)
                clearOtpFields()
                startCountdownTimer()
                Toast.makeText(this, getString(R.string.otp_resend_success), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }
    }

    // ──────────────────────────────────────────────
    //  Auth State Observer
    // ──────────────────────────────────────────────

    private fun observeAuthState() {
        viewModel.authState.observe(this) { state ->
            when (state) {
                is AuthViewModel.AuthState.Loading -> {
                    binding.btnVerify.isEnabled = false
                    binding.btnVerify.alpha = 0.6f
                    binding.progressBar.visibility = View.VISIBLE
                }
                is AuthViewModel.AuthState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnVerify.isEnabled = true
                    binding.btnVerify.alpha = 1f
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
                    binding.btnVerify.isEnabled = true
                    binding.btnVerify.alpha = 1f
                    showOtpError()
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                    viewModel.resetState()
                }
                is AuthViewModel.AuthState.Idle -> { /* no-op */ }
            }
        }
    }

    // ──────────────────────────────────────────────
    //  Countdown Timer
    // ──────────────────────────────────────────────

    private fun startCountdownTimer() {
        binding.tvResend.isEnabled = false
        binding.tvResend.alpha = 0.5f

        countdownTimer?.cancel()

        countdownTimer = object : CountDownTimer(COUNTDOWN_MILLIS, TICK_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val formatted = String.format("%02d:%02d", minutes, seconds)
                binding.tvTimer.text = getString(R.string.resend_timer, formatted)
            }

            override fun onFinish() {
                binding.tvTimer.text = getString(R.string.resend_timer, "00:00")
                binding.tvResend.isEnabled = true
                binding.tvResend.alpha = 1f
            }
        }.start()
    }

    // ──────────────────────────────────────────────
    //  Entry Animation Choreography
    // ──────────────────────────────────────────────

    private fun startEntryAnimations() {
        val standard = DecelerateInterpolator()
        val overshoot = OvershootInterpolator(1.1f)

        with(binding) {

            // ① Top bar — fade-in
            val topViews = listOf<View>(btnBack, tvBrand)
            topViews.forEach { view ->
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .setStartDelay(100)
                    .setDuration(400)
                    .start()
            }

            // ② Card — slide-up + scale
            otpCard.alpha = 0f
            otpCard.translationY = 30f
            otpCard.scaleX = 0.97f
            otpCard.scaleY = 0.97f
            otpCard.animate()
                .alpha(1f)
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(250)
                .setDuration(600)
                .setInterpolator(overshoot)
                .start()

            // ③ Timer + resend — fade-in
            val bottomViews = listOf<View>(timerRow, tvResend)
            bottomViews.forEachIndexed { i, view ->
                view.alpha = 0f
                view.animate()
                    .alpha(if (i == 1) 0.5f else 1f) // resend starts dimmed
                    .setStartDelay(700L + i * 120L)
                    .setDuration(400)
                    .start()
            }

            // ④ Footer — fade-in
            tvFooter.alpha = 0f
            tvFooter.animate()
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(350)
                .start()
        }
    }
}
