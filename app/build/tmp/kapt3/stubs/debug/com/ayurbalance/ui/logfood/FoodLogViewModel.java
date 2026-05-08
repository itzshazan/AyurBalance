package com.ayurbalance.ui.logfood;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0012\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/ayurbalance/ui/logfood/FoodLogViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_state", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ayurbalance/ui/logfood/FoodLogState;", "kotlin.jvm.PlatformType", "state", "Landroidx/lifecycle/LiveData;", "getState", "()Landroidx/lifecycle/LiveData;", "fetchDailyProgress", "", "load", "prediction", "Lcom/ayurbalance/ui/logfood/FoodPrediction;", "mealType", "", "saveFoodLog", "nutrition", "Lcom/ayurbalance/ui/logfood/FoodNutrition;", "app_debug"})
public final class FoodLogViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ayurbalance.ui.logfood.FoodLogState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ayurbalance.ui.logfood.FoodLogState> state = null;
    
    public FoodLogViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ayurbalance.ui.logfood.FoodLogState> getState() {
        return null;
    }
    
    public final void load(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.logfood.FoodPrediction prediction, @org.jetbrains.annotations.NotNull()
    java.lang.String mealType) {
    }
    
    private final void saveFoodLog(com.ayurbalance.ui.logfood.FoodPrediction prediction, com.ayurbalance.ui.logfood.FoodNutrition nutrition, java.lang.String mealType) {
    }
    
    private final void fetchDailyProgress() {
    }
}