package com.ayurbalance.ui.logfood;

/**
 * On-device food classifier powered by Google ML Kit Image Labeling.
 * Requires no model file — the built-in model (~600 categories from Open Images)
 * recognises common Indian and global foods: biryani, curry, samosa, dosa, naan, etc.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0006\u0012\u0004\u0012\u00020\r0\u0011J\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\u000bJ\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\u0006\u0010\u0017\u001a\u00020\u000bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/ayurbalance/ui/logfood/FoodClassifier;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "FOOD_TERMS", "", "", "labeler", "Lcom/google/mlkit/vision/label/ImageLabeler;", "ready", "", "classify", "", "bitmap", "Landroid/graphics/Bitmap;", "callback", "Lkotlin/Function1;", "Lcom/ayurbalance/ui/logfood/FoodPrediction;", "close", "initialize", "isFoodRelated", "label", "isReady", "Companion", "app_debug"})
public final class FoodClassifier {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private static final float CONFIDENCE_THRESHOLD = 0.3F;
    private static final int TOP_K = 3;
    @org.jetbrains.annotations.NotNull()
    private final com.google.mlkit.vision.label.ImageLabeler labeler = null;
    private boolean ready = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> FOOD_TERMS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.logfood.FoodClassifier.Companion Companion = null;
    
    public FoodClassifier(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean initialize() {
        return false;
    }
    
    /**
     * Async classification — result delivered on the calling thread via [callback].
     * Call [imageProxy.close()] BEFORE calling this (already done in LogFoodActivity).
     */
    public final void classify(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.ayurbalance.ui.logfood.FoodPrediction>, kotlin.Unit> callback) {
    }
    
    private final boolean isFoodRelated(java.lang.String label) {
        return false;
    }
    
    public final boolean isReady() {
        return false;
    }
    
    public final void close() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/ayurbalance/ui/logfood/FoodClassifier$Companion;", "", "()V", "CONFIDENCE_THRESHOLD", "", "TOP_K", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}