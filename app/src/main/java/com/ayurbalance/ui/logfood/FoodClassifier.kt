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
        "idli", "appam", "grain", "flour", "cereal",
        // proteins
        "chicken", "meat", "fish", "egg", "paneer", "tofu", "lentil", "dal",
        "bean", "legume", "protein",
        // vegetables & fruits
        "vegetable", "veggie", "fruit", "salad", "greens", "leaf",
        // Indian dishes
        "curry", "biryani", "samosa", "pakora", "tikka", "korma", "masala",
        "sabzi", "chutney", "raita", "khichdi", "upma", "poha",
        // soups & liquids
        "soup", "broth", "stew", "gravy", "sauce",
        // dairy
        "milk", "yogurt", "curd", "cream", "cheese",
        // desserts
        "dessert", "sweet", "halwa", "kheer", "payasam",
        // generic
        "baked", "fried", "roasted", "steamed", "cooked"
    )

    fun isReady() = ready

    fun close() {
        labeler.close()
    }
}
