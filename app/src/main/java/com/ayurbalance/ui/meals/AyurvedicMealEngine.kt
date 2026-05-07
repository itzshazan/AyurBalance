package com.ayurbalance.ui.meals

import com.ayurbalance.data.models.MealItem
import com.ayurbalance.data.models.MealType
import com.ayurbalance.data.models.SeasonalProtocol
import java.time.LocalDate
import java.time.Month

object AyurvedicMealEngine {

    // ─── Season constants ───────────────────────────────────────────────────
    const val HEMANTA  = "Hemanta"   // Nov-Dec  pre-winter
    const val SHISHIRA = "Shishira"  // Jan-Feb  late winter
    const val VASANTA  = "Vasanta"   // Mar-Apr  spring
    const val GRISHMA  = "Grishma"   // May-Jun  summer
    const val VARSHA   = "Varsha"    // Jul-Aug  monsoon
    const val SHARAD   = "Sharad"    // Sep-Oct  autumn

    private val ALL_S    = listOf(HEMANTA, SHISHIRA, VASANTA, GRISHMA, VARSHA, SHARAD)
    private val WINTER_S = listOf(HEMANTA, SHISHIRA)
    private val SUMMER_S = listOf(GRISHMA, VARSHA)
    private val SPRING_S = listOf(VASANTA, SHARAD)
    private val WARM_S   = listOf(HEMANTA, SHISHIRA, VASANTA)
    private val COOL_S   = listOf(GRISHMA, VARSHA, SHARAD)

    // Dosha shorthand sets
    private val V    = listOf("VATA")
    private val P    = listOf("PITTA")
    private val K    = listOf("KAPHA")
    private val VP   = listOf("VATA", "PITTA")
    private val VK   = listOf("VATA", "KAPHA")
    private val PK   = listOf("PITTA", "KAPHA")
    private val ALL_D = listOf("VATA", "PITTA", "KAPHA")

    // ─── Meal database ──────────────────────────────────────────────────────
    private fun meal(id: String, name: String, type: MealType, cal: Int, prep: Int,
                     d: List<String>, s: List<String> = ALL_S) =
        MealItem(id, name, type.name, cal, prep, d, s)

