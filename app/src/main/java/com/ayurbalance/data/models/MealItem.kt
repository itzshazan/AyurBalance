package com.ayurbalance.data.models

import kotlinx.serialization.Serializable

enum class MealType(val displayName: String, val time: String, val icon: String) {
    BREAKFAST("Breakfast", "8:00 AM",  "☀"),
    LUNCH    ("Lunch",     "1:00 PM",  "⊕"),
    SNACK    ("Snack",     "4:00 PM",  "✿"),
    DINNER   ("Dinner",    "7:00 PM",  "☽")
}

@Serializable
data class MealItem(
    val id: String,
    val name: String,
    val type: String,           // MealType.name
    val calories: Int,
    val prepTimeMin: Int,
    val doshaCompatible: List<String>,   // ["VATA","PITTA","KAPHA"] or subset
    val seasonCompatible: List<String>,  // Ritu names
    val swappable: Boolean = true
) {
    val mealType: MealType get() = MealType.valueOf(type)
}

@Serializable
data class SeasonalProtocol(
    val ritu: String,
    val displayName: String,
    val guidance: String,
    val shortDesc: String,
    val recommendedFoods: List<String>,
    val avoidFoods: List<String>
)
