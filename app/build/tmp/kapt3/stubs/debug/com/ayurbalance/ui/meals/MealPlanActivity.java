package com.ayurbalance.ui.meals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u000fH\u0002J\u0012\u0010!\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0018\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\u0019H\u0016J\b\u0010\'\u001a\u00020\u000fH\u0002J\b\u0010(\u001a\u00020\u000fH\u0002J\u001e\u0010)\u001a\u00020\u000f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00182\u0006\u0010+\u001a\u00020\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006,"}, d2 = {"Lcom/ayurbalance/ui/meals/MealPlanActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/ayurbalance/ui/meals/SwapMealBottomSheet$SwapListener;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityMealPlanBinding;", "mealAdapter", "Lcom/ayurbalance/ui/meals/MealCardAdapter;", "viewModel", "Lcom/ayurbalance/ui/meals/MealPlanViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/meals/MealPlanViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyDayStyle", "", "container", "Landroid/widget/LinearLayout;", "tvNum", "Landroid/widget/TextView;", "isSelected", "", "bindMeals", "meals", "", "Lcom/ayurbalance/data/models/MealItem;", "bindSeasonalCard", "protocol", "Lcom/ayurbalance/data/models/SeasonalProtocol;", "buildDayView", "date", "Ljava/time/LocalDate;", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMealSwapped", "oldMeal", "newMeal", "setupBottomNav", "setupRecyclerView", "updateWeekStrip", "week", "selected", "app_debug"})
public final class MealPlanActivity extends androidx.appcompat.app.AppCompatActivity implements com.ayurbalance.ui.meals.SwapMealBottomSheet.SwapListener {
    private com.ayurbalance.databinding.ActivityMealPlanBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ayurbalance.ui.meals.MealCardAdapter mealAdapter;
    
    public MealPlanActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.meals.MealPlanViewModel getViewModel() {
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
    
    private final void bindMeals(java.util.List<com.ayurbalance.data.models.MealItem> meals) {
    }
    
    private final void bindSeasonalCard(com.ayurbalance.data.models.SeasonalProtocol protocol) {
    }
    
    private final void updateWeekStrip(java.util.List<java.time.LocalDate> week, java.time.LocalDate selected) {
    }
    
    private final android.widget.LinearLayout buildDayView(java.time.LocalDate date, boolean isSelected) {
        return null;
    }
    
    private final void applyDayStyle(android.widget.LinearLayout container, android.widget.TextView tvNum, boolean isSelected) {
    }
    
    @java.lang.Override()
    public void onMealSwapped(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.models.MealItem oldMeal, @org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.models.MealItem newMeal) {
    }
    
    private final void setupBottomNav() {
    }
}