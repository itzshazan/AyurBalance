package com.ayurbalance.ui.meals;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\t\u0010!\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\t\u0010#\u001a\u00020\u000eH\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tH\u00c6\u0003Ji\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010&\u001a\u00020\u000e2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\tH\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0015R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006+"}, d2 = {"Lcom/ayurbalance/ui/meals/MealPlanState;", "", "selectedDate", "Ljava/time/LocalDate;", "currentWeek", "", "meals", "Lcom/ayurbalance/data/models/MealItem;", "prakriti", "", "ritu", "seasonalProtocol", "Lcom/ayurbalance/data/models/SeasonalProtocol;", "isLoading", "", "error", "(Ljava/time/LocalDate;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/ayurbalance/data/models/SeasonalProtocol;ZLjava/lang/String;)V", "getCurrentWeek", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "()Z", "getMeals", "getPrakriti", "getRitu", "getSeasonalProtocol", "()Lcom/ayurbalance/data/models/SeasonalProtocol;", "getSelectedDate", "()Ljava/time/LocalDate;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class MealPlanState {
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate selectedDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.time.LocalDate> currentWeek = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.ayurbalance.data.models.MealItem> meals = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String prakriti = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String ritu = null;
    @org.jetbrains.annotations.Nullable()
    private final com.ayurbalance.data.models.SeasonalProtocol seasonalProtocol = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public MealPlanState(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate selectedDate, @org.jetbrains.annotations.NotNull()
    java.util.List<java.time.LocalDate> currentWeek, @org.jetbrains.annotations.NotNull()
    java.util.List<com.ayurbalance.data.models.MealItem> meals, @org.jetbrains.annotations.NotNull()
    java.lang.String prakriti, @org.jetbrains.annotations.NotNull()
    java.lang.String ritu, @org.jetbrains.annotations.Nullable()
    com.ayurbalance.data.models.SeasonalProtocol seasonalProtocol, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate getSelectedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.time.LocalDate> getCurrentWeek() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ayurbalance.data.models.MealItem> getMeals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPrakriti() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRitu() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ayurbalance.data.models.SeasonalProtocol getSeasonalProtocol() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public MealPlanState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.time.LocalDate> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ayurbalance.data.models.MealItem> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ayurbalance.data.models.SeasonalProtocol component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ayurbalance.ui.meals.MealPlanState copy(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate selectedDate, @org.jetbrains.annotations.NotNull()
    java.util.List<java.time.LocalDate> currentWeek, @org.jetbrains.annotations.NotNull()
    java.util.List<com.ayurbalance.data.models.MealItem> meals, @org.jetbrains.annotations.NotNull()
    java.lang.String prakriti, @org.jetbrains.annotations.NotNull()
    java.lang.String ritu, @org.jetbrains.annotations.Nullable()
    com.ayurbalance.data.models.SeasonalProtocol seasonalProtocol, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}