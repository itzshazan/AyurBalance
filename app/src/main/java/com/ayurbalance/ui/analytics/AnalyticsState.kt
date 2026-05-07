package com.ayurbalance.ui.analytics

import android.graphics.Color

data class AnalyticsState(
    val avgDailyKcal: Int = 0,
    val kcalDeltaPercent: Float = 0f,
    val doshaAlignmentPercent: Int = 0,
    val doshaAlignmentDelta: Float = 0f,
    val chartBars: List<ChartBar> = emptyList(),
    val carbsPercent: Int = 0,
    val proteinPercent: Int = 0,
    val fatPercent: Int = 0,
    val insightText: String = "",
    val isLoading: Boolean = true,
    val isEmpty: Boolean = false
)

data class ChartBar(
    val dayLabel: String,
    val alignmentPercent: Int,
    val isToday: Boolean = false
)

data class MacroItem(
    val name: String,
    val percent: Int,
    val color: Int
)

val MACRO_ITEMS_PLACEHOLDER = listOf(
    MacroItem("Carbohydrates", 0, Color.parseColor("#E8A75A")),
    MacroItem("Protein",       0, Color.parseColor("#2D5F1B")),
    MacroItem("Fat",           0, Color.parseColor("#C4572A"))
)
