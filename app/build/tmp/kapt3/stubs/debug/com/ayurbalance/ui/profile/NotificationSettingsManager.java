package com.ayurbalance.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J@\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019JH\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/ayurbalance/ui/profile/NotificationSettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "notifManager", "Landroid/app/NotificationManager;", "buildIntent", "Landroid/app/PendingIntent;", "requestCode", "", "title", "", "message", "channel", "cancel", "", "am", "Landroid/app/AlarmManager;", "createChannels", "scheduleDaily", "hour", "minute", "scheduleDinacharyaReminders", "enabled", "", "scheduleHydrationReminders", "scheduleMealReminders", "scheduleRepeating", "intervalMs", "", "Companion", "app_debug"})
public final class NotificationSettingsManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_MEAL = "ayur_meal_reminders";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_HYDRATION = "ayur_hydration_reminders";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_DINACHARYA = "ayur_dinacharya_reminders";
    private static final int REQ_MEAL_1 = 1001;
    private static final int REQ_MEAL_2 = 1002;
    private static final int REQ_MEAL_3 = 1003;
    private static final int REQ_HYDRATION = 1010;
    private static final int REQ_DINACHARYA = 1020;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TITLE = "notif_title";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MESSAGE = "notif_message";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CHANNEL = "notif_channel";
    @org.jetbrains.annotations.NotNull()
    private final android.app.NotificationManager notifManager = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.profile.NotificationSettingsManager.Companion Companion = null;
    
    public NotificationSettingsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void createChannels() {
    }
    
    public final void scheduleMealReminders(boolean enabled) {
    }
    
    public final void scheduleHydrationReminders(boolean enabled) {
    }
    
    public final void scheduleDinacharyaReminders(boolean enabled) {
    }
    
    private final void scheduleDaily(android.app.AlarmManager am, int requestCode, int hour, int minute, java.lang.String title, java.lang.String message, java.lang.String channel) {
    }
    
    private final void scheduleRepeating(android.app.AlarmManager am, int requestCode, int hour, int minute, java.lang.String title, java.lang.String message, java.lang.String channel, long intervalMs) {
    }
    
    private final void cancel(android.app.AlarmManager am, int requestCode) {
    }
    
    private final android.app.PendingIntent buildIntent(int requestCode, java.lang.String title, java.lang.String message, java.lang.String channel) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/ayurbalance/ui/profile/NotificationSettingsManager$Companion;", "", "()V", "CHANNEL_DINACHARYA", "", "CHANNEL_HYDRATION", "CHANNEL_MEAL", "EXTRA_CHANNEL", "EXTRA_MESSAGE", "EXTRA_TITLE", "REQ_DINACHARYA", "", "REQ_HYDRATION", "REQ_MEAL_1", "REQ_MEAL_2", "REQ_MEAL_3", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}