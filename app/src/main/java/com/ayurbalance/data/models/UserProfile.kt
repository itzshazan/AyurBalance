package com.ayurbalance.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    @SerialName("user_id") val userId: String = "",
    val age: Int = 0,
    val gender: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0,
    @SerialName("activity_level") val activityLevel: String = "",
    @SerialName("sleep_hours") val sleepHours: Double = 0.0,
    @SerialName("sleep_quality") val sleepQuality: String = "",
    @SerialName("diet_type") val dietType: String = "",
    @SerialName("health_conditions") val healthConditions: List<String> = emptyList(),
    val goal: String = ""
)
