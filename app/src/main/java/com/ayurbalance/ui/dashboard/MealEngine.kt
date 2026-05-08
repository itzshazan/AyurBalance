package com.ayurbalance.ui.dashboard

data class MealRecommendation(
    val label: String,
    val time: String,
    val name: String,
    val calories: Int,
    val doshaNote: String,
    val emoji: String,
    val carbs: Int,
    val protein: Int,
    val fats: Int
)

data class ImbalanceAlert(
    val title: String,
    val description: String
)

object MealEngine {

    fun recommend(dominantDosha: String, hour: Int): MealRecommendation {
        val (label, time) = mealSlot(hour)
        return when (dominantDosha) {
            "VATA" -> when (label) {
                "Breakfast" -> MealRecommendation(label, time, "Warm oat porridge", 280, "Vata-pacifying", "🥣", 42, 9, 7)
                "Lunch"     -> MealRecommendation(label, time, "Root vegetable khichdi", 340, "Vata-pacifying", "🍲", 52, 13, 8)
                "Snack"     -> MealRecommendation(label, time, "Warm sesame milk", 160, "Vata-pacifying", "🥛", 18, 5, 7)
                else        -> MealRecommendation(label, time, "Spiced lentil soup", 300, "Vata-pacifying", "🍵", 38, 14, 9)
            }
            "PITTA" -> when (label) {
                "Breakfast" -> MealRecommendation(label, time, "Coconut banana smoothie", 260, "Pitta-pacifying", "🥥", 38, 6, 9)
                "Lunch"     -> MealRecommendation(label, time, "Moong dal khichdi", 320, "Pitta-pacifying", "🍛", 42, 14, 8)
                "Snack"     -> MealRecommendation(label, time, "Cucumber mint raita", 140, "Pitta-pacifying", "🥒", 12, 6, 4)
                else        -> MealRecommendation(label, time, "Coconut rice with coriander", 290, "Pitta-pacifying", "🍚", 46, 7, 6)
            }
            else -> when (label) {
                "Breakfast" -> MealRecommendation(label, time, "Ginger lemon herbal tea", 40, "Kapha-pacifying", "🍵", 8, 1, 0)
                "Lunch"     -> MealRecommendation(label, time, "Barley vegetable soup", 240, "Kapha-pacifying", "🥗", 38, 10, 4)
                "Snack"     -> MealRecommendation(label, time, "Roasted seeds mix", 180, "Kapha-pacifying", "🌻", 12, 8, 11)
                else        -> MealRecommendation(label, time, "Light mung bean stir-fry", 200, "Kapha-pacifying", "🫘", 28, 12, 3)
            }
        }
    }

    fun defaultMeal() = MealRecommendation("Lunch", "1:00 PM", "Moong dal khichdi", 320, "Pitta-pacifying", "🍛", 42, 14, 8)

    private fun mealSlot(hour: Int): Pair<String, String> = when {
        hour < 10 -> "Breakfast" to "8:00 AM"
        hour < 13 -> "Lunch"     to "12:30 PM"
        hour < 17 -> "Snack"     to "4:00 PM"
        else       -> "Dinner"   to "7:00 PM"
    }
}
