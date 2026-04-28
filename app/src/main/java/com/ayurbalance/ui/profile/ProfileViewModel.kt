package com.ayurbalance.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.AyurBalanceApp
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

/**
 * ViewModel for multi-step Health Profile form.
 *
 * Holds all user-entered metrics across the 4-step flow.
 * Data is kept in memory during the flow and persisted
 * to Supabase once all steps are complete.
 */
class ProfileViewModel : ViewModel() {

    // ──────────────────────────────────────────────
    //  Form State
    // ──────────────────────────────────────────────

    private val _age = MutableLiveData<Int?>(null)
    val age: LiveData<Int?> = _age

    private val _gender = MutableLiveData("female")
    val gender: LiveData<String> = _gender

    private val _height = MutableLiveData<Double?>(null)
    val height: LiveData<Double?> = _height

    private val _heightUnit = MutableLiveData("cm")
    val heightUnit: LiveData<String> = _heightUnit

    private val _weight = MutableLiveData<Double?>(null)
    val weight: LiveData<Double?> = _weight

    private val _weightUnit = MutableLiveData("kg")
    val weightUnit: LiveData<String> = _weightUnit

    // ──────────────────────────────────────────────
    //  Validation
    // ──────────────────────────────────────────────

    private val _isStep1Valid = MutableLiveData(false)
    val isStep1Valid: LiveData<Boolean> = _isStep1Valid

    // ──────────────────────────────────────────────
    //  Setters
    // ──────────────────────────────────────────────

    fun setAge(value: Int?) {
        _age.value = value
        validateStep1()
    }

    fun setGender(value: String) {
        _gender.value = value
        validateStep1()
    }

    fun setHeight(value: Double?) {
        _height.value = value
        validateStep1()
    }

    fun setHeightUnit(unit: String) {
        _heightUnit.value = unit
    }

    fun setWeight(value: Double?) {
        _weight.value = value
        validateStep1()
    }

    fun setWeightUnit(unit: String) {
        _weightUnit.value = unit
    }

    // ──────────────────────────────────────────────
    //  Step 1 Validation Logic
    // ──────────────────────────────────────────────

    private fun validateStep1() {
        val ageOk = (_age.value ?: 0) > 0
        val genderOk = !_gender.value.isNullOrEmpty()
        val heightOk = (_height.value ?: 0.0) > 0
        val weightOk = (_weight.value ?: 0.0) > 0

        _isStep1Valid.value = ageOk && genderOk && heightOk && weightOk
    }

    // ──────────────────────────────────────────────
    //  Data Export (for backend sync)
    // ──────────────────────────────────────────────

    /**
     * Returns Step 1 data as a map for serialization.
     */
    fun getStep1Data(): Map<String, Any?> = mapOf(
        "age" to _age.value,
        "gender" to _gender.value,
        "height" to _height.value,
        "height_unit" to _heightUnit.value,
        "weight" to _weight.value,
        "weight_unit" to _weightUnit.value
    )

    // ══════════════════════════════════════════════
    //  STEP 2 — Lifestyle & Habits
    // ══════════════════════════════════════════════

    private val _activityLevel = MutableLiveData("sedentary")
    val activityLevel: LiveData<String> = _activityLevel

    private val _sleepHours = MutableLiveData(7.5)
    val sleepHours: LiveData<Double> = _sleepHours

    private val _sleepQuality = MutableLiveData("average")
    val sleepQuality: LiveData<String> = _sleepQuality

    private val _isStep2Valid = MutableLiveData(true) // defaults are valid
    val isStep2Valid: LiveData<Boolean> = _isStep2Valid

    fun setActivityLevel(level: String) {
        _activityLevel.value = level
        validateStep2()
    }

    fun setSleepHours(hours: Double) {
        _sleepHours.value = hours
        validateStep2()
    }

    fun setSleepQuality(quality: String) {
        _sleepQuality.value = quality
        validateStep2()
    }

    private fun validateStep2() {
        val actOk = !_activityLevel.value.isNullOrEmpty()
        val sleepOk = (_sleepHours.value ?: 0.0) > 0
        val qualityOk = !_sleepQuality.value.isNullOrEmpty()
        _isStep2Valid.value = actOk && sleepOk && qualityOk
    }

