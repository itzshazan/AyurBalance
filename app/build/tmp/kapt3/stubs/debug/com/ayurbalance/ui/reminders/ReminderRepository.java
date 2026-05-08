package com.ayurbalance.ui.reminders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u000e\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0006\u0010\u0016\u001a\u00020\fJ\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0018J\u0016\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010 J\u000e\u0010!\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0006\u0010\"\u001a\u00020\u0013J\u000e\u0010#\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\fJ\u001e\u0010$\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J\u000e\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u001bJ\b\u0010*\u001a\u00020+H\u0002J\u000e\u0010,\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/ayurbalance/ui/reminders/ReminderRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dao", "Lcom/ayurbalance/data/local/ReminderDao;", "db", "Lcom/ayurbalance/data/local/AyurBalanceDatabase;", "pref", "Landroid/content/SharedPreferences;", "currentInsight", "", "dosha", "currentSeasonalTip", "defaultReminders", "", "Lcom/ayurbalance/data/local/ReminderEntity;", "ensureDefaultReminders", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReminders", "getDominantDosha", "getHydrationCurrent", "", "getHydrationGoal", "getStreakDays", "", "logWater", "addLitres", "markComplete", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetDailyCompletions", "resetHydrationIfNewDay", "setDominantDosha", "setEnabled", "enabled", "", "(IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "streakMessage", "days", "todayStartMs", "", "vikritiCheckedToday", "app_debug"})
public final class ReminderRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.data.local.AyurBalanceDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.data.local.ReminderDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences pref = null;
    
    public ReminderRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureDefaultReminders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllReminders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.data.local.ReminderEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markComplete(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setEnabled(int id, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resetDailyCompletions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final float getHydrationCurrent() {
        return 0.0F;
    }
    
    public final void logWater(float addLitres) {
    }
    
    public final float getHydrationGoal(@org.jetbrains.annotations.NotNull()
    java.lang.String dosha) {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDominantDosha() {
        return null;
    }
    
    public final void setDominantDosha(@org.jetbrains.annotations.NotNull()
    java.lang.String dosha) {
    }
    
    public final int getStreakDays() {
        return 0;
    }
    
    public final void resetHydrationIfNewDay() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentInsight(@org.jetbrains.annotations.NotNull()
    java.lang.String dosha) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentSeasonalTip(@org.jetbrains.annotations.NotNull()
    java.lang.String dosha) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String streakMessage(int days) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object vikritiCheckedToday(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final long todayStartMs() {
        return 0L;
    }
    
    private final java.util.List<com.ayurbalance.data.local.ReminderEntity> defaultReminders() {
        return null;
    }
}