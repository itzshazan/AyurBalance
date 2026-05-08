package com.ayurbalance.ui.prakriti

data class InsightModel(
    val physical: String,
    val mental: String,
    val risks: String,
    val recommendations: String
)

data class ResultModel(
    val primaryDosha: String,
    val secondaryDosha: String,
    val doshaLabel: String,
    val elementsLine: String,
    val doshaEmoji: String,
    val vataPercent: Int,
    val pittaPercent: Int,
    val kaphaPercent: Int,
    val insights: InsightModel
)

object InsightEngine {

    private val elementsMap = mapOf(
        "VATA" to "Air & Ether",
        "PITTA" to "Fire & Water",
        "KAPHA" to "Earth & Water"
    )

    private val emojiMap = mapOf(
        "VATA" to "🌬️",
        "PITTA" to "🔥",
        "KAPHA" to "🌿"
    )

    fun buildResult(vataScore: Int, pittaScore: Int, kaphaScore: Int): ResultModel {
        val total = vataScore + pittaScore + kaphaScore
        val vataPercent = if (total > 0) vataScore * 100 / total else 33
        val pittaPercent = if (total > 0) pittaScore * 100 / total else 34
        val kaphaPercent = if (total > 0) 100 - vataPercent - pittaPercent else 33

        val sorted = listOf("VATA" to vataPercent, "PITTA" to pittaPercent, "KAPHA" to kaphaPercent)
            .sortedByDescending { it.second }

        val primary = sorted[0].first
        val secondary = sorted[1].first
        val doshaLabel = "$primary–$secondary"

        val primaryElement = elementsMap[primary] ?: "Air & Ether"
        val secondaryElement = elementsMap[secondary] ?: "Fire & Water"
        val elementsLine = "Dual dosha · $primaryElement · $secondaryElement"

        val emoji = emojiMap[primary] ?: "🌿"
        val insights = generateInsights(primary, secondary)

        return ResultModel(
            primaryDosha = primary,
            secondaryDosha = secondary,
            doshaLabel = doshaLabel,
            elementsLine = elementsLine,
            doshaEmoji = emoji,
            vataPercent = vataPercent,
            pittaPercent = pittaPercent,
            kaphaPercent = kaphaPercent,
            insights = insights
        )
    }

    private fun generateInsights(primary: String, secondary: String): InsightModel {
        return when {
            setOf(primary, secondary) == setOf("VATA", "PITTA") -> InsightModel(
                physical = "Lean, active body with a strong metabolism and sensitive digestion.",
                mental = "Sharp, creative mind — quick thinker with high drive and ambition.",
                risks = "Acidity, anxiety, burnout, and irregular sleep patterns.",
                recommendations = "Favour cooling foods, avoid spicy meals, and commit to a consistent daily routine."
            )
            setOf(primary, secondary) == setOf("VATA", "KAPHA") -> InsightModel(
                physical = "Variable energy levels with a tendency to retain weight during cold seasons.",
                mental = "Calm and patient nature, but can be slow to motivate or take initiative.",
                risks = "Fatigue, congestion, water retention, and sluggish digestion.",
                recommendations = "Stay active every day, favour a warm and light diet, and avoid cold or heavy foods."
            )
            setOf(primary, secondary) == setOf("PITTA", "KAPHA") -> InsightModel(
                physical = "Strong, sturdy build with moderate to strong metabolism and good endurance.",
                mental = "Focused, stable, and goal-oriented with consistent emotional grounding.",
                risks = "Inflammation, weight gain, skin issues, and liver sensitivity.",
                recommendations = "Choose light, non-oily meals; avoid excessive heat, heavy foods, and overexertion."
            )
            primary == "VATA" -> InsightModel(
                physical = "Slender, light frame with variable appetite and fluctuating energy.",
                mental = "Creative, enthusiastic multi-tasker with an active and wandering mind.",
                risks = "Anxiety, dryness, constipation, joint discomfort, and poor sleep.",
                recommendations = "Follow a warm, oily, grounding diet — prioritise regularity and adequate rest."
            )
            primary == "PITTA" -> InsightModel(
                physical = "Medium build with sharp appetite and a tendency to overheat.",
                mental = "Ambitious, analytical, and detail-oriented with a competitive edge.",
                risks = "Acidity, inflammation, irritability, and premature hair thinning.",
                recommendations = "Favour cooling foods, avoid alcohol and spicy meals, and practice calming activities."
            )
            else -> InsightModel(
                physical = "Heavier build with slow but steady metabolism and excellent physical endurance.",
                mental = "Loving, loyal, and calm with deep emotional stability.",
                risks = "Weight gain, lethargy, respiratory congestion, and slow digestion.",
                recommendations = "Exercise vigorously each day, favour light and warming foods, and reduce sugar and dairy."
            )
        }
    }
}
