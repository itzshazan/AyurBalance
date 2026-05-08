package com.ayurbalance.ui.reminders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayurbalance.ui.meals.AyurvedicMealEngine
import kotlinx.coroutines.launch

data class ReminderScreenState(
    val items: List<ReminderItem> = emptyList(),
    val isLoading: Boolean = true
)

class ReminderViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ReminderRepository(application)

    private val _state = MutableLiveData(ReminderScreenState())
    val state: LiveData<ReminderScreenState> = _state

    init {
        loadReminders()
    }

    fun loadReminders() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)

            repo.ensureDefaultReminders()
            repo.resetHydrationIfNewDay()

            val reminders  = repo.getAllReminders()
            val dosha      = repo.getDominantDosha()
            val hydCurrent = repo.getHydrationCurrent()
            val hydGoal    = repo.getHydrationGoal(dosha)
            val streak     = repo.getStreakDays()
            val ritu       = AyurvedicMealEngine.currentRitu()
            val vikritiDone = repo.vikritiCheckedToday()

            val items = buildList {
                // Meal reminders (top priority)
                reminders.filter { it.type == "MEAL" }.forEach { r ->
                    val mealInfo = mealInfoFor(r.title)
                    add(ReminderItem.MealCard(r, mealInfo.first, mealInfoDosha(dosha), mealInfo.second))
                }

                // Hydration card
                add(ReminderItem.HydrationCard(hydCurrent, hydGoal, dosha))

                // Dinacharya routines
                reminders.filter { it.type == "DINACHARYA" }.forEach { r ->
                    add(ReminderItem.DinacharyaCard(r))
                }

                // Vikriti check-in
                add(ReminderItem.VikritiCard(vikritiDone))

                // Streak motivation
                add(ReminderItem.StreakCard(streak, repo.streakMessage(streak)))

                // Wellness insight
                add(ReminderItem.InsightCard(repo.currentInsight(dosha), ritu))

                // Seasonal tip
                add(ReminderItem.SeasonalTipCard(repo.currentSeasonalTip(dosha), ritu))
            }

            _state.value = ReminderScreenState(items = items, isLoading = false)
        }
    }

    fun markComplete(id: Int) {
        viewModelScope.launch {
            repo.markComplete(id)
            loadReminders()
        }
    }

    fun logWater(litres: Float = 0.25f) {
        viewModelScope.launch {
            repo.logWater(litres)
            loadReminders()
        }
    }

    fun setReminderEnabled(id: Int, enabled: Boolean) {
        viewModelScope.launch {
            repo.setEnabled(id, enabled)
            loadReminders()
        }
    }

    private fun mealInfoFor(title: String): Pair<String, Int> = when {
        title.contains("Breakfast", ignoreCase = true) -> Pair("Moong dal khichdi with ghee", 280)
        title.contains("Lunch",     ignoreCase = true) -> Pair("Seasonal vegetable curry with roti", 420)
        else                                            -> Pair("Light khichdi or warm soup", 220)
    }

    private fun mealInfoDosha(dosha: String): String = when (dosha.uppercase()) {
        "PITTA" -> "Pitta-pacifying"
        "KAPHA" -> "Kapha-lightening"
        else    -> "Vata-nourishing"
    }
}
