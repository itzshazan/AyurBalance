package com.ayurbalance.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\u001a\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0016\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\tH\u0082@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\tH\u0082@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\tH\u0002J2\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u001d2\u0006\u0010\u0013\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0010H\u0082@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$J\u0014\u0010%\u001a\u00020\u000b*\u00020\u00102\u0006\u0010\"\u001a\u00020\tH\u0002J\u001b\u0010&\u001a\u0004\u0018\u00010\u000b*\u00020\u00102\u0006\u0010\"\u001a\u00020\tH\u0002\u00a2\u0006\u0002\u0010\'J\u0014\u0010(\u001a\u00020\t*\u00020\u00102\u0006\u0010\"\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "buildConstitutionLabel", "", "vata", "", "pitta", "kapha", "buildDisplayName", "metadata", "Lkotlinx/serialization/json/JsonObject;", "email", "calculateStreak", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchUserProfile", "loadProfile", "Lcom/ayurbalance/ui/profile/ProfileState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseConditions", "", "raw", "resolvePrakritiPercentages", "Lkotlin/Triple;", "profile", "(Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveNotificationPref", "", "key", "enabled", "", "intOf", "intOfOrNull", "(Lkotlinx/serialization/json/JsonObject;Ljava/lang/String;)Ljava/lang/Integer;", "strOf", "app_debug"})
public final class ProfileRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final android.content.SharedPreferences prefs = null;
    
    public ProfileRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ayurbalance.ui.profile.ProfileState> $completion) {
        return null;
    }
    
    public final void saveNotificationPref(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean enabled) {
    }
    
    private final java.lang.String buildDisplayName(kotlinx.serialization.json.JsonObject metadata, java.lang.String email) {
        return null;
    }
    
    private final java.lang.Object fetchUserProfile(java.lang.String userId, kotlin.coroutines.Continuation<? super kotlinx.serialization.json.JsonObject> $completion) {
        return null;
    }
    
    private final java.lang.Object resolvePrakritiPercentages(java.lang.String userId, kotlinx.serialization.json.JsonObject profile, kotlin.coroutines.Continuation<? super kotlin.Triple<java.lang.Integer, java.lang.Integer, java.lang.Integer>> $completion) {
        return null;
    }
    
    private final java.lang.String buildConstitutionLabel(int vata, int pitta, int kapha) {
        return null;
    }
    
    private final java.lang.Object calculateStreak(java.lang.String userId, kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    private final java.util.List<java.lang.String> parseConditions(java.lang.String raw) {
        return null;
    }
    
    private final int intOf(kotlinx.serialization.json.JsonObject $this$intOf, java.lang.String key) {
        return 0;
    }
    
    private final java.lang.Integer intOfOrNull(kotlinx.serialization.json.JsonObject $this$intOfOrNull, java.lang.String key) {
        return null;
    }
    
    private final java.lang.String strOf(kotlinx.serialization.json.JsonObject $this$strOf, java.lang.String key) {
        return null;
    }
}