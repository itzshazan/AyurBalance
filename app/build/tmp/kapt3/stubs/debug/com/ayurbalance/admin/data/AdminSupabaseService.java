package com.ayurbalance.admin.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0002J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002J\u000e\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010 J\u001e\u0010#\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010 J\u0018\u0010$\u001a\u0004\u0018\u00010\u00122\u0006\u0010%\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006&"}, d2 = {"Lcom/ayurbalance/admin/data/AdminSupabaseService;", "", "()V", "supabase", "Lio/github/jan/supabase/SupabaseClient;", "getSupabase", "()Lio/github/jan/supabase/SupabaseClient;", "fetchFoodItems", "", "Lcom/ayurbalance/admin/data/FoodItem;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchPendingDeletions", "Lcom/ayurbalance/admin/data/DeletionRequest;", "fetchRecipes", "Lcom/ayurbalance/admin/data/Recipe;", "fetchUsers", "Lcom/ayurbalance/admin/data/UserRecord;", "getCurrentUserEmail", "", "processDeletion", "", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sampleDeletionRequests", "sampleFoodItems", "sampleRecipes", "sampleUsers", "signOut", "", "updateFoodItemStatus", "itemId", "status", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRecipeStatus", "recipeId", "updateUserStatus", "verifyAdminRole", "email", "app_debug"})
public final class AdminSupabaseService {
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.admin.data.AdminSupabaseService INSTANCE = null;
    
    private AdminSupabaseService() {
        super();
    }
    
    private final io.github.jan.supabase.SupabaseClient getSupabase() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object verifyAdminRole(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object signOut(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.admin.data.UserRecord>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUserStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchFoodItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.admin.data.FoodItem>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFoodItemStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String itemId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchRecipes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.admin.data.Recipe>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRecipeStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String recipeId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchPendingDeletions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ayurbalance.admin.data.DeletionRequest>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object processDeletion(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final java.util.List<com.ayurbalance.admin.data.UserRecord> sampleUsers() {
        return null;
    }
    
    private final java.util.List<com.ayurbalance.admin.data.FoodItem> sampleFoodItems() {
        return null;
    }
    
    private final java.util.List<com.ayurbalance.admin.data.Recipe> sampleRecipes() {
        return null;
    }
    
    private final java.util.List<com.ayurbalance.admin.data.DeletionRequest> sampleDeletionRequests() {
        return null;
    }
}