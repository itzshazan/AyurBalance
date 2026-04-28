package com.ayurbalance.ui.logfood

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ViewfinderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val density get() = resources.displayMetrics.density

    private val bracketPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#4A7A35")
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val ringPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        alpha = 160
    }

    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        val w = width.toFloat()
        val h = height.toFloat()

        bracketPaint.strokeWidth = 3f * density
        ringPaint.strokeWidth = 1.5f * density

        val bracketLen = 28f * density
        val bracketPad = 36f * density
        val ringRadius = 48f * density
        val cx = w / 2f
        val cy = h / 2f

        // Top-left bracket
        canvas.drawLine(bracketPad, bracketPad, bracketPad + bracketLen, bracketPad, bracketPaint)
        canvas.drawLine(bracketPad, bracketPad, bracketPad, bracketPad + bracketLen, bracketPaint)

        // Top-right bracket
        canvas.drawLine(w - bracketPad, bracketPad, w - bracketPad - bracketLen, bracketPad, bracketPaint)
        canvas.drawLine(w - bracketPad, bracketPad, w - bracketPad, bracketPad + bracketLen, bracketPaint)

        // Bottom-left bracket
        canvas.drawLine(bracketPad, h - bracketPad, bracketPad + bracketLen, h - bracketPad, bracketPaint)
        canvas.drawLine(bracketPad, h - bracketPad, bracketPad, h - bracketPad - bracketLen, bracketPaint)

        // Bottom-right bracket
        canvas.drawLine(w - bracketPad, h - bracketPad, w - bracketPad - bracketLen, h - bracketPad, bracketPaint)
        canvas.drawLine(w - bracketPad, h - bracketPad, w - bracketPad, h - bracketPad - bracketLen, bracketPaint)

        // Center focus ring
        canvas.drawCircle(cx, cy, ringRadius, ringPaint)

        // Center dot
        canvas.drawCircle(cx, cy, 5f * density, dotPaint)
    }
}
