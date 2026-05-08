package com.ayurbalance.ui.reminders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002JH\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\rH\u0002\u00a8\u0006\u0018"}, d2 = {"Lcom/ayurbalance/ui/reminders/ReminderScheduler;", "", "()V", "cancelAll", "", "context", "Landroid/content/Context;", "cancelTag", "tag", "", "initialDelayMs", "", "targetHour", "", "targetMinute", "scheduleAll", "scheduleHydrationChecks", "scheduleMealReminder", "title", "body", "hour", "minute", "channel", "notifId", "app_debug"})
public final class ReminderScheduler {
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.reminders.ReminderScheduler INSTANCE = null;
    
    private ReminderScheduler() {
        super();
    }
    
    public final void scheduleAll(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void scheduleMealReminder(android.content.Context context, java.lang.String tag, java.lang.String title, java.lang.String body, int hour, int minute, java.lang.String channel, int notifId) {
    }
    
    private final void scheduleHydrationChecks(android.content.Context context) {
    }
    
    public final void cancelAll(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void cancelTag(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
    }
    
    private final long initialDelayMs(int targetHour, int targetMinute) {
        return 0L;
    }
}