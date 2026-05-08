package com.ayurbalance.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/ayurbalance/data/local/MealPlanDao;", "", "deleteStale", "", "olderThanMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlanForDate", "Lcom/ayurbalance/data/local/CachedMealPlanEntity;", "dateKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlansForRange", "", "from", "to", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlan", "plan", "(Lcom/ayurbalance/data/local/CachedMealPlanEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface MealPlanDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPlan(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.data.local.CachedMealPlanEntity plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM meal_plan_cache WHERE dateKey = :dateKey LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPlanForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String dateKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ayurbalance.data.local.CachedMealPlanEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM meal_plan_cache WHERE dateKey BETWEEN :from AND :to ORDER BY dateKey")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPlansForRange(@org.jetbrains.annotations.NotNull()
    java.lang.String from, @org.jetbrains.annotations.NotNull()
    java.lang.String to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.data.local.CachedMealPlanEntity>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM meal_plan_cache WHERE generatedAt < :olderThanMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteStale(long olderThanMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}