package com.ayurbalance.ui.logfood;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010\u0016\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/ayurbalance/ui/logfood/LogFoodViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_state", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ayurbalance/ui/logfood/LogFoodState;", "kotlin.jvm.PlatformType", "state", "Landroidx/lifecycle/LiveData;", "getState", "()Landroidx/lifecycle/LiveData;", "reset", "", "selectCandidate", "index", "", "setModelReady", "ready", "", "setTab", "tab", "Lcom/ayurbalance/ui/logfood/LogFoodState$Tab;", "updatePredictions", "predictions", "", "Lcom/ayurbalance/ui/logfood/FoodPrediction;", "app_debug"})
public final class LogFoodViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ayurbalance.ui.logfood.LogFoodState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ayurbalance.ui.logfood.LogFoodState> state = null;
    
    public LogFoodViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ayurbalance.ui.logfood.LogFoodState> getState() {
        return null;
    }
    
    public final void setModelReady(boolean ready) {
    }
    
    public final void updatePredictions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ayurbalance.ui.logfood.FoodPrediction> predictions) {
    }
    
    public final void selectCandidate(int index) {
    }
    
    public final void setTab(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.logfood.LogFoodState.Tab tab) {
    }
    
    public final void reset() {
    }
}