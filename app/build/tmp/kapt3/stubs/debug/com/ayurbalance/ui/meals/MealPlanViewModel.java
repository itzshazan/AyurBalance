package com.ayurbalance.ui.meals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00122\u0006\u0010\u0017\u001a\u00020\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J&\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0082@\u00a2\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0013J\u0016\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006$"}, d2 = {"Lcom/ayurbalance/ui/meals/MealPlanViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_state", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ayurbalance/ui/meals/MealPlanState;", "kotlin.jvm.PlatformType", "engine", "Lcom/ayurbalance/ui/meals/AyurvedicMealEngine;", "repository", "Lcom/ayurbalance/ui/meals/MealPlanRepository;", "state", "Landroidx/lifecycle/LiveData;", "getState", "()Landroidx/lifecycle/LiveData;", "buildWeek", "", "Ljava/time/LocalDate;", "anchor", "getSwapOptions", "Lcom/ayurbalance/data/models/MealItem;", "meal", "loadInitialData", "", "loadMealsForDate", "date", "prakriti", "", "ritu", "(Ljava/time/LocalDate;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectDate", "swapMeal", "oldMeal", "newMeal", "app_debug"})
public final class MealPlanViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.ui.meals.MealPlanRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.ui.meals.AyurvedicMealEngine engine = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ayurbalance.ui.meals.MealPlanState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ayurbalance.ui.meals.MealPlanState> state = null;
    
    public MealPlanViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ayurbalance.ui.meals.MealPlanState> getState() {
        return null;
    }
    
    private final void loadInitialData() {
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
    }
    
    public final void swapMeal(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.models.MealItem oldMeal, @org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.models.MealItem newMeal) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ayurbalance.data.models.MealItem> getSwapOptions(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.models.MealItem meal) {
        return null;
    }
    
    private final java.lang.Object loadMealsForDate(java.time.LocalDate date, java.lang.String prakriti, java.lang.String ritu, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.util.List<java.time.LocalDate> buildWeek(java.time.LocalDate anchor) {
        return null;
    }
}