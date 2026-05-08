package com.ayurbalance.ui.prakriti

data class PrakritiState(
    val currentIndex: Int,
    val vataScore: Int,
    val pittaScore: Int,
    val kaphaScore: Int,
    val answers: MutableMap<Int, DoshaType>
)
