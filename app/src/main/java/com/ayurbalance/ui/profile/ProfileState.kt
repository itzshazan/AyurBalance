package com.ayurbalance.ui.profile

data class ProfileState(
    val userId: String = "",
    val displayName: String = "",
    val email: String = "",
    val age: Int = 0,
    val gender: String = "",
    val prakritiType: String = "",
    val constitutionLabel: String = "",
    val vataPct: Int = 0,
    val pittaPct: Int = 0,
    val kaphaPct: Int = 0,
    val dietType: String = "",
    val healthConditions: List<String> = emptyList(),
    val goal: String = "",
    val activityLevel: String = "",
    val sleepHours: Double = 0.0,
    val streakDays: Int = 0,
    val mealReminders: Boolean = true,
    val hydrationReminders: Boolean = false,
    val dinacharyaReminders: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
) {
    val initials: String get() {
        val parts = displayName.trim().split(" ")
        return when {
            parts.size >= 2 -> "${parts[0].firstOrNull() ?: ""}${parts[1].firstOrNull() ?: ""}".uppercase()
            parts.size == 1 && parts[0].isNotEmpty() -> parts[0].take(2).uppercase()
            else -> "A"
        }
    }

    val dominantDosha: String get() = when {
        pittaPct >= vataPct && pittaPct >= kaphaPct -> "PITTA"
        vataPct >= pittaPct && vataPct >= kaphaPct  -> "VATA"
        else                                         -> "KAPHA"
    }
}
