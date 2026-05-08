package com.ayurbalance.ui.analytics;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u000eH\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001e"}, d2 = {"Lcom/ayurbalance/ui/analytics/AnalyticsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityAnalyticsBinding;", "macroAdapter", "Lcom/ayurbalance/ui/analytics/MacroProgressAdapter;", "viewModel", "Lcom/ayurbalance/ui/analytics/AnalyticsViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/analytics/AnalyticsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "animateEntrance", "", "formatDeltaPercent", "", "delta", "", "formatDeltaPoints", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupBottomNav", "setupRecyclerView", "showContent", "state", "Lcom/ayurbalance/ui/analytics/AnalyticsState;", "showEmpty", "app_debug"})
public final class AnalyticsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivityAnalyticsBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ayurbalance.ui.analytics.MacroProgressAdapter macroAdapter;
    
    public AnalyticsActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.analytics.AnalyticsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void showEmpty() {
    }
    
    private final void showContent(com.ayurbalance.ui.analytics.AnalyticsState state) {
    }
    
    private final java.lang.String formatDeltaPercent(float delta) {
        return null;
    }
    
    private final java.lang.String formatDeltaPoints(float delta) {
        return null;
    }
    
    private final void animateEntrance() {
    }
    
    private final void setupBottomNav() {
    }
}