    val allMeals: List<MealItem> = listOf(
        // ── BREAKFAST ───────────────────────────────────────────────────────
        meal("b01", "Poha with coconut",          MealType.BREAKFAST, 260, 15, VP),
        meal("b02", "Moong dal chilla",            MealType.BREAKFAST, 220, 20, VK),
        meal("b03", "Upma with vegetables",        MealType.BREAKFAST, 290, 20, V),
        meal("b04", "Idli with sambar",            MealType.BREAKFAST, 240, 10, P),
        meal("b05", "Ragi porridge",               MealType.BREAKFAST, 190, 15, K,   SPRING_S + WINTER_S),
        meal("b06", "Sabudana khichdi",            MealType.BREAKFAST, 330, 20, V,   WINTER_S + listOf(SHARAD)),
        meal("b07", "Dalia with ghee and dates",   MealType.BREAKFAST, 350, 15, V,   WINTER_S),
        meal("b08", "Banana cardamom smoothie",    MealType.BREAKFAST, 220,  5, VP,  SUMMER_S),
        meal("b09", "Bajra roti with ghee",        MealType.BREAKFAST, 310, 20, V,   WINTER_S),
        meal("b10", "Mint coriander poha",         MealType.BREAKFAST, 240, 15, P,   COOL_S),
        meal("b11", "Vegetable rava idli",         MealType.BREAKFAST, 260, 25, PK),
        meal("b12", "Amaranth porridge",           MealType.BREAKFAST, 200, 15, PK),
        meal("b13", "Rice kanji (congee)",         MealType.BREAKFAST, 160, 20, V),
        meal("b14", "Sprouted moong salad",        MealType.BREAKFAST, 140, 10, K,   listOf(VASANTA, GRISHMA)),
        meal("b15", "Sooji upma with peas",        MealType.BREAKFAST, 280, 20, ALL_D),
        meal("b16", "Coconut chutney dosa",        MealType.BREAKFAST, 290, 20, P,   SUMMER_S),
        meal("b17", "Millet flakes poha",          MealType.BREAKFAST, 200, 15, K),
        meal("b18", "Warm ashwagandha milk",        MealType.BREAKFAST, 130,  5, V,   WINTER_S),
        meal("b19", "Papaya with lime & cardamom", MealType.BREAKFAST,  90,  5, PK,  SUMMER_S),
        meal("b20", "Besan chilla",                MealType.BREAKFAST, 240, 20, K),
        meal("b21", "Avocado roti toast",          MealType.BREAKFAST, 310, 10, VP),
        meal("b22", "Poha with peanuts",           MealType.BREAKFAST, 310, 15, V),
        meal("b23", "Quinoa porridge",             MealType.BREAKFAST, 270, 15, PK),
        meal("b24", "Oats idli",                   MealType.BREAKFAST, 220, 25, K),

        // ── LUNCH ───────────────────────────────────────────────────────────
        meal("l01", "Moong dal khichdi",           MealType.LUNCH, 320, 25, ALL_D),
        meal("l02", "Dal makhani with rice",       MealType.LUNCH, 450, 40, V,   WINTER_S),
        meal("l03", "Palak paneer with roti",      MealType.LUNCH, 380, 30, VP),
        meal("l04", "Vegetable millet bowl",       MealType.LUNCH, 290, 25, K),
        meal("l05", "Rajma with brown rice",       MealType.LUNCH, 380, 35, PK,  listOf(VASANTA, SHARAD)),
        meal("l06", "Sambar rice",                 MealType.LUNCH, 340, 20, P),
        meal("l07", "Lauki chana dal",             MealType.LUNCH, 300, 30, PK,  COOL_S),
        meal("l08", "Chana masala with roti",      MealType.LUNCH, 420, 30, ALL_D, listOf(VASANTA, SHARAD)),
        meal("l09", "Methi thepla with curd",      MealType.LUNCH, 350, 25, K),
        meal("l10", "Aloo gobi with roti",         MealType.LUNCH, 370, 25, V,   WINTER_S),
        meal("l11", "Spinach dal with rice",       MealType.LUNCH, 360, 30, P),
        meal("l12", "Curd rice with tadka",        MealType.LUNCH, 280, 15, P,   COOL_S),
        meal("l13", "Bhindi masala with roti",     MealType.LUNCH, 320, 25, K),
        meal("l14", "Pumpkin soup with chapati",   MealType.LUNCH, 280, 20, VP,  WINTER_S),
        meal("l15", "Mattar paneer with rice",     MealType.LUNCH, 430, 35, V,   WINTER_S),
        meal("l16", "Seasonal vegetables dal",     MealType.LUNCH, 300, 20, ALL_D),
        meal("l17", "Lemon rice with peanuts",     MealType.LUNCH, 310, 20, PK,  listOf(GRISHMA)),
        meal("l18", "Bajra khichdi",               MealType.LUNCH, 350, 30, VK,  WINTER_S),
        meal("l19", "Toor dal with jeera rice",    MealType.LUNCH, 360, 25, ALL_D),
        meal("l20", "Vegetable korma with rice",   MealType.LUNCH, 400, 35, V,   WINTER_S),
        meal("l21", "Mushroom curry with roti",    MealType.LUNCH, 340, 30, VK),
        meal("l22", "Kadhi with rice",             MealType.LUNCH, 320, 30, V,   COOL_S),
        meal("l23", "Tindora curry with rice",     MealType.LUNCH, 280, 25, PK),
        meal("l24", "Mixed dal tadka",             MealType.LUNCH, 350, 25, ALL_D),

        // ── SNACK ───────────────────────────────────────────────────────────
        meal("s01", "Banana with cardamom",        MealType.SNACK, 110,  2, V),
        meal("s02", "Roasted makhana",             MealType.SNACK, 130,  5, VP),
        meal("s03", "Coconut water",               MealType.SNACK,  50,  1, P,   SUMMER_S),
        meal("s04", "Dates and almonds",           MealType.SNACK, 160,  2, V,   WINTER_S),
        meal("s05", "Cucumber with mint chutney",  MealType.SNACK,  60,  5, P,   COOL_S),
        meal("s06", "Apple with cinnamon",         MealType.SNACK,  90,  2, V,   WARM_S),
        meal("s07", "Sesame jaggery ladoo",        MealType.SNACK, 180, 10, V,   WINTER_S),
        meal("s08", "Sprouted moong bowl",         MealType.SNACK,  80,  5, K,   listOf(VASANTA, GRISHMA)),
        meal("s09", "Herbal tulsi kadha",          MealType.SNACK,  30, 10, ALL_D, listOf(VARSHA, SHISHIRA)),
        meal("s10", "Puffed rice chaat",           MealType.SNACK, 120,  5, K),
        meal("s11", "Steamed dhokla",              MealType.SNACK, 150, 15, PK),
        meal("s12", "Sweet potato chaat",          MealType.SNACK, 150, 10, V,   WARM_S),
        meal("s13", "Buttermilk with cumin",       MealType.SNACK,  55,  3, P,   COOL_S),
        meal("s14", "Walnut and fig mix",          MealType.SNACK, 200,  2, V,   WINTER_S),
        meal("s15", "Roasted chana",               MealType.SNACK, 130,  5, K),
        meal("s16", "Popped amaranth bar",         MealType.SNACK, 140,  2, PK),
        meal("s17", "Pomegranate seeds",           MealType.SNACK,  80,  3, P,   listOf(SHARAD)),
        meal("s18", "Guduchi herbal tea",          MealType.SNACK,  10, 10, ALL_D),

        // ── DINNER ──────────────────────────────────────────────────────────
        meal("d01", "Palak paneer with roti",      MealType.DINNER, 410, 25, VP),
        meal("d02", "Moong dal soup + chapati",    MealType.DINNER, 340, 20, ALL_D),
        meal("d03", "Vegetable khichdi",           MealType.DINNER, 320, 25, ALL_D),
        meal("d04", "Ragi roti with dal",          MealType.DINNER, 300, 25, K),
        meal("d05", "Broccoli stir-fry with rice", MealType.DINNER, 280, 20, PK,  listOf(VASANTA, GRISHMA)),
        meal("d06", "Masoor dal with chapati",     MealType.DINNER, 360, 25, ALL_D),
        meal("d07", "Pumpkin sabzi + millet roti", MealType.DINNER, 310, 25, V,   WINTER_S),
        meal("d08", "Bitter gourd curry + rice",   MealType.DINNER, 250, 30, PK,  listOf(SHARAD)),
        meal("d09", "Lauki kofta curry",           MealType.DINNER, 320, 35, P),
        meal("d10", "Sesame spinach with roti",    MealType.DINNER, 340, 20, V,   WINTER_S),
        meal("d11", "Ash gourd dal",               MealType.DINNER, 280, 30, P),
        meal("d12", "Ridge gourd with rice",       MealType.DINNER, 260, 25, P,   listOf(VARSHA, SHARAD)),
        meal("d13", "Apple gourd (tinda) curry",   MealType.DINNER, 290, 25, V,   WARM_S),
        meal("d14", "Oats khichdi",                MealType.DINNER, 290, 20, K),
        meal("d15", "Warm turmeric milk + poha",   MealType.DINNER, 200,  8, V,   WINTER_S),
        meal("d16", "Bajra vegetable soup",        MealType.DINNER, 220, 25, VK,  WINTER_S),
        meal("d17", "Quinoa vegetable bowl",       MealType.DINNER, 320, 20, PK),
        meal("d18", "Coriander lentil soup",       MealType.DINNER, 250, 25, P),
        meal("d19", "Besan chilla with chutney",   MealType.DINNER, 280, 20, K),
        meal("d20", "Adai (mixed dal dosa)",       MealType.DINNER, 330, 25, ALL_D),
        meal("d21", "Zucchini moong soup",         MealType.DINNER, 200, 20, PK,  COOL_S),
        meal("d22", "Karela dal + rice",           MealType.DINNER, 290, 30, K,   listOf(SHARAD, VARSHA)),
        meal("d23", "Dill leaves dal",             MealType.DINNER, 270, 25, V),
        meal("d24", "Amaranth greens sabzi",       MealType.DINNER, 200, 20, PK),
    )

