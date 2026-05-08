package com.ayurbalance.ui.reminders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ8\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/ayurbalance/ui/reminders/ReminderNotificationHelper;", "", "()V", "CHANNEL_DINACHARYA", "", "CHANNEL_HYDRATION", "CHANNEL_MEAL", "CHANNEL_VIKRITI", "CHANNEL_WELLNESS", "createChannels", "", "context", "Landroid/content/Context;", "showNotification", "notificationId", "", "channelId", "title", "body", "deepLinkAction", "app_debug"})
public final class ReminderNotificationHelper {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_MEAL = "ayurbalance_meal";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_HYDRATION = "ayurbalance_hydration";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_DINACHARYA = "ayurbalance_dinacharya";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_VIKRITI = "ayurbalance_vikriti";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_WELLNESS = "ayurbalance_wellness";
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.reminders.ReminderNotificationHelper INSTANCE = null;
    
    private ReminderNotificationHelper() {
        super();
    }
    
    public final void createChannels(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void showNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int notificationId, @org.jetbrains.annotations.NotNull()
    java.lang.String channelId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String body, @org.jetbrains.annotations.NotNull()
    java.lang.String deepLinkAction) {
    }
}