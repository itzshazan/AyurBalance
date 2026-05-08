package com.ayurbalance.ui.logfood

import android.content.Context
import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

/**
 * On-device food classifier powered by Google ML Kit Image Labeling.
 * Requires no model file — the built-in model (~600 categories from Open Images)
 * recognises common Indian and global foods: biryani, curry, samosa, dosa, naan, etc.
 */
class FoodClassifier(private val context: Context) {

    companion object {
        private const val CONFIDENCE_THRESHOLD = 0.30f
        private const val TOP_K = 3
    }

    private val labeler = ImageLabeling.getClient(
        ImageLabelerOptions.Builder()
            .setConfidenceThreshold(CONFIDENCE_THRESHOLD)
            .build()
    )

    private var ready = false

    fun initialize(): Boolean {
        ready = true
        return true
    }

    /**
     * Async classification — result delivered on the calling thread via [callback].
     * Call [imageProxy.close()] BEFORE calling this (already done in LogFoodActivity).
     */
    fun classify(bitmap: Bitmap, callback: (List<FoodPrediction>) -> Unit) {
        val image = InputImage.fromBitmap(bitmap, 0)
        labeler.process(image)
            .addOnSuccessListener { labels ->
                // Prefer food-specific labels, fall back to top-3 overall
                val foodLabels = labels.filter { isFoodRelated(it.text) }
                    .sortedByDescending { it.confidence }
                    .take(TOP_K)

                val source = foodLabels.ifEmpty {
                    labels.sortedByDescending { it.confidence }.take(TOP_K)
                }

                callback(source.map { label ->
                    val name = label.text.lowercase().trim()
                    FoodPrediction(
                        name             = name,
                        confidence       = (label.confidence * 100).toInt().coerceIn(1, 99),
                        doshaTag         = DoshaFoodMapper.doshaTag(name),
                        caloriesPer100g  = DoshaFoodMapper.calories(name)
                    )
                })
            }
            .addOnFailureListener {
                callback(emptyList())
            }
    }

    private fun isFoodRelated(label: String): Boolean {
        val l = label.lowercase()
        return FOOD_TERMS.any { l.contains(it) }
    }

    private val FOOD_TERMS = listOf(
        "food", "dish", "meal", "cuisine", "recipe", "ingredient", "snack",
        // grains & breads
        "rice", "bread", "roti", "naan", "chapati", "paratha", "puri", "dosa",
        "idli", "appam", "grain", "flour", "cereal", "pasta", "noodle",
        // proteins
        "chicken", "meat", "fish", "egg", "paneer", "tofu", "lentil", "dal",
        "bean", "legume", "protein", "mutton", "lamb", "prawn", "shrimp",
        "pork", "beef", "turkey", "seafood",
        // individual fruits
        "apple", "banana", "mango", "orange", "grape", "strawberry",
        "watermelon", "lemon", "lime", "pineapple", "papaya", "guava",
        "pomegranate", "kiwi", "peach", "plum", "cherry", "coconut",
        "melon", "berr", "avocado", "fig", "date", "litchi", "jackfruit",
        // individual vegetables
        "potato", "tomato", "onion", "carrot", "spinach", "cauliflower",
        "broccoli", "cucumber", "eggplant", "aubergine", "pea", "corn",
        "pepper", "capsicum", "mushroom", "ginger", "garlic", "cabbage",
        "pumpkin", "okra", "ladyfinger", "radish", "beetroot", "zucchini",
        "asparagus", "celery", "leek", "turnip", "yam", "sweet potato",
        "bottle gourd", "bitter gourd", "drumstick", "lotus",
        // vegetables & fruits (generic)
        "vegetable", "veggie", "fruit", "salad", "greens", "leaf", "produce",
        // Indian dishes
        "curry", "biryani", "samosa", "pakora", "tikka", "korma", "masala",
        "sabzi", "chutney", "raita", "khichdi", "upma", "poha", "rajma",
        "chole", "bhatura", "kofta", "kebab", "dhokla", "thepla", "uttapam",
        "pesarattu", "payasam", "halwa", "kheer", "ladoo", "burfi", "jalebi",
        "bhel", "pani puri", "vada", "medu", "pongal", "rasam", "sambar",
        // soups & liquids
        "soup", "broth", "stew", "gravy", "sauce", "stock",
        // dairy
        "milk", "yogurt", "curd", "cream", "cheese", "butter", "ghee",
        // desserts & sweets
        "dessert", "sweet", "cake", "pastry", "chocolate", "ice cream",
        // cooking methods (help catch labelled dishes)
        "baked", "fried", "roasted", "steamed", "cooked", "grilled",
        "boiled", "sauteed", "stir", "tandoor"
    )

    fun isReady() = ready

    fun close() {
        labeler.close()
    }
}