    // ─── Seasonal Protocols ─────────────────────────────────────────────────
    val seasonalProtocols: Map<String, SeasonalProtocol> = mapOf(
        HEMANTA to SeasonalProtocol(
            ritu = HEMANTA,
            displayName = "Hemanta Ritu",
            guidance = "Embrace warm, grounding grains and nourishing soups as we transition into early winter. Digestive fire is strongest — enjoy heavier, sustaining meals.",
            shortDesc = "Pre-Winter · Nov – Dec",
            recommendedFoods = listOf("Sesame", "Jaggery", "Ghee", "Root vegetables", "Dal soup"),
            avoidFoods = listOf("Cold foods", "Raw salads", "Excess fruit juice")
        ),
        SHISHIRA to SeasonalProtocol(
            ritu = SHISHIRA,
            displayName = "Shishira Ritu",
            guidance = "Deep winter calls for dense, warming foods. Focus on sesame, ghee and whole grains to maintain body heat and immunity.",
            shortDesc = "Late Winter · Jan – Feb",
            recommendedFoods = listOf("Bajra", "Urad dal", "Sesame oil", "Hot soups", "Dates"),
            avoidFoods = listOf("Bitter foods", "Dry snacks", "Cold drinks")
        ),
        VASANTA to SeasonalProtocol(
            ritu = VASANTA,
            displayName = "Vasanta Ritu",
            guidance = "Spring awakens digestion after winter. Choose light, bitter and astringent foods to clear accumulated Kapha and refresh the system.",
            shortDesc = "Spring · Mar – Apr",
            recommendedFoods = listOf("Barley", "Bitter greens", "Ginger tea", "Sprouts", "Millet"),
            avoidFoods = listOf("Heavy dairy", "Fried foods", "Excess sweets")
        ),
        GRISHMA to SeasonalProtocol(
            ritu = GRISHMA,
            displayName = "Grishma Ritu",
            guidance = "Summer's heat calls for cooling, hydrating foods. Prioritise sweet, bitter and astringent tastes to pacify Pitta and prevent overheating.",
            shortDesc = "Summer · May – Jun",
            recommendedFoods = listOf("Coconut water", "Cucumber", "Mint", "Curd rice", "Rice kanji"),
            avoidFoods = listOf("Spicy foods", "Fermented items", "Excess salt")
        ),
        VARSHA to SeasonalProtocol(
            ritu = VARSHA,
            displayName = "Varsha Ritu",
            guidance = "Monsoon weakens digestive fire. Opt for warm, easily digestible foods with digestive spices like cumin, ginger and asafoetida.",
            shortDesc = "Monsoon · Jul – Aug",
            recommendedFoods = listOf("Warm soup", "Khichdi", "Ginger", "Cumin", "Pomegranate"),
            avoidFoods = listOf("Raw salads", "Leafy greens", "Fish", "Cold water")
        ),
        SHARAD to SeasonalProtocol(
            ritu = SHARAD,
            displayName = "Sharad Ritu",
            guidance = "Autumn pacifies Vata with sweet, bitter foods. Pitta accumulated in summer releases — cooling foods and moderate eating restore balance.",
            shortDesc = "Autumn · Sep – Oct",
            recommendedFoods = listOf("Bitter gourd", "Pomegranate", "Coriander", "Rice", "Moong dal"),
            avoidFoods = listOf("Excess oil", "Sour foods", "Heavy meat")
        )
    )

