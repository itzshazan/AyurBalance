package com.ayurbalance.ui.prakriti

enum class DoshaType { VATA, PITTA, KAPHA }

data class PrakritiQuestion(
    val id: Int,
    val dimension: String,
    val questionText: String,
    val vataOption: String,
    val pittaOption: String,
    val kaphaOption: String
)
