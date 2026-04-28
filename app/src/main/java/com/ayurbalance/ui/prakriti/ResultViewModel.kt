package com.ayurbalance.ui.prakriti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.AyurBalanceApp
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class ResultViewModel : ViewModel() {

    private val _resultData = MutableLiveData<ResultModel>()
    val resultData: LiveData<ResultModel> = _resultData

    private val _saveComplete = MutableLiveData(false)
    val saveComplete: LiveData<Boolean> = _saveComplete

    fun compute(vataScore: Int, pittaScore: Int, kaphaScore: Int) {
        _resultData.value = InsightEngine.buildResult(vataScore, pittaScore, kaphaScore)
        saveToSupabase(vataScore, pittaScore, kaphaScore)
    }

    private fun saveToSupabase(vataScore: Int, pittaScore: Int, kaphaScore: Int) {
        viewModelScope.launch {
            try {
                val result = _resultData.value ?: return@launch
                val supabase = AyurBalanceApp.supabaseClient
                val userId = supabase.auth.currentUserOrNull()?.id ?: return@launch

                supabase.from("user_profiles").upsert(
                    value = buildJsonObject {
                        put("user_id", userId)
                        put("prakriti_type", result.doshaLabel)
                        put("vata", result.vataPercent)
                        put("pitta", result.pittaPercent)
                        put("kapha", result.kaphaPercent)
                    },
                    onConflict = "user_id"
                )
                _saveComplete.postValue(true)
            } catch (_: Exception) {
                _saveComplete.postValue(true)
            }
        }
    }
}
