package com.ayurbalance.ui.logfood

object DoshaFoodMapper {

    data class FoodMeta(val doshaTag: String, val caloriesPer100g: Int)

    private val map: Map<String, FoodMeta> = mapOf(
        // Heavy / Pitta-aggravating
        "dal makhani"            to FoodMeta("PITTA HEAVY",    340),
        "butter chicken"         to FoodMeta("PITTA HEAVY",    290),
        "chicken biryani"        to FoodMeta("PITTA HEAVY",    210),
        "biryani"                to FoodMeta("PITTA MODERATE", 200),
        "chole"                  to FoodMeta("PITTA MODERATE", 180),
        "rajma"                  to FoodMeta("KAPHA MODERATE", 130),
        "pav bhaji"              to FoodMeta("PITTA HEAVY",    210),
        "vada pav"               to FoodMeta("KAPHA HEAVY",    250),
        "paneer tikka"           to FoodMeta("PITTA MODERATE", 260),
        "paneer butter masala"   to FoodMeta("PITTA HEAVY",    310),
        "matar paneer"           to FoodMeta("PITTA MODERATE", 180),
        "palak paneer"           to FoodMeta("PITTA MODERATE", 160),
        "kadai paneer"           to FoodMeta("PITTA HEAVY",    280),
        "shahi paneer"           to FoodMeta("PITTA HEAVY",    350),
        "malai kofta"            to FoodMeta("KAPHA HEAVY",    400),
        "navratan korma"         to FoodMeta("KAPHA MODERATE", 220),
        "chicken curry"          to FoodMeta("PITTA MODERATE", 200),
        "mutton curry"           to FoodMeta("PITTA HEAVY",    250),
        "fish curry"             to FoodMeta("PITTA MODERATE", 160),
        "prawn curry"            to FoodMeta("PITTA MODERATE", 130),
        "egg curry"              to FoodMeta("PITTA MODERATE", 160),
        "bhatura"                to FoodMeta("KAPHA HEAVY",    320),
        "naan"                   to FoodMeta("KAPHA MODERATE", 310),
        "paratha"                to FoodMeta("KAPHA MODERATE", 280),
        "puri"                   to FoodMeta("KAPHA HEAVY",    340),
        "gulab jamun"            to FoodMeta("KAPHA HEAVY",    380),
        "jalebi"                 to FoodMeta("KAPHA HEAVY",    360),
        "halwa"                  to FoodMeta("KAPHA HEAVY",    310),
        "kheer"                  to FoodMeta("KAPHA MODERATE", 150),
        "samosa"                 to FoodMeta("KAPHA HEAVY",    260),
        "kachori"                to FoodMeta("KAPHA HEAVY",    280),
        "pakora"                 to FoodMeta("KAPHA MODERATE", 230),
        "tandoori chicken"       to FoodMeta("PITTA MODERATE", 165),
        "seekh kebab"            to FoodMeta("PITTA MODERATE", 220),
        "chicken tikka"          to FoodMeta("PITTA MODERATE", 180),
        "vegetable biryani"      to FoodMeta("PITTA LIGHT",    175),
        "fried rice"             to FoodMeta("KAPHA MODERATE", 190),

        // Light / Vata or Kapha-pacifying
        "idli"                   to FoodMeta("VATA PACIFYING",  58),
        "dosa"                   to FoodMeta("VATA PACIFYING",  110),
        "masala dosa"            to FoodMeta("PITTA LIGHT",    130),
        "uttapam"                to FoodMeta("VATA PACIFYING",  120),
        "pesarattu"              to FoodMeta("KAPHA LIGHT",     90),
        "upma"                   to FoodMeta("KAPHA LIGHT",    140),
        "poha"                   to FoodMeta("KAPHA LIGHT",    140),
        "khichdi"                to FoodMeta("VATA PACIFYING",  130),
        "sabudana khichdi"       to FoodMeta("VATA PACIFYING",  180),
        "medu vada"              to FoodMeta("KAPHA MODERATE", 200),
        "dhokla"                 to FoodMeta("KAPHA LIGHT",    150),
        "thepla"                 to FoodMeta("VATA PACIFYING",  180),
        "chapati"                to FoodMeta("VATA PACIFYING",  100),
        "roti"                   to FoodMeta("VATA PACIFYING",  100),
        "rice"                   to FoodMeta("VATA PACIFYING",  130),
        "curd rice"              to FoodMeta("PITTA LIGHT",    140),
        "coconut rice"           to FoodMeta("PITTA LIGHT",    160),
        "lemon rice"             to FoodMeta("PITTA LIGHT",    155),
        "tamarind rice"          to FoodMeta("PITTA MODERATE", 170),
        "sambar"                 to FoodMeta("VATA PACIFYING",   50),
        "rasam"                  to FoodMeta("VATA PACIFYING",   25),
        "dal tadka"              to FoodMeta("VATA PACIFYING",  120),
        "dal fry"                to FoodMeta("VATA PACIFYING",  115),
        "yellow dal"             to FoodMeta("VATA PACIFYING",  100),
        "moong dal"              to FoodMeta("VATA PACIFYING",   90),
        "masoor dal"             to FoodMeta("VATA PACIFYING",  110),
        "toor dal"               to FoodMeta("VATA PACIFYING",  115),
        "urad dal"               to FoodMeta("KAPHA MODERATE", 130),
        "aloo gobi"              to FoodMeta("VATA PACIFYING",   90),
        "aloo tikki"             to FoodMeta("KAPHA MODERATE", 200),
        "dum aloo"               to FoodMeta("PITTA MODERATE", 200),
        "baingan bharta"         to FoodMeta("VATA PACIFYING",  100),
        "sarson ka saag"         to FoodMeta("KAPHA LIGHT",     65),
        "makki ki roti"          to FoodMeta("KAPHA LIGHT",    200),
        "vegetable curry"        to FoodMeta("VATA PACIFYING",   80),
        "mixed vegetable"        to FoodMeta("VATA PACIFYING",   75),
        "spinach dal"            to FoodMeta("VATA PACIFYING",   95),
        "avial"                  to FoodMeta("KAPHA LIGHT",     110),
        "appam"                  to FoodMeta("VATA PACIFYING",   90),
        "puttu"                  to FoodMeta("KAPHA LIGHT",     120),
        "payasam"                to FoodMeta("KAPHA MODERATE", 150),
        "bhel puri"              to FoodMeta("VATA LIGHT",     120),
        "pani puri"              to FoodMeta("PITTA MODERATE", 140),
        "misal pav"              to FoodMeta("PITTA MODERATE", 200)
    )

    private val default = FoodMeta("PITTA MODERATE", 200)

    fun get(foodName: String): FoodMeta {
        val key = foodName.lowercase().trim()
        // exact match
        map[key]?.let { return it }
        // partial match
        return map.entries.firstOrNull { key.contains(it.key) || it.key.contains(key) }?.value
            ?: default
    }

    fun doshaTag(foodName: String) = get(foodName).doshaTag
    fun calories(foodName: String) = get(foodName).caloriesPer100g
}
