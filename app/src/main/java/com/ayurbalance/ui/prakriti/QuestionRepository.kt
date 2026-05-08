package com.ayurbalance.ui.prakriti

object QuestionRepository {

    val questions: List<PrakritiQuestion> = listOf(

        // ══════════════════════════════════════════
        //  DIMENSION 1: PHYSICAL CONSTITUTION (Q1–6)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 1,
            dimension = "PHYSICAL CONSTITUTION",
            questionText = "How would you describe your body frame?",
            vataOption = "Thin, lean, or lanky — difficult to gain weight",
            pittaOption = "Medium, athletic, and well-proportioned",
            kaphaOption = "Broad, stocky, or large — tends to gain weight easily"
        ),
        PrakritiQuestion(
            id = 2,
            dimension = "PHYSICAL CONSTITUTION",
            questionText = "How is your skin typically?",
            vataOption = "Dry, rough, or flaky — especially in cold weather",
            pittaOption = "Warm, oily, or prone to redness and sensitivity",
            kaphaOption = "Smooth, cool, moist, and thick"
        ),
        PrakritiQuestion(
            id = 3,
            dimension = "PHYSICAL CONSTITUTION",
            questionText = "How would you describe your hair?",
            vataOption = "Dry, thin, or frizzy — tends to break easily",
            pittaOption = "Fine, oily, or prone to early greying",
            kaphaOption = "Thick, lustrous, wavy, and naturally oily"
        ),
        PrakritiQuestion(
            id = 4,
            dimension = "PHYSICAL CONSTITUTION",
            questionText = "How does your body respond to weight changes?",
            vataOption = "Difficult to gain weight even with a good appetite",
            pittaOption = "Gain and lose weight relatively easily",
            kaphaOption = "Gain weight easily and struggle to lose it"
        ),
        PrakritiQuestion(
            id = 5,
            dimension = "PHYSICAL CONSTITUTION",
            questionText = "How would you describe your joints?",
            vataOption = "Thin, prominent, and prone to cracking or popping",
            pittaOption = "Moderately built, flexible, and occasionally inflamed",
            kaphaOption = "Large, well-padded, and generally stable"
        ),
        PrakritiQuestion(
            id = 6,
            dimension = "PHYSICAL CONSTITUTION",
            questionText = "What is your natural body temperature?",
            vataOption = "Often feel cold — especially in hands and feet",
            pittaOption = "Run warm — feel hot or sweaty frequently",
            kaphaOption = "Moderate — comfortable and adaptable in most climates"
        ),

