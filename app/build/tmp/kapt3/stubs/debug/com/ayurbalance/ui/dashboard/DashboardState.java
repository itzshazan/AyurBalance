package com.ayurbalance.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u00a2\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\t\u0010.\u001a\u00020\u0011H\u00c6\u0003J\t\u0010/\u001a\u00020\u0013H\u00c6\u0003J\t\u00100\u001a\u00020\u0013H\u00c6\u0003J\t\u00101\u001a\u00020\tH\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\tH\u00c6\u0003J\t\u00107\u001a\u00020\tH\u00c6\u0003J\t\u00108\u001a\u00020\tH\u00c6\u0003J\t\u00109\u001a\u00020\tH\u00c6\u0003J\u00a3\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\tH\u00c6\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\tH\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R\u0011\u0010\u0015\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\"\u00a8\u0006@"}, d2 = {"Lcom/ayurbalance/ui/dashboard/DashboardState;", "", "greeting", "", "userName", "userInitials", "avatarUrl", "dayRitu", "streakDays", "", "vataPercent", "pittaPercent", "kaphaPercent", "dominantDosha", "imbalanceAlert", "Lcom/ayurbalance/ui/dashboard/ImbalanceAlert;", "nextMeal", "Lcom/ayurbalance/ui/dashboard/MealRecommendation;", "hydrationCurrentL", "", "hydrationGoalL", "pranaBreathsPerMin", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Lcom/ayurbalance/ui/dashboard/ImbalanceAlert;Lcom/ayurbalance/ui/dashboard/MealRecommendation;FFI)V", "getAvatarUrl", "()Ljava/lang/String;", "getDayRitu", "getDominantDosha", "getGreeting", "getHydrationCurrentL", "()F", "getHydrationGoalL", "getImbalanceAlert", "()Lcom/ayurbalance/ui/dashboard/ImbalanceAlert;", "getKaphaPercent", "()I", "getNextMeal", "()Lcom/ayurbalance/ui/dashboard/MealRecommendation;", "getPittaPercent", "getPranaBreathsPerMin", "getStreakDays", "getUserInitials", "getUserName", "getVataPercent", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class DashboardState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String greeting = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userInitials = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String avatarUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dayRitu = null;
    private final int streakDays = 0;
    private final int vataPercent = 0;
    private final int pittaPercent = 0;
    private final int kaphaPercent = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dominantDosha = null;
    @org.jetbrains.annotations.Nullable()
    private final com.ayurbalance.ui.dashboard.ImbalanceAlert imbalanceAlert = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.ui.dashboard.MealRecommendation nextMeal = null;
    private final float hydrationCurrentL = 0.0F;
    private final float hydrationGoalL = 0.0F;
    private final int pranaBreathsPerMin = 0;
    
    public DashboardState(@org.jetbrains.annotations.NotNull()
    java.lang.String greeting, @org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String userInitials, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String dayRitu, int streakDays, int vataPercent, int pittaPercent, int kaphaPercent, @org.jetbrains.annotations.NotNull()
    java.lang.String dominantDosha, @org.jetbrains.annotations.Nullable()
    com.ayurbalance.ui.dashboard.ImbalanceAlert imbalanceAlert, @org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.dashboard.MealRecommendation nextMeal, float hydrationCurrentL, float hydrationGoalL, int pranaBreathsPerMin) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGreeting() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserInitials() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAvatarUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDayRitu() {
        return null;
    }
    
    public final int getStreakDays() {
        return 0;
    }
    
    public final int getVataPercent() {
        return 0;
    }
    
    public final int getPittaPercent() {
        return 0;
    }
    
    public final int getKaphaPercent() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDominantDosha() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ayurbalance.ui.dashboard.ImbalanceAlert getImbalanceAlert() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ayurbalance.ui.dashboard.MealRecommendation getNextMeal() {
        return null;
    }
    
    public final float getHydrationCurrentL() {
        return 0.0F;
    }
    
    public final float getHydrationGoalL() {
        return 0.0F;
    }
    
    public final int getPranaBreathsPerMin() {
        return 0;
    }
    
    public DashboardState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ayurbalance.ui.dashboard.ImbalanceAlert component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ayurbalance.ui.dashboard.MealRecommendation component12() {
        return null;
    }
    
    public final float component13() {
        return 0.0F;
    }
    
    public final float component14() {
        return 0.0F;
    }
    
    public final int component15() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ayurbalance.ui.dashboard.DashboardState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String greeting, @org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String userInitials, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String dayRitu, int streakDays, int vataPercent, int pittaPercent, int kaphaPercent, @org.jetbrains.annotations.NotNull()
    java.lang.String dominantDosha, @org.jetbrains.annotations.Nullable()
    com.ayurbalance.ui.dashboard.ImbalanceAlert imbalanceAlert, @org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.dashboard.MealRecommendation nextMeal, float hydrationCurrentL, float hydrationGoalL, int pranaBreathsPerMin) {
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