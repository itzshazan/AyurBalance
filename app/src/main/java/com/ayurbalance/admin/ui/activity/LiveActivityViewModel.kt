package com.ayurbalance.admin.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayurbalance.admin.data.ActivityLogEntry
import com.ayurbalance.admin.data.LogType

class LiveActivityViewModel : ViewModel() {

    private val _events = MutableLiveData<List<ActivityLogEntry>>()
    val events: LiveData<List<ActivityLogEntry>> = _events

    init {
        loadSampleEvents()
    }

    private fun loadSampleEvents() {
        _events.value = listOf(
            ActivityLogEntry("09:41:02", LogType.FOOD, "user_7821 logged Dal makhani · 87% confidence · Pitta-aggravating flag set"),
            ActivityLogEntry("09:41:00", LogType.CHECKIN, "user_4430 completed Vikriti check-in · Vata elevated · plan refreshed"),
            ActivityLogEntry("09:40:58", LogType.AUTH, "user_9912 signed up · OTP verified · onboarding started"),
            ActivityLogEntry("09:40:55", LogType.FOOD, "user_2201 barcode scan · Amul Butter · USDA match · logged 14 g"),
            ActivityLogEntry("09:40:51", LogType.WARN, "user_6634 food image — confidence 38% · fallback to manual search triggered"),
            ActivityLogEntry("09:40:48", LogType.FOOD, "user_3345 voice entry · \"idli sambar\" · NLP matched · 2 items logged"),
            ActivityLogEntry("09:40:44", LogType.ERROR, "user_8801 image upload failed · network timeout · retry queued")
        )
    }
}
