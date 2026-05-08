package com.ayurbalance.ui.analytics;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010\u0016J8\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0002J\"\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u001f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u000e\u0010 \u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u0010H\u0002J\u0014\u0010%\u001a\u00020&*\u00020\f2\u0006\u0010\'\u001a\u00020\u000eH\u0002J\u0014\u0010(\u001a\u00020\t*\u00020\f2\u0006\u0010\'\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/ayurbalance/ui/analytics/AnalyticsRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fmt", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "computeDoshaAlignment", "", "logs", "", "Lkotlinx/serialization/json/JsonObject;", "primaryDosha", "", "dateOf", "Ljava/time/LocalDate;", "row", "extractPrimaryDosha", "prakriti", "fetchPrakriti", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateInsight", "alignment", "dosha", "ritu", "carbs", "protein", "fat", "groupCaloriesByDay", "", "loadAnalytics", "Lcom/ayurbalance/ui/analytics/AnalyticsState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shortDayLabel", "date", "floatOf", "", "key", "intOf", "app_debug"})
public final class AnalyticsRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final java.time.format.DateTimeFormatter fmt = null;
    
    public AnalyticsRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadAnalytics(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ayurbalance.ui.analytics.AnalyticsState> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchPrakriti(java.lang.String userId, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.String extractPrimaryDosha(java.lang.String prakriti) {
        return null;
    }
    
    private final java.time.LocalDate dateOf(kotlinx.serialization.json.JsonObject row) {
        return null;
    }
    
    private final java.util.Map<java.time.LocalDate, java.lang.Integer> groupCaloriesByDay(java.util.List<kotlinx.serialization.json.JsonObject> logs) {
        return null;
    }
    
    private final int computeDoshaAlignment(java.util.List<kotlinx.serialization.json.JsonObject> logs, java.lang.String primaryDosha) {
        return 0;
    }
    
    private final java.lang.String shortDayLabel(java.time.LocalDate date) {
        return null;
    }
    
    private final java.lang.String generateInsight(int alignment, java.lang.String dosha, java.lang.String ritu, int carbs, int protein, int fat) {
        return null;
    }
    
    private final int intOf(kotlinx.serialization.json.JsonObject $this$intOf, java.lang.String key) {
        return 0;
    }
    
    private final float floatOf(kotlinx.serialization.json.JsonObject $this$floatOf, java.lang.String key) {
        return 0.0F;
    }
}