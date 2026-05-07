package com.ayurbalance.ui.logfood

data class FoodNutrition(
    val caloriesPer100g: Int,
    val proteinG: Float,
    val carbsG: Float,
    val fatG: Float,
    val servingG: Int = 150
)

object FoodNutritionMapper {

    private val data = mapOf(
        "dal makhani"      to FoodNutrition(150, 8.5f, 18.0f, 4.5f, 200),
        "rajma"            to FoodNutrition(127, 8.7f, 22.8f, 0.5f, 180),
        "rajma curry"      to FoodNutrition(130, 8.2f, 21.0f, 2.1f, 180),
        "kali dal"         to FoodNutrition(143, 9.1f, 19.5f, 3.4f, 180),
        "palak paneer"     to FoodNutrition(180, 9.2f, 7.0f, 13.0f, 200),
        "paneer"           to FoodNutrition(265, 18.3f, 3.4f, 20.8f, 100),
        "biryani"          to FoodNutrition(220, 8.0f, 35.0f, 7.0f, 250),
        "chicken biryani"  to FoodNutrition(248, 13.5f, 32.0f, 8.2f, 250),
        "butter chicken"   to FoodNutrition(165, 16.5f, 9.0f, 8.0f, 200),
        "chana masala"     to FoodNutrition(134, 7.4f, 22.0f, 2.5f, 200),
        "aloo gobi"        to FoodNutrition(98, 2.8f, 15.5f, 3.2f, 200),
        "aloo"             to FoodNutrition(77, 2.0f, 17.5f, 0.1f, 150),
        "roti"             to FoodNutrition(297, 11.0f, 61.0f, 2.5f, 60),
        "chapati"          to FoodNutrition(297, 11.0f, 61.0f, 2.5f, 60),
        "naan"             to FoodNutrition(310, 9.0f, 54.0f, 6.5f, 80),
        "rice"             to FoodNutrition(130, 2.7f, 28.6f, 0.3f, 200),
        "white rice"       to FoodNutrition(130, 2.7f, 28.6f, 0.3f, 200),
        "brown rice"       to FoodNutrition(112, 2.6f, 23.5f, 0.9f, 200),
        "idli"             to FoodNutrition(58, 2.0f, 11.0f, 0.4f, 150),
        "dosa"             to FoodNutrition(168, 4.0f, 28.0f, 5.5f, 100),
        "sambar"           to FoodNutrition(57, 3.1f, 10.2f, 1.1f, 200),
        "rasam"            to FoodNutrition(35, 1.5f, 6.0f, 0.5f, 200),
        "upma"             to FoodNutrition(132, 4.0f, 21.0f, 3.5f, 180),
        "poha"             to FoodNutrition(165, 3.0f, 34.0f, 2.0f, 150),
        "paratha"          to FoodNutrition(260, 6.5f, 37.0f, 9.5f, 100),
        "aloo paratha"     to FoodNutrition(274, 6.0f, 40.0f, 10.0f, 120),
        "halwa"            to FoodNutrition(330, 4.5f, 52.0f, 12.0f, 100),
        "khichdi"          to FoodNutrition(120, 5.0f, 20.0f, 2.8f, 200),
        "curd"             to FoodNutrition(98, 11.0f, 3.4f, 4.3f, 150),
        "dahi"             to FoodNutrition(98, 11.0f, 3.4f, 4.3f, 150),
        "raita"            to FoodNutrition(55, 3.5f, 7.0f, 1.5f, 150),
        "lassi"            to FoodNutrition(105, 3.5f, 18.0f, 2.5f, 200),
        "chai"             to FoodNutrition(45, 1.5f, 7.0f, 1.2f, 150),
        "milk"             to FoodNutrition(61, 3.2f, 4.8f, 3.3f, 200),
        "paneer tikka"     to FoodNutrition(280, 18.0f, 6.5f, 20.0f, 150),
        "samosa"           to FoodNutrition(302, 5.5f, 39.0f, 14.0f, 70),
        "pakora"           to FoodNutrition(265, 7.0f, 32.0f, 12.5f, 100),
        "puri"             to FoodNutrition(340, 8.0f, 50.0f, 13.0f, 60),
        "sabzi"            to FoodNutrition(80, 2.5f, 12.0f, 2.5f, 150),
        "korma"            to FoodNutrition(198, 12.0f, 10.0f, 13.5f, 200),
        "mutter paneer"    to FoodNutrition(175, 10.0f, 10.5f, 11.0f, 200),
        "shahi paneer"     to FoodNutrition(230, 11.5f, 10.0f, 17.0f, 200),
        "gulab jamun"      to FoodNutrition(387, 4.8f, 62.0f, 14.0f, 80),
        "kheer"            to FoodNutrition(155, 4.5f, 26.0f, 4.5f, 150),
        "ladoo"            to FoodNutrition(460, 9.0f, 55.0f, 22.0f, 50),
        "jalebi"           to FoodNutrition(380, 2.0f, 79.0f, 7.0f, 60),
    )

    fun get(foodName: String): FoodNutrition {
        val key = foodName.lowercase().trim()
        data[key]?.let { return it }
        for ((k, v) in data) { if (key.contains(k) || k.contains(key)) return v }
        return FoodNutrition(
            caloriesPer100g = 150,
            proteinG = 5.0f,
            carbsG = 20.0f,
            fatG = 5.0f
        )
    }

    fun forServing(foodName: String): FoodNutrition {
        val base = get(foodName)
        val factor = base.servingG / 100f
        return base.copy(
            caloriesPer100g = (base.caloriesPer100g * factor).toInt(),
            proteinG = base.proteinG * factor,
            carbsG   = base.carbsG   * factor,
            fatG     = base.fatG     * factor
        )
    }
}
