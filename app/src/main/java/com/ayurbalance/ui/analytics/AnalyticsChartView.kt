package com.ayurbalance.ui.analytics

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator

class AnalyticsChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bars: List<ChartBar> = emptyList()
    private var animatedFraction = 0f

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#4A7A35")
    }
    private val todayPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2D5F1B")
    }
    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#EDE5DA")
    }
    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#8A8279")
        textAlign = Paint.Align.CENTER
        textSize = 11f * context.resources.displayMetrics.scaledDensity
    }
    private val todayLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2D5F1B")
        textAlign = Paint.Align.CENTER
        textSize = 11f * context.resources.displayMetrics.scaledDensity
        isFakeBoldText = true
    }

    private val barRect   = RectF()
    private val trackRect = RectF()

    fun setBars(data: List<ChartBar>) {
        bars = data
        animatedFraction = 0f
        startAnimation()
    }

    private fun startAnimation() {
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 900
            interpolator = DecelerateInterpolator(1.5f)
            addUpdateListener {
                this@AnalyticsChartView.animatedFraction = it.animatedFraction
                this@AnalyticsChartView.invalidate()
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bars.isEmpty()) return

        val labelH    = height * 0.20f
        val chartTop  = 8f
        val chartBot  = height - labelH

        val count    = bars.size
        val barWidth = width / (count * 1.8f)
        val spacing  = (width.toFloat() - barWidth * count) / (count + 1)
        val cornerR  = barWidth / 2f

        bars.forEachIndexed { i, bar ->
            val left = spacing + i * (barWidth + spacing)
            val right = left + barWidth
            val cx   = (left + right) / 2f

            // Track (full height background)
            trackRect.set(left, chartTop, right, chartBot)
            canvas.drawRoundRect(trackRect, cornerR, cornerR, trackPaint)

            // Animated fill bar
            if (bar.alignmentPercent > 0) {
                val fillH   = (chartBot - chartTop) * (bar.alignmentPercent / 100f) * animatedFraction
                val fillTop = chartBot - fillH
                barRect.set(left, fillTop, right, chartBot)
                canvas.drawRoundRect(barRect, cornerR, cornerR, if (bar.isToday) todayPaint else barPaint)
            }

            // Day label
            val labelY = height - labelH * 0.15f
            canvas.drawText(bar.dayLabel, cx, labelY, if (bar.isToday) todayLabelPaint else labelPaint)
        }
    }
}
