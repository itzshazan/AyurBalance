package com.ayurbalance.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val age: Int,
    val gender: String,
    val height: Double,
    val weight: Double,
    @SerialName("activity_level") val activityLevel: String,
    @SerialName("sleep_hours") val sleepHours: Double,
    @SerialName("sleep_quality") val sleepQuality: String,
    @SerialName("diet_type") val dietType: String,
    @SerialName("health_conditions") val healthConditions: List<String>,
    val goal: String
)
