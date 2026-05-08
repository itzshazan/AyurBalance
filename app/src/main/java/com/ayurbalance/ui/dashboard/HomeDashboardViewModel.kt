package com.ayurbalance.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.AyurBalanceApp
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import java.util.Calendar

data class DashboardState(
    val greeting: String = "Good morning,",
    val userName: String = "Seeker",
    val userInitials: String = "S",
    val avatarUrl: String? = null,
    val dayRitu: String = "",
    val streakDays: Int = 1,
    val vataPercent: Int = 33,
    val pittaPercent: Int = 34,
    val kaphaPercent: Int = 33,
    val dominantDosha: String = "PITTA",
    val imbalanceAlert: ImbalanceAlert? = null,
    val nextMeal: MealRecommendation = MealEngine.defaultMeal(),
    val hydrationCurrentL: Float = 1.2f,
    val hydrationGoalL: Float = 2.0f,
    val pranaBreathsPerMin: Int = 8
)

@Serializable
private data class UserProfileRow(
    @SerialName("user_id") val userId: String = "",
    val vata: Int = 33,
    val pitta: Int = 34,
    val kapha: Int = 33
)

class HomeDashboardViewModel : ViewModel() {

    private val _state = MutableLiveData<DashboardState>()
    val state: LiveData<DashboardState> = _state

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val cal = Calendar.getInstance()
            val hour = cal.get(Calendar.HOUR_OF_DAY)

            val greeting = when {
                hour < 12 -> "Good morning,"
                hour < 17 -> "Good afternoon,"
                else      -> "Good evening,"
            }
            val dayRitu = "${dayName(cal.get(Calendar.DAY_OF_WEEK))} · ${ritu(cal.get(Calendar.MONTH) + 1)}"

            var userName = "Seeker"
            var avatarUrl: String? = null
            var vata = 33
            var pitta = 34
            var kapha = 33

            try {
                val supabase = AyurBalanceApp.supabaseClient
                val user = supabase.auth.currentUserOrNull()

                if (user != null) {
                    // Name and avatar come from auth metadata — works for both
                    // email/password (stores full_name in metadata on sign-up)
                    // and Google OAuth (Supabase auto-populates from Google profile)
                    val meta = user.userMetadata
                    val metaName = meta?.get("full_name")?.jsonPrimitive?.contentOrNull
                        ?: meta?.get("name")?.jsonPrimitive?.contentOrNull
                    avatarUrl = meta?.get("avatar_url")?.jsonPrimitive?.contentOrNull
                        ?: meta?.get("picture")?.jsonPrimitive?.contentOrNull

                    val firstName = metaName?.split(" ")?.firstOrNull()
                    if (!firstName.isNullOrBlank()) userName = firstName

                    // Dosha data from user_profiles filtered to this user's row
                    try {
                        val profiles = supabase.from("user_profiles")
                            .select {
                                filter { eq("user_id", user.id) }
                            }
                            .decodeList<UserProfileRow>()

                        profiles.firstOrNull()?.let {
                            vata = it.vata
                            pitta = it.pitta
                            kapha = it.kapha
                        }
                    } catch (_: Exception) {}
                }
            } catch (_: Exception) {}

            val sorted = listOf("VATA" to vata, "PITTA" to pitta, "KAPHA" to kapha)
                .sortedByDescending { it.second }
            val dominant = sorted[0].first
            val dominantPct = sorted[0].second

            val alert: ImbalanceAlert? = if (dominantPct > 50) {
                ImbalanceAlert(
                    title = alertTitle(dominant),
                    description = imbalanceDesc(dominant)
                )
            } else null

            val initials = userName.firstOrNull()?.uppercaseChar()?.toString() ?: "S"

            _state.postValue(
                DashboardState(
                    greeting = greeting,
                    userName = userName,
                    userInitials = initials,
                    avatarUrl = avatarUrl,
                    dayRitu = dayRitu,
                    streakDays = 1,
                    vataPercent = vata,
                    pittaPercent = pitta,
                    kaphaPercent = kapha,
                    dominantDosha = dominant,
                    imbalanceAlert = alert,
                    nextMeal = MealEngine.recommend(dominant, hour),
                    hydrationCurrentL = 1.2f,
                    hydrationGoalL = 2.0f,
                    pranaBreathsPerMin = 8
                )
            )
        }
    }

    private fun alertTitle(dosha: String) = when (dosha) {
        "VATA"  -> "Vata rising"
        "PITTA" -> "Pitta rising"
        else    -> "Kapha rising"
    }

    private fun dayName(dow: Int) = when (dow) {
        Calendar.MONDAY    -> "Monday"
        Calendar.TUESDAY   -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY  -> "Thursday"
        Calendar.FRIDAY    -> "Friday"
        Calendar.SATURDAY  -> "Saturday"
        else               -> "Sunday"
    }

    private fun ritu(month: Int) = when (month) {
        1, 2   -> "Shishira"
        3, 4   -> "Vasanta"
        5, 6   -> "Grishma"
        7, 8   -> "Varsha"
        9, 10  -> "Sharad"
        else   -> "Hemanta"
    }

    private fun imbalanceDesc(dosha: String) = when (dosha) {
        "VATA"  -> "Irregular digestion · Grounding foods recommended"
        "PITTA" -> "Internal heat detected · Cooling foods recommended"
        else    -> "Low energy detected · Warming, light foods recommended"
    }
}
