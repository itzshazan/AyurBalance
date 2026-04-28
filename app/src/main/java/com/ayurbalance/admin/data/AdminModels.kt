package com.ayurbalance.admin.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminUser(
    val id: String = "",
    val email: String = "",
    val role: String = "",
    @SerialName("created_at") val createdAt: String = ""
)

data class DashboardMetrics(
    val totalUsers: Int = 24871,
    val dau: Int = 8204,
    val assessmentsDone: Int = 21430,
    val mealsToday: Int = 31560,
    val totalUsersDelta: String = "+312 this week",
    val dauDelta: String = "+5.2% vs last week",
    val assessmentRate: String = "86% completion rate",
    val mealsDelta: String = "+8% vs yesterday"
)

@Serializable
data class UserRecord(
    val id: String = "",
    val email: String = "",
    @SerialName("full_name") val fullName: String = "",
    val prakriti: String? = null,
    val status: String = "active",
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("last_active") val lastActive: String? = null
)

data class ActivityLogEntry(
    val time: String,
    val type: LogType,
    val message: String
)

enum class LogType(val label: String) {
    FOOD("Food"),
    CHECKIN("Check-in"),
    AUTH("Auth"),
    ERROR("Error"),
    WARN("Warn"),
    INFO("Info")
}

@Serializable
data class FoodItem(
    val id: String = "",
    val name: String = "",
    val source: String = "ICMR",
    @SerialName("calories_per_100g") val calories: Int = 0,
    @SerialName("dosha_impact") val doshaImpact: String? = null,
    @SerialName("ayurvedic_properties") val properties: String? = null,
    val status: String = "pending"
)

@Serializable
data class Recipe(
    val id: String = "",
    val name: String = "",
    val dosha: String = "",
    val season: String = "All",
    @SerialName("prep_time_minutes") val prepTime: Int = 0,
    val status: String = "draft"
)

data class DeletionRequest(
    val userId: String,
    val requestedDate: String,
    val ageHours: Int
)

data class MLMetrics(
    val avgConfidence: Int = 71,
    val top1Accuracy: Int = 84,
    val fallbackRate: Int = 18,
    val modelSizeMb: Float = 11.2f
)

data class SystemService(
    val name: String,
    val status: ServiceStatus
)

enum class ServiceStatus { OPERATIONAL, DEGRADED, DOWN }
