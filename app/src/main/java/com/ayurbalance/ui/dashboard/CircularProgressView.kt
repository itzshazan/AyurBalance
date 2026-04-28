package com.ayurbalance.ui.dashboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircularProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress: Float = 0.6f
        set(value) { field = value.coerceIn(0f, 1f); invalidate() }

    var trackColor: Int = Color.parseColor("#E8E0D6")
        set(value) { field = value; invalidate() }

    var arcColor: Int = Color.parseColor("#2D5F1B")
        set(value) { field = value; invalidate() }

    var strokeDp: Float = 7f
        set(value) { field = value; invalidate() }

    var isDashed: Boolean = false
        set(value) { field = value; invalidate() }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val oval = RectF()

    override fun onDraw(canvas: Canvas) {
        val strokePx = strokeDp * resources.displayMetrics.density
        val cx = width / 2f
        val cy = height / 2f
        val radius = minOf(cx, cy) - strokePx

        oval.set(cx - radius, cy - radius, cx + radius, cy + radius)

        paint.strokeWidth = strokePx

        // Track ring
        paint.color = trackColor
        paint.pathEffect = if (isDashed) DashPathEffect(floatArrayOf(10f, 7f), 0f) else null
        canvas.drawArc(oval, -90f, 360f, false, paint)

        // Progress arc (only when not dashed)
        if (!isDashed && progress > 0f) {
            paint.color = arcColor
            paint.pathEffect = null
            canvas.drawArc(oval, -90f, progress * 360f, false, paint)
        }
    }
}
