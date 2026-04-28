package com.ayurbalance.ui.location.model

data class RegionData(
    val code: String,
    val label: String,
    val climateZone: String  // "tropical" | "arid" | "temperate"
) {
    companion object {
        fun allRegions(): List<RegionData> = listOf(
            RegionData("IN_NORTH",       "North India",               "temperate"),
            RegionData("IN_SOUTH",       "South India",               "tropical"),
            RegionData("IN_EAST",        "East India",                "tropical"),
            RegionData("IN_WEST",        "West India",                "temperate"),
            RegionData("IN_WEST_ARID",   "West India (Arid)",         "arid"),
            RegionData("IN_NE",          "Northeast India",           "tropical"),
            RegionData("INTL_TEMPERATE", "International (Temperate)", "temperate"),
            RegionData("INTL_TROPICAL",  "International (Tropical)",  "tropical"),
            RegionData("INTL_ARID",      "International (Arid)",      "arid")
        )
    }
}