    fun getStep2Data(): Map<String, Any?> = mapOf(
        "activity_level" to _activityLevel.value,
        "sleep_hours" to _sleepHours.value,
        "sleep_quality" to _sleepQuality.value
    )

    // ══════════════════════════════════════════════
    //  STEP 3 — Diet & Health
    // ══════════════════════════════════════════════

    private val _dietType = MutableLiveData("vegetarian")
    val dietType: LiveData<String> = _dietType

    private val _healthConditions = MutableLiveData<Set<String>>(emptySet())
    val healthConditions: LiveData<Set<String>> = _healthConditions

    private val _isStep3Valid = MutableLiveData(true) // defaults are valid
    val isStep3Valid: LiveData<Boolean> = _isStep3Valid

    fun setDietType(type: String) {
        _dietType.value = type
        validateStep3()
    }

    fun toggleHealthCondition(condition: String) {
        val current = _healthConditions.value?.toMutableSet() ?: mutableSetOf()
        if (current.contains(condition)) {
            current.remove(condition)
        } else {
            current.add(condition)
        }
        _healthConditions.value = current
        validateStep3()
    }

    private fun validateStep3() {
        val dietOk = !_dietType.value.isNullOrEmpty()
        _isStep3Valid.value = dietOk
    }

    fun getStep3Data(): Map<String, Any?> = mapOf(
        "diet_type" to _dietType.value,
        "health_conditions" to _healthConditions.value?.toList()
    )

    // ══════════════════════════════════════════════
    //  STEP 4 — Goals & Focus
    // ══════════════════════════════════════════════

    private val _goal = MutableLiveData<String>()
    val goal: LiveData<String> = _goal

    private val _isStep4Valid = MutableLiveData(false)
    val isStep4Valid: LiveData<Boolean> = _isStep4Valid

    fun setGoal(selectedGoal: String) {
        _goal.value = selectedGoal
        validateStep4()
    }

    private fun validateStep4() {
        val goalOk = !_goal.value.isNullOrEmpty()
        _isStep4Valid.value = goalOk
    }

    // ══════════════════════════════════════════════
    //  FINAL SUBMISSION (Supabase)
    // ══════════════════════════════════════════════

    sealed class SubmissionState {
        object Idle : SubmissionState()
        object Loading : SubmissionState()
        object Success : SubmissionState()
        data class Error(val message: String) : SubmissionState()
    }

    private val _submissionState = MutableLiveData<SubmissionState>(SubmissionState.Idle)
    val submissionState: LiveData<SubmissionState> = _submissionState

    fun submitProfile() {
        // Collect all data into the model (userId injected in coroutine)
        val profile = com.ayurbalance.data.models.UserProfile(
            age = _age.value ?: 0,
            gender = _gender.value ?: "unknown",
            height = _height.value ?: 0.0,
            weight = _weight.value ?: 0.0,
            activityLevel = _activityLevel.value ?: "sedentary",
            sleepHours = _sleepHours.value ?: 0.0,
            sleepQuality = _sleepQuality.value ?: "average",
            dietType = _dietType.value ?: "vegetarian",
            healthConditions = _healthConditions.value?.toList() ?: emptyList(),
            goal = _goal.value ?: "general_wellness"
        )

        _submissionState.value = SubmissionState.Loading

        viewModelScope.launch {
            try {
                val userId = AyurBalanceApp.supabaseClient.auth.currentUserOrNull()?.id ?: ""
                val profileWithId = profile.copy(userId = userId)

                // Upsert so re-running setup doesn't create duplicate rows
                AyurBalanceApp.supabaseClient
                    .from("user_profiles")
                    .upsert(value = profileWithId, onConflict = "user_id")

                _submissionState.postValue(SubmissionState.Success)
            } catch (e: Exception) {
                e.printStackTrace()
                _submissionState.postValue(SubmissionState.Error("Failed to save profile: ${e.message}"))
            }
        }
    }
}