        // ══════════════════════════════════════════
        //  DIMENSION 2: DIGESTIVE TENDENCIES (Q7–12)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 7,
            dimension = "DIGESTIVE TENDENCIES",
            questionText = "How would you describe your appetite?",
            vataOption = "Variable and irregular — strong at times, absent at others",
            pittaOption = "Strong and consistent — irritable if meals are delayed",
            kaphaOption = "Moderate and steady — comfortable skipping meals"
        ),
        PrakritiQuestion(
            id = 8,
            dimension = "DIGESTIVE TENDENCIES",
            questionText = "How quickly do you digest food?",
            vataOption = "Irregular — sometimes fast, sometimes sluggish with bloating",
            pittaOption = "Fast and efficient — feel hungry again within a few hours",
            kaphaOption = "Slow and steady — feel full for long periods"
        ),
        PrakritiQuestion(
            id = 9,
            dimension = "DIGESTIVE TENDENCIES",
            questionText = "How are your bowel movements typically?",
            vataOption = "Irregular — tends toward dryness, constipation, or gas",
            pittaOption = "Regular — sometimes loose or more frequent than usual",
            kaphaOption = "Slow but consistent — movements are heavy and well-formed"
        ),
        PrakritiQuestion(
            id = 10,
            dimension = "DIGESTIVE TENDENCIES",
            questionText = "How do you respond when you are hungry?",
            vataOption = "Easily distracted from hunger — often forget to eat",
            pittaOption = "Cannot delay meals — feel irritable or faint when hungry",
            kaphaOption = "Patient with hunger — rarely affected by delayed meals"
        ),
        PrakritiQuestion(
            id = 11,
            dimension = "DIGESTIVE TENDENCIES",
            questionText = "What flavours do you naturally crave?",
            vataOption = "Sweet, sour, and salty — warming and grounding tastes",
            pittaOption = "Sweet, bitter, and astringent — cooling and refreshing tastes",
            kaphaOption = "Pungent, bitter, and astringent — light and stimulating tastes"
        ),
        PrakritiQuestion(
            id = 12,
            dimension = "DIGESTIVE TENDENCIES",
            questionText = "How does your stomach respond to different foods?",
            vataOption = "Sensitive — prone to bloating, gas, or cramping",
            pittaOption = "Strong but reactive — prone to heartburn or inflammation",
            kaphaOption = "Slow — tends toward heaviness or congestion after rich meals"
        ),

        // ══════════════════════════════════════════
        //  DIMENSION 3: SLEEP & ENERGY (Q13–18)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 13,
            dimension = "SLEEP & ENERGY",
            questionText = "How many hours of sleep do you naturally need?",
            vataOption = "6–7 hours — light sleeper, easily disturbed during the night",
            pittaOption = "7–8 hours — moderate sleeper, often with vivid dreams",
            kaphaOption = "8–10 hours — deep, heavy sleeper who is difficult to wake"
        ),
        PrakritiQuestion(
            id = 14,
            dimension = "SLEEP & ENERGY",
            questionText = "How would you describe the quality of your sleep?",
            vataOption = "Light and interrupted — difficult to fall or stay asleep",
            pittaOption = "Moderate — intense, goal-oriented, or problem-solving dreams",
            kaphaOption = "Deep and prolonged — feel heavy or groggy upon waking"
        ),
        PrakritiQuestion(
            id = 15,
            dimension = "SLEEP & ENERGY",
            questionText = "How do you feel when you first wake up?",
            vataOption = "Alert or anxious almost immediately after waking",
            pittaOption = "Purposeful and ready to begin the day",
            kaphaOption = "Slow and groggy — need significant time before feeling awake"
        ),
        PrakritiQuestion(
            id = 16,
            dimension = "SLEEP & ENERGY",
            questionText = "How would you describe your physical stamina?",
            vataOption = "Quick bursts of energy that deplete easily",
            pittaOption = "Moderate-to-high endurance with a competitive drive",
            kaphaOption = "Slow to warm up but able to sustain effort for long periods"
        ),
        PrakritiQuestion(
            id = 17,
            dimension = "SLEEP & ENERGY",
            questionText = "How do you recover after intense physical effort?",
            vataOption = "Slowly — feel worn out and need extended rest",
            pittaOption = "Moderately — tend to push through fatigue with willpower",
            kaphaOption = "Physically quick to recover — but mentally sluggish afterward"
        ),
        PrakritiQuestion(
            id = 18,
            dimension = "SLEEP & ENERGY",
            questionText = "What are your dreams typically like?",
            vataOption = "Movement-filled, fearful, or fragmented",
            pittaOption = "Intense, vivid, conflict-driven, or goal-oriented",
            kaphaOption = "Calm, watery, sentimental, or rarely remembered"
        ),

        // ══════════════════════════════════════════
        //  DIMENSION 4: MENTAL TEMPERAMENT (Q19–24)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 19,
            dimension = "MENTAL TEMPERAMENT",
            questionText = "How would you describe your memory?",
            vataOption = "Quick to learn, quick to forget — memory is short-term",
            pittaOption = "Sharp and precise — holds detailed information well",
            kaphaOption = "Slow to absorb, but retains knowledge for years"
        ),
        PrakritiQuestion(
            id = 20,
            dimension = "MENTAL TEMPERAMENT",
            questionText = "How do you typically make decisions?",
            vataOption = "Quickly, but frequently changes mind — indecisive under pressure",
            pittaOption = "Decisively and confidently — stands firmly by choices",
            kaphaOption = "Slowly and deliberately — carefully weighs every option"
        ),
        PrakritiQuestion(
            id = 21,
            dimension = "MENTAL TEMPERAMENT",
            questionText = "How do you prefer to learn new things?",
            vataOption = "Through movement, conversation, and variety",
            pittaOption = "Through reading, analysis, and structured systems",
            kaphaOption = "Through repetition, hands-on practice, and real examples"
        ),
        PrakritiQuestion(
            id = 22,
            dimension = "MENTAL TEMPERAMENT",
            questionText = "How do you respond emotionally to stress?",
            vataOption = "Anxiety, worry, or restless overthinking",
            pittaOption = "Irritability, frustration, or critical behaviour",
            kaphaOption = "Withdrawal, sadness, or emotional suppression"
        ),
        PrakritiQuestion(
            id = 23,
            dimension = "MENTAL TEMPERAMENT",
            questionText = "How would you describe your ability to concentrate?",
            vataOption = "Scattered — creative but easily distracted",
            pittaOption = "Intense and sharp — can over-focus or become rigid",
            kaphaOption = "Calm and steady — takes time to start, but completes tasks"
        ),
        PrakritiQuestion(
            id = 24,
            dimension = "MENTAL TEMPERAMENT",
            questionText = "How would you describe your communication style?",
            vataOption = "Fast, enthusiastic, and topic-jumping — talks frequently",
            pittaOption = "Direct, sharp, confident, and sometimes argumentative",
            kaphaOption = "Warm, measured, and thoughtful — speaks slowly and carefully"
        ),

        // ══════════════════════════════════════════
        //  DIMENSION 5: SENSORY SENSITIVITIES (Q25–30)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 25,
            dimension = "SENSORY SENSITIVITIES",
            questionText = "How do you tolerate heat?",
            vataOption = "Prefer warmth — cold is far more uncomfortable than heat",
            pittaOption = "Very sensitive to heat — prefer cool or air-conditioned spaces",
            kaphaOption = "Generally comfortable — warm environments suit me well"
        ),
        PrakritiQuestion(
            id = 26,
            dimension = "SENSORY SENSITIVITIES",
            questionText = "How do you tolerate cold weather?",
            vataOption = "Poorly — feel cold constantly and require extra layers",
            pittaOption = "Well — feel refreshed and energised in cooler climates",
            kaphaOption = "Moderately — prefer dry warmth over cold and dampness"
        ),
        PrakritiQuestion(
            id = 27,
            dimension = "SENSORY SENSITIVITIES",
            questionText = "How sensitive are you to noise and loud sounds?",
            vataOption = "Highly sensitive — noise and chaos feel overwhelming",
            pittaOption = "Moderately sensitive — sharp sounds trigger irritability",
            kaphaOption = "Low sensitivity — generally unbothered by surrounding noise"
        ),
        PrakritiQuestion(
            id = 28,
            dimension = "SENSORY SENSITIVITIES",
            questionText = "How sensitive are you to bright light?",
            vataOption = "Moderately sensitive — especially during anxious or tired states",
            pittaOption = "Very sensitive — bright light causes discomfort or headaches",
            kaphaOption = "Low sensitivity — generally comfortable in bright environments"
        ),
        PrakritiQuestion(
            id = 29,
            dimension = "SENSORY SENSITIVITIES",
            questionText = "How does your body respond to touch?",
            vataOption = "Dry or rough skin — light or unexpected touch can be unpleasant",
            pittaOption = "Warm and reactive — prone to rashes or skin sensitivity",
            kaphaOption = "Receptive to touch — skin tends to be cool and smooth"
        ),
        PrakritiQuestion(
            id = 30,
            dimension = "SENSORY SENSITIVITIES",
            questionText = "How do you adapt to new environments or changes in routine?",
            vataOption = "Quickly excited but soon overwhelmed — adjustment is irregular",
            pittaOption = "Adapts fast with an urge to control or reorganise the space",
            kaphaOption = "Slow and steady adaptation — strongly dislikes abrupt change"
        ),

        // ══════════════════════════════════════════
        //  DIMENSION 6: METABOLIC INDICATORS (Q31–36)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 31,
            dimension = "METABOLIC INDICATORS",
            questionText = "How much water do you typically feel the need to drink?",
            vataOption = "Often forget to drink — become dehydrated without noticing",
            pittaOption = "Very thirsty — drink large amounts of water throughout the day",
            kaphaOption = "Moderately thirsty — rarely feel an intense urge to drink"
        ),
        PrakritiQuestion(
            id = 32,
            dimension = "METABOLIC INDICATORS",
            questionText = "How much do you perspire during activity?",
            vataOption = "Minimal — dry skin even during moderate exercise",
            pittaOption = "Profuse sweating with strong odour, even with mild activity",
            kaphaOption = "Moderate — not excessive, but can be somewhat sticky"
        ),
        PrakritiQuestion(
            id = 33,
            dimension = "METABOLIC INDICATORS",
            questionText = "How would you describe your overall metabolic rate?",
            vataOption = "Irregular — fast at some times, sluggish at others",
            pittaOption = "High — burns calories quickly, can eat substantially",
            kaphaOption = "Slow and efficient — gains energy from small amounts of food"
        ),
        PrakritiQuestion(
            id = 34,
            dimension = "METABOLIC INDICATORS",
            questionText = "How is your energy level distributed throughout the day?",
            vataOption = "Fluctuating — high at times, but crashes suddenly and unexpectedly",
            pittaOption = "Strong peak in the middle of the day — fades toward evening",
            kaphaOption = "Steady but low-starting — builds slowly and consistently"
        ),
        PrakritiQuestion(
            id = 35,
            dimension = "METABOLIC INDICATORS",
            questionText = "How long do you typically feel full after eating a meal?",
            vataOption = "Short intervals — digests quickly or feels hungry irregularly",
            pittaOption = "2–3 hours — strong, sharp hunger returns relatively fast",
            kaphaOption = "4–6 hours or more — feels full and satisfied for long periods"
        ),
        PrakritiQuestion(
            id = 36,
            dimension = "METABOLIC INDICATORS",
            questionText = "How would you describe your body odour?",
            vataOption = "Minimal or neutral — not particularly noticeable",
            pittaOption = "Sharp, pungent, or sour — noticeable especially when active",
            kaphaOption = "Mild and steady — can sometimes be slightly sweet or musty"
        ),

        // ══════════════════════════════════════════
        //  DIMENSION 7: LIFESTYLE & ACTIVITY (Q37–42)
        // ══════════════════════════════════════════

        PrakritiQuestion(
            id = 37,
            dimension = "LIFESTYLE & ACTIVITY",
            questionText = "What type of physical activity do you naturally enjoy?",
            vataOption = "Light, varied, and creative — yoga, dance, or walking",
            pittaOption = "Intense, competitive, and goal-driven — running or team sports",
            kaphaOption = "Slow, rhythmic, and sustained — swimming, hiking, or cycling"
        ),
        PrakritiQuestion(
            id = 38,
            dimension = "LIFESTYLE & ACTIVITY",
            questionText = "How would you describe your approach to work?",
            vataOption = "Creative bursts followed by difficulty with sustained follow-through",
            pittaOption = "Organised, driven, and often demanding of self and others",
            kaphaOption = "Consistent and methodical — works steadily at a reliable pace"
        ),
        PrakritiQuestion(
            id = 39,
            dimension = "LIFESTYLE & ACTIVITY",
            questionText = "How do you typically respond when under stress?",
            vataOption = "Anxiety, restlessness, and rapid nervous thinking",
            pittaOption = "Anger, frustration, and a strong urge to take control",
            kaphaOption = "Emotional withdrawal, silence, or comfort-seeking behaviour"
        ),
        PrakritiQuestion(
            id = 40,
            dimension = "LIFESTYLE & ACTIVITY",
            questionText = "How do you behave in social situations?",
            vataOption = "Energetic and enthusiastic — but mentally exhausted afterward",
            pittaOption = "Confident, assertive, and naturally takes the lead",
            kaphaOption = "Warm, supportive, and prefers close, familiar company"
        ),
        PrakritiQuestion(
            id = 41,
            dimension = "LIFESTYLE & ACTIVITY",
            questionText = "How do you manage money and personal resources?",
            vataOption = "Spends impulsively on varied interests — irregular savings habits",
            pittaOption = "Strategic and purposeful — financially ambitious and organised",
            kaphaOption = "Conservative and cautious — holds onto resources carefully"
        ),
        PrakritiQuestion(
            id = 42,
            dimension = "LIFESTYLE & ACTIVITY",
            questionText = "How consistent are you with maintaining a daily routine?",
            vataOption = "Poor consistency — routines feel limiting and creativity is stifled",
            pittaOption = "Excellent — follows structured schedules with strong discipline",
            kaphaOption = "Good consistency — but strongly resistant to any change in routine"
        )
    )
}
