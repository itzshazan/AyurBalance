package com.ayurbalance.ui.logfood

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class FoodClassifier(private val context: Context) {

    companion object {
        private const val TAG = "FoodClassifier"
        private const val MODEL_FILE = "food_model.tflite"
        private const val LABELS_FILE = "food_labels.txt"
        private const val INPUT_SIZE = 224
        private const val NUM_THREADS = 4
        private const val TOP_K = 3
        private const val CONFIDENCE_THRESHOLD = 0.05f  // 5% minimum
    }

    private var interpreter: Interpreter? = null
    private val labels = mutableListOf<String>()
    private var ready = false

    // Input: [1, 224, 224, 3] float32 — reused across calls to avoid allocation
    private val inputBuffer: ByteBuffer = ByteBuffer
        .allocateDirect(4 * INPUT_SIZE * INPUT_SIZE * 3)
        .apply { order(ByteOrder.nativeOrder()) }

    fun initialize(): Boolean {
        return try {
            val model = loadModelBuffer()
            val options = Interpreter.Options().apply { numThreads = NUM_THREADS }
            interpreter = Interpreter(model, options)
            loadLabels()
            ready = true
            Log.i(TAG, "Initialized: ${labels.size} labels, input ${INPUT_SIZE}x${INPUT_SIZE}")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Init failed — place food_model.tflite in app/src/main/assets/: ${e.message}")
            false
        }
    }

    private fun loadModelBuffer(): MappedByteBuffer {
        val fd = context.assets.openFd(MODEL_FILE)
        return FileInputStream(fd.fileDescriptor).channel.map(
            FileChannel.MapMode.READ_ONLY,
            fd.startOffset,
            fd.declaredLength
        )
    }

    private fun loadLabels() {
        labels.clear()
        context.assets.open(LABELS_FILE).bufferedReader().forEachLine { line ->
            val trimmed = line.trim()
            if (trimmed.isNotEmpty()) labels.add(trimmed)
        }
    }

    fun classify(bitmap: Bitmap): List<FoodPrediction> {
        val interp = interpreter ?: return emptyList()
        if (labels.isEmpty()) return emptyList()

        // Resize to model input size
        val resized = if (bitmap.width == INPUT_SIZE && bitmap.height == INPUT_SIZE) {
            bitmap
        } else {
            Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true)
        }

        fillInputBuffer(resized)

        // Output shape: [1, numLabels]
        val numLabels = labels.size
        val output = Array(1) { FloatArray(numLabels) }

        interp.run(inputBuffer, output)

        return output[0]
            .mapIndexed { i, prob -> i to prob }
            .sortedByDescending { it.second }
            .take(TOP_K)
            .filter { it.second >= CONFIDENCE_THRESHOLD }
            .map { (i, prob) ->
                val name = labels.getOrElse(i) { "Unknown" }
                FoodPrediction(
                    name = name,
                    confidence = (prob * 100).toInt().coerceIn(1, 99),
                    doshaTag = DoshaFoodMapper.doshaTag(name),
                    caloriesPer100g = DoshaFoodMapper.calories(name)
                )
            }
    }

    // Normalize pixels to [0, 1] range (standard for MobileNet/EfficientNet TFLite exports)
    private fun fillInputBuffer(bitmap: Bitmap) {
        inputBuffer.rewind()
        val pixels = IntArray(INPUT_SIZE * INPUT_SIZE)
        bitmap.getPixels(pixels, 0, INPUT_SIZE, 0, 0, INPUT_SIZE, INPUT_SIZE)
        for (px in pixels) {
            inputBuffer.putFloat(((px shr 16) and 0xFF) / 255.0f)  // R
            inputBuffer.putFloat(((px shr 8)  and 0xFF) / 255.0f)  // G
            inputBuffer.putFloat(( px         and 0xFF) / 255.0f)  // B
        }
    }

    fun isReady() = ready

    fun close() {
        interpreter?.close()
        interpreter = null
        ready = false
    }
}
