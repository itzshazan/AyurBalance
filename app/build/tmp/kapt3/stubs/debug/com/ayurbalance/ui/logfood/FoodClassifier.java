package com.ayurbalance.ui.logfood;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u0017\u001a\u00020\rJ\b\u0010\u0018\u001a\u00020\u0014H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/ayurbalance/ui/logfood/FoodClassifier;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "inputBuffer", "Ljava/nio/ByteBuffer;", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "labels", "", "", "ready", "", "classify", "", "Lcom/ayurbalance/ui/logfood/FoodPrediction;", "bitmap", "Landroid/graphics/Bitmap;", "close", "", "fillInputBuffer", "initialize", "isReady", "loadLabels", "loadModelBuffer", "Ljava/nio/MappedByteBuffer;", "Companion", "app_debug"})
public final class FoodClassifier {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "FoodClassifier";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MODEL_FILE = "food_model.tflite";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LABELS_FILE = "food_labels.txt";
    private static final int INPUT_SIZE = 224;
    private static final int NUM_THREADS = 4;
    private static final int TOP_K = 3;
    private static final float CONFIDENCE_THRESHOLD = 0.05F;
    @org.jetbrains.annotations.Nullable()
    private org.tensorflow.lite.Interpreter interpreter;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> labels = null;
    private boolean ready = false;
    @org.jetbrains.annotations.NotNull()
    private final java.nio.ByteBuffer inputBuffer = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.logfood.FoodClassifier.Companion Companion = null;
    
    public FoodClassifier(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean initialize() {
        return false;
    }
    
    private final java.nio.MappedByteBuffer loadModelBuffer() {
        return null;
    }
    
    private final void loadLabels() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ayurbalance.ui.logfood.FoodPrediction> classify(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    private final void fillInputBuffer(android.graphics.Bitmap bitmap) {
    }
    
    public final boolean isReady() {
        return false;
    }
    
    public final void close() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/ayurbalance/ui/logfood/FoodClassifier$Companion;", "", "()V", "CONFIDENCE_THRESHOLD", "", "INPUT_SIZE", "", "LABELS_FILE", "", "MODEL_FILE", "NUM_THREADS", "TAG", "TOP_K", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}