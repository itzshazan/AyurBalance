package com.ayurbalance.ui.prakriti

import android.content.Context
import android.content.SharedPreferences

class LocalStorageManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveState(state: PrakritiState) {
        prefs.edit().apply {
            putInt(KEY_CURRENT_INDEX, state.currentIndex)
            putInt(KEY_VATA_SCORE, state.vataScore)
            putInt(KEY_PITTA_SCORE, state.pittaScore)
            putInt(KEY_KAPHA_SCORE, state.kaphaScore)
            putString(KEY_ANSWERS, serializeAnswers(state.answers))
            putBoolean(KEY_HAS_STATE, true)
            apply()
        }
    }

    fun loadState(): PrakritiState? {
        if (!prefs.getBoolean(KEY_HAS_STATE, false)) return null
        return PrakritiState(
            currentIndex = prefs.getInt(KEY_CURRENT_INDEX, 0),
            vataScore = prefs.getInt(KEY_VATA_SCORE, 0),
            pittaScore = prefs.getInt(KEY_PITTA_SCORE, 0),
            kaphaScore = prefs.getInt(KEY_KAPHA_SCORE, 0),
            answers = deserializeAnswers(prefs.getString(KEY_ANSWERS, "") ?: "")
        )
    }

    fun clearState() {
        prefs.edit().clear().apply()
    }

    fun hasSavedState(): Boolean = prefs.getBoolean(KEY_HAS_STATE, false)

    private fun serializeAnswers(answers: Map<Int, DoshaType>): String =
        answers.entries.joinToString(";") { "${it.key}:${it.value.name}" }

    private fun deserializeAnswers(raw: String): MutableMap<Int, DoshaType> {
        if (raw.isBlank()) return mutableMapOf()
        return raw.split(";").mapNotNull { entry ->
            val parts = entry.split(":")
            if (parts.size == 2) {
                val idx = parts[0].toIntOrNull() ?: return@mapNotNull null
                val dosha = DoshaType.entries.find { it.name == parts[1] } ?: return@mapNotNull null
                idx to dosha
            } else null
        }.toMap().toMutableMap()
    }

    companion object {
        private const val PREF_NAME = "ayur_prakriti_state"
        private const val KEY_HAS_STATE = "has_state"
        private const val KEY_CURRENT_INDEX = "current_index"
        private const val KEY_VATA_SCORE = "vata_score"
        private const val KEY_PITTA_SCORE = "pitta_score"
        private const val KEY_KAPHA_SCORE = "kapha_score"
        private const val KEY_ANSWERS = "answers"
    }
}
