package com.ayurbalance.ui.meals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J,\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010$JD\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b*\u0010+J$\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010-\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0018J,\u0010.\u001a\u00020\'2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010 \u001a\u00020\u0018H\u0082@\u00a2\u0006\u0002\u00100R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00061"}, d2 = {"Lcom/ayurbalance/ui/meals/MealPlanRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cacheTtlMs", "", "dao", "Lcom/ayurbalance/data/local/MealPlanDao;", "getDao", "()Lcom/ayurbalance/data/local/MealPlanDao;", "dao$delegate", "Lkotlin/Lazy;", "db", "Lcom/ayurbalance/data/local/AyurBalanceDatabase;", "getDb", "()Lcom/ayurbalance/data/local/AyurBalanceDatabase;", "db$delegate", "engine", "Lcom/ayurbalance/ui/meals/AyurvedicMealEngine;", "fmt", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "fetchPrakriti", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMealsForDate", "", "Lcom/ayurbalance/data/models/MealItem;", "date", "Ljava/time/LocalDate;", "prakriti", "ritu", "(Ljava/time/LocalDate;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSeasonalProtocol", "Lcom/ayurbalance/data/models/SeasonalProtocol;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSwap", "Lkotlin/Result;", "", "oldMeal", "newMeal", "saveSwap-hUnOzRk", "(Ljava/time/LocalDate;Lcom/ayurbalance/data/models/MealItem;Lcom/ayurbalance/data/models/MealItem;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "swapOptions", "meal", "syncToSupabase", "meals", "(Ljava/time/LocalDate;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class MealPlanRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy dao$delegate = null;
    private final java.time.format.DateTimeFormatter fmt = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.ui.meals.AyurvedicMealEngine engine = null;
    private final long cacheTtlMs = 43200000L;
    
    public MealPlanRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final com.ayurbalance.data.local.AyurBalanceDatabase getDb() {
        return null;
    }
    
    private final com.ayurbalance.data.local.MealPlanDao getDao() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMealsForDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    java.lang.String prakriti, @org.jetbrains.annotations.NotNull()
    java.lang.String ritu, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.data.models.MealItem>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSeasonalProtocol(@org.jetbrains.annotations.NotNull()
    java.lang.String ritu, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ayurbalance.data.models.SeasonalProtocol> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchPrakriti(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ayurbalance.data.models.MealItem> swapOptions(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.models.MealItem meal, @org.jetbrains.annotations.NotNull()
    java.lang.String prakriti, @org.jetbrains.annotations.NotNull()
    java.lang.String ritu) {
        return null;
    }
    
    private final java.lang.Object syncToSupabase(java.time.LocalDate date, java.util.List<com.ayurbalance.data.models.MealItem> meals, java.lang.String ritu, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}