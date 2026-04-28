package com.ayurbalance.ui.prakriti

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PrakritiViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PrakritiRepository(application)
    private val questions = QuestionRepository.questions

    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex

    private val answers = mutableMapOf<Int, DoshaType>()

    private var vataScore = 0
    private var pittaScore = 0
    private var kaphaScore = 0

    private val _selectedDosha = MutableLiveData<DoshaType?>(null)
    val selectedDosha: LiveData<DoshaType?> = _selectedDosha

    private val _vataPercent = MutableLiveData(33)
    val vataPercent: LiveData<Int> = _vataPercent

    private val _pittaPercent = MutableLiveData(34)
    val pittaPercent: LiveData<Int> = _pittaPercent

    private val _kaphaPercent = MutableLiveData(33)
    val kaphaPercent: LiveData<Int> = _kaphaPercent

    private val _constitutionNote = MutableLiveData("Select answers to discover your Prakriti profile.")
    val constitutionNote: LiveData<String> = _constitutionNote

    private val _assessmentComplete = MutableLiveData(false)
    val assessmentComplete: LiveData<Boolean> = _assessmentComplete

    private val _hasSavedProgress = MutableLiveData(repository.hasSavedProgress())
    val hasSavedProgress: LiveData<Boolean> = _hasSavedProgress

    fun getCurrentQuestion(): PrakritiQuestion = questions[_currentIndex.value ?: 0]

    fun selectAnswer(dosha: DoshaType) {
        val idx = _currentIndex.value ?: return
        val previous = answers[idx]
        if (previous != null) {
            when (previous) {
                DoshaType.VATA -> vataScore--
                DoshaType.PITTA -> pittaScore--
                DoshaType.KAPHA -> kaphaScore--
            }
        }
        answers[idx] = dosha
        when (dosha) {
            DoshaType.VATA -> vataScore++
            DoshaType.PITTA -> pittaScore++
            DoshaType.KAPHA -> kaphaScore++
        }
        _selectedDosha.value = dosha
        recalculatePercents()
    }

    fun nextQuestion() {
        val current = _currentIndex.value ?: return
        if (current >= questions.size - 1) {
            saveState()
            _assessmentComplete.value = true
            return
        }
        val next = current + 1
        _currentIndex.value = next
        _selectedDosha.value = answers[next]
        saveState()
    }

    fun previousQuestion(): Boolean {
        val current = _currentIndex.value ?: return false
        if (current <= 0) return false
        val prev = current - 1
        _currentIndex.value = prev
        _selectedDosha.value = answers[prev]
        return true
    }

    fun saveState() {
        val state = PrakritiState(
            currentIndex = _currentIndex.value ?: 0,
            vataScore = vataScore,
            pittaScore = pittaScore,
            kaphaScore = kaphaScore,
            answers = answers.toMutableMap()
        )
        repository.save(state)
    }

    fun resumeSavedState() {
        val state = repository.load() ?: return
        answers.clear()
        answers.putAll(state.answers)
        vataScore = state.vataScore
        pittaScore = state.pittaScore
        kaphaScore = state.kaphaScore
        _currentIndex.value = state.currentIndex
        _selectedDosha.value = answers[state.currentIndex]
        recalculatePercents()
        _hasSavedProgress.value = false
    }

    fun clearSavedState() {
        repository.clear()
        _hasSavedProgress.value = false
    }

    fun getScores(): Triple<Int, Int, Int> = Triple(vataScore, pittaScore, kaphaScore)

    private fun recalculatePercents() {
        val total = answers.size
        if (total == 0) {
            _vataPercent.value = 33
            _pittaPercent.value = 34
            _kaphaPercent.value = 33
            _constitutionNote.value = "Select answers to discover your Prakriti profile."
            return
        }
        val vPct = (vataScore * 100 / total)
        val pPct = (pittaScore * 100 / total)
        val kPct = 100 - vPct - pPct

        _vataPercent.value = vPct
        _pittaPercent.value = pPct
        _kaphaPercent.value = kPct
        _constitutionNote.value = buildNote(vataScore, pittaScore, kaphaScore, total)
    }

    private fun buildNote(v: Int, p: Int, k: Int, total: Int): String {
        val sorted = listOf("Vata" to v, "Pitta" to p, "Kapha" to k)
            .sortedByDescending { it.second }
        val (d1Name, d1Val) = sorted[0]
        val (d2Name, _) = sorted[1]
        val (_, d3Val) = sorted[2]

        val constitution = when {
            d1Val.toFloat() / total >= 0.55f -> "predominant $d1Name constitution"
            d3Val == 0 -> "$d1Name-$d2Name constitution"
            (sorted[0].second - sorted[1].second) <= 1 &&
                    (sorted[1].second - sorted[2].second) <= 1 -> "Tridoshic constitution"
            else -> "$d1Name-$d2Name constitution"
        }
        return "*Your current selections suggest a $constitution"
    }
}
