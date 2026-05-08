package com.ayurbalance.ui.logfood

data class AyurvedicProfile(
    val rasa: String,       // primary taste
    val guna: String,       // quality
    val doshaEffect: String,// brief effect on doshas
    val insight: String     // personalised tip shown on success screen
)

object AyurvedicFoodMapper {

    private val data = mapOf(
        "dal makhani"    to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↑ Kapha", "Best enjoyed in small portions at lunch when digestion is strongest."),
        "rajma"          to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↑ Kapha", "Soak overnight and cook with cumin to reduce gas formation."),
        "rajma curry"    to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↑ Kapha", "Soak overnight and cook with cumin to reduce gas formation."),
        "kali dal"       to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↑ Kapha, ↓ Vata", "Ideal for building strength; avoid at night for Kapha types."),
        "palak paneer"   to AyurvedicProfile("Tikta (Bitter)", "Laghu (Light)", "↓ Pitta, ↓ Kapha", "Spinach is cooling and ideal for balancing Pitta. Eat fresh."),
        "paneer"         to AyurvedicProfile("Madhura (Sweet)", "Snigdha (Oily)", "↑ Kapha, ↓ Vata", "Nourishing for Vata; consume in moderation if Kapha-dominant."),
        "biryani"        to AyurvedicProfile("Tikta & Katu (Mixed)", "Guru (Heavy)", "↑ Pitta, ↑ Kapha", "Aromatic spices add agni support; eat in moderate quantities."),
        "chicken biryani" to AyurvedicProfile("Tikta & Katu (Mixed)", "Guru (Heavy)", "↑ Pitta, ↑ Kapha", "Heavy meal — best reserved for midday when digestive fire peaks."),
        "butter chicken" to AyurvedicProfile("Madhura (Sweet)", "Snigdha (Oily)", "↑ Pitta, ↑ Kapha", "Rich and warming; ideal for Vata. Pitta types should limit frequency."),
        "chana masala"   to AyurvedicProfile("Kashaya (Astringent)", "Laghu (Light)", "↓ Kapha, ↑ Vata", "High fibre supports digestion; add ghee to balance Vata."),
        "aloo gobi"      to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "Tridoshic (balanced)", "Light and balanced — suitable for all constitutions."),
        "roti"           to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "Tridoshic (balanced)", "Whole wheat roti with ghee supports steady energy."),
        "chapati"        to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "Tridoshic (balanced)", "Whole wheat chapati with ghee supports steady energy."),
        "naan"           to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↑ Kapha, ↑ Pitta", "White flour and butter make this heating; enjoy occasionally."),
        "rice"           to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "↓ Pitta, ↓ Vata", "Basmati rice is tridoshic and easy to digest — a staple food."),
        "white rice"     to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "↓ Pitta, ↓ Vata", "Basmati rice is tridoshic and easy to digest — a staple food."),
        "brown rice"     to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↓ Kapha", "Higher fibre than white rice; best for Kapha types."),
        "idli"           to AyurvedicProfile("Amla (Sour)", "Laghu (Light)", "↓ Vata, ↑ Pitta slightly", "Fermentation enhances probiotic value and digestibility."),
        "dosa"           to AyurvedicProfile("Amla (Sour)", "Laghu (Light)", "↑ Pitta mildly", "Crispy and light; pair with coconut chutney to cool Pitta."),
        "sambar"         to AyurvedicProfile("Tikta (Bitter)", "Laghu (Light)", "Tridoshic (balanced)", "Lentil and vegetable soup — deeply nourishing and easy to digest."),
        "upma"           to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "Tridoshic (balanced)", "Light and grounding; ideal as a Vata-pacifying breakfast."),
        "poha"           to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "↓ Pitta, ↓ Vata", "Light flattened rice — one of the easiest breakfast foods for all doshas."),
        "paratha"        to AyurvedicProfile("Madhura (Sweet)", "Snigdha (Oily)", "↑ Kapha", "Satisfying and grounding; avoid heavy filling for Kapha types."),
        "khichdi"        to AyurvedicProfile("Madhura (Sweet)", "Laghu (Light)", "Tridoshic (balanced)", "The ultimate Ayurvedic healing food. Excellent for all doshas."),
        "curd"           to AyurvedicProfile("Amla (Sour)", "Guru (Heavy)", "↑ Kapha, ↑ Pitta", "Avoid at night; best consumed fresh at midday with rock salt."),
        "dahi"           to AyurvedicProfile("Amla (Sour)", "Guru (Heavy)", "↑ Kapha, ↑ Pitta", "Avoid at night; best consumed fresh at midday with rock salt."),
        "raita"          to AyurvedicProfile("Madhura & Amla", "Shita (Cool)", "↓ Pitta", "Cooling raita balances spicy meals — ideal for Pitta pacification."),
        "lassi"          to AyurvedicProfile("Madhura (Sweet)", "Snigdha (Oily)", "↓ Vata, ↑ Kapha", "Sweet lassi nourishes and calms; buttermilk version aids digestion."),
        "samosa"         to AyurvedicProfile("Katu (Pungent)", "Guru (Heavy)", "↑ Pitta, ↑ Kapha, ↑ Vata", "Deep-fried and heavy — best as an occasional treat only."),
        "pakora"         to AyurvedicProfile("Katu (Pungent)", "Guru (Heavy)", "↑ Pitta, ↑ Kapha", "Fried foods are heating; enjoy sparingly with cooling chutney."),
        "gulab jamun"    to AyurvedicProfile("Madhura (Sweet)", "Guru (Heavy)", "↑ Kapha", "Very sweet and dense; small portions are enough to satisfy."),
        "kheer"          to AyurvedicProfile("Madhura (Sweet)", "Snigdha (Oily)", "↓ Vata, ↑ Kapha", "Milk-based sweet that soothes Vata; best in cooler seasons."),
    )

    fun get(foodName: String): AyurvedicProfile {
        val key = foodName.lowercase().trim()
        data[key]?.let { return it }
        for ((k, v) in data) { if (key.contains(k) || k.contains(key)) return v }
        return AyurvedicProfile(
            rasa     = "Madhura (Sweet)",
            guna     = "Laghu (Light)",
            doshaEffect = "Tridoshic (balanced)",
            insight  = "Eat mindfully and chew thoroughly to enhance absorption."
        )
    }
}
