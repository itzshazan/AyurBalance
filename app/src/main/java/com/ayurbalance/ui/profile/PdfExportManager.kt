package com.ayurbalance.ui.profile

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PdfExportManager(private val context: Context) {

    suspend fun exportReport(state: ProfileState, onDone: (Uri?) -> Unit) {
        withContext(Dispatchers.IO) {
            val uri = runCatching { generatePdf(state) }.getOrNull()
            withContext(Dispatchers.Main) { onDone(uri) }
        }
    }

    private fun generatePdf(state: ProfileState): Uri {
        val document = PdfDocument()

        // A4: 595 x 842 points at 72dpi
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page     = document.startPage(pageInfo)
        drawPage(page.canvas, state)
        document.finishPage(page)

        val uri = savePdf(document, "AyurBalance_Report_${timestamp()}.pdf")
        document.close()
        return uri
    }

    private fun drawPage(canvas: Canvas, state: ProfileState) {
        val green  = Color.parseColor("#2D5F1B")
        val cream  = Color.parseColor("#FDF8F3")
        val brown  = Color.parseColor("#4A4540")
        val caption = Color.parseColor("#8A8279")

        val headerPaint  = Paint().apply { color = green }
        val bodyPaint    = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = cream }
        val titlePaint   = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE; textSize = 22f; isFakeBoldText = true
        }
        val subPaint     = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; textSize = 13f }
        val headingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = green; textSize = 14f; isFakeBoldText = true
        }
        val bodyTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = brown; textSize = 12f }
        val captionPaint  = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = caption; textSize = 10f }
        val dividerPaint  = Paint().apply { color = Color.parseColor("#E8E0D6") }

        // Header block
        canvas.drawRect(0f, 0f, 595f, 120f, headerPaint)
        canvas.drawText("AyurBalance", 32f, 48f, titlePaint)
        canvas.drawText("Personal Wellness Report", 32f, 68f, subPaint)
        canvas.drawText("Generated ${SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())}", 32f, 88f, subPaint.apply { alpha = 180 })

        // Profile block
        var y = 148f
        canvas.drawText("PROFILE", 32f, y, headingPaint)
        y += 20f
        canvas.drawRect(32f, y, 563f, y + 1f, dividerPaint)
        y += 16f
        canvas.drawText("Name:   ${state.displayName}", 32f, y, bodyTextPaint); y += 18f
        canvas.drawText("Email:  ${state.email}", 32f, y, bodyTextPaint); y += 18f
        if (state.age > 0) { canvas.drawText("Age:    ${state.age}", 32f, y, bodyTextPaint); y += 18f }
        if (state.gender.isNotBlank()) { canvas.drawText("Gender: ${state.gender.replaceFirstChar { it.uppercase() }}", 32f, y, bodyTextPaint); y += 18f }

        // Prakriti block
        y += 12f
        canvas.drawText("PRAKRITI CONSTITUTION", 32f, y, headingPaint); y += 20f
        canvas.drawRect(32f, y, 563f, y + 1f, dividerPaint); y += 16f
        if (state.constitutionLabel.isNotBlank()) {
            canvas.drawText("Type:   ${state.constitutionLabel}", 32f, y, bodyTextPaint); y += 18f
        }
        if (state.vataPct > 0) {
            canvas.drawText("Vāta:   ${state.vataPct}%   Pitta: ${state.pittaPct}%   Kapha: ${state.kaphaPct}%", 32f, y, bodyTextPaint)
            y += 18f
        }

        // Wellness profile
        y += 12f
        canvas.drawText("WELLNESS PROFILE", 32f, y, headingPaint); y += 20f
        canvas.drawRect(32f, y, 563f, y + 1f, dividerPaint); y += 16f
        if (state.dietType.isNotBlank()) {
            canvas.drawText("Diet:     ${state.dietType.replace("_", " ").replaceFirstChar { it.uppercase() }}", 32f, y, bodyTextPaint); y += 18f
        }
        if (state.goal.isNotBlank()) {
            canvas.drawText("Goal:     ${state.goal.replace("_", " ").replaceFirstChar { it.uppercase() }}", 32f, y, bodyTextPaint); y += 18f
        }
        if (state.activityLevel.isNotBlank()) {
            canvas.drawText("Activity: ${state.activityLevel.replace("_", " ").replaceFirstChar { it.uppercase() }}", 32f, y, bodyTextPaint); y += 18f
        }
        if (state.sleepHours > 0) {
            canvas.drawText("Sleep:    ${state.sleepHours} hrs/night", 32f, y, bodyTextPaint); y += 18f
        }
        if (state.healthConditions.isNotEmpty()) {
            canvas.drawText("Conditions: ${state.healthConditions.joinToString(", ")}", 32f, y, bodyTextPaint); y += 18f
        }

        // Streak
        if (state.streakDays > 0) {
            y += 12f
            canvas.drawText("WELLNESS STREAK", 32f, y, headingPaint); y += 20f
            canvas.drawRect(32f, y, 563f, y + 1f, dividerPaint); y += 16f
            canvas.drawText("🔥 ${state.streakDays} consecutive days logged", 32f, y, bodyTextPaint); y += 18f
        }

        // Footer
        canvas.drawRect(0f, 800f, 595f, 801f, dividerPaint)
        canvas.drawText(
            "Generated by AyurBalance • Your Ayurvedic Wellness Companion",
            32f, 825f, captionPaint
        )
    }

    private fun savePdf(document: PdfDocument, filename: String): Uri {
        val values = ContentValues().apply {
            put(MediaStore.Downloads.DISPLAY_NAME, filename)
            put(MediaStore.Downloads.MIME_TYPE, "application/pdf")
            put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
            ?: error("MediaStore insert failed")
        resolver.openOutputStream(uri)!!.use { document.writeTo(it) }
        return uri
    }

    private fun timestamp() = SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(Date())

    fun openShareSheet(context: Context, uri: Uri) {
        val share = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(share, "Share Wellness Report"))
    }
}