    // ─── Core API ───────────────────────────────────────────────────────────

    fun currentRitu(): String {
        return when (LocalDate.now().month) {
            Month.NOVEMBER, Month.DECEMBER             -> HEMANTA
            Month.JANUARY,  Month.FEBRUARY             -> SHISHIRA
            Month.MARCH,    Month.APRIL                -> VASANTA
            Month.MAY,      Month.JUNE                 -> GRISHMA
            Month.JULY,     Month.AUGUST               -> VARSHA
            Month.SEPTEMBER, Month.OCTOBER             -> SHARAD
            else                                       -> HEMANTA
        }
    }

    fun getSeasonalProtocol(ritu: String = currentRitu()): SeasonalProtocol =
        seasonalProtocols[ritu] ?: seasonalProtocols.values.first()

    fun generateDayPlan(date: LocalDate, prakriti: String, ritu: String): List<MealItem> {
        val primaryDosha = extractPrimaryDosha(prakriti)
        val dayIndex     = date.dayOfYear

        return MealType.values().map { type ->
            selectMeal(type, primaryDosha, ritu, dayIndex)
        }
    }

    fun getSwapOptions(meal: MealItem, prakriti: String, ritu: String): List<MealItem> {
        val primaryDosha = extractPrimaryDosha(prakriti)
        return allMeals
            .filter { it.type == meal.type && it.id != meal.id }
            .filter { primaryDosha in it.doshaCompatible || it.doshaCompatible.containsAll(listOf("VATA","PITTA","KAPHA")) }
            .filter { ritu in it.seasonCompatible }
            .filter { kotlin.math.abs(it.calories - meal.calories) <= 120 }
            .take(5)
            .ifEmpty {
                allMeals.filter { it.type == meal.type && it.id != meal.id }
                    .filter { ritu in it.seasonCompatible }
                    .take(5)
            }
    }

    // ─── Private helpers ────────────────────────────────────────────────────

    private fun extractPrimaryDosha(prakriti: String): String {
        if (prakriti.isBlank()) return "VATA"
        val upper = prakriti.uppercase().trim()
        return when {
            upper.startsWith("PI") || upper == "PITTA" -> "PITTA"
            upper.startsWith("KA") || upper == "KAPHA" -> "KAPHA"
            else                                        -> "VATA"
        }
    }

    private fun selectMeal(type: MealType, dosha: String, ritu: String, dayIndex: Int): MealItem {
        val candidates = allMeals
            .filter { it.type == type.name }
            .filter { dosha in it.doshaCompatible }
            .filter { ritu in it.seasonCompatible }

        val pool = candidates.ifEmpty {
            allMeals.filter { it.type == type.name && ritu in it.seasonCompatible }
                .ifEmpty { allMeals.filter { it.type == type.name } }
        }

        return pool[dayIndex % pool.size]
    }
}
