package com.ayurbalance.ui.logfood;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\tH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010!\u001a\u00020\u0017H\u0002J\u0012\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u0017H\u0014J\b\u0010&\u001a\u00020\u0017H\u0002J\b\u0010\'\u001a\u00020\u0017H\u0002J\b\u0010(\u001a\u00020\u0017H\u0002J\b\u0010)\u001a\u00020\u0017H\u0002J\b\u0010*\u001a\u00020\u0017H\u0002J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006."}, d2 = {"Lcom/ayurbalance/ui/logfood/LogFoodActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityLogFoodBinding;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "cameraPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "classifier", "Lcom/ayurbalance/ui/logfood/FoodClassifier;", "inferenceIntervalMs", "", "lastInferenceMs", "viewModel", "Lcom/ayurbalance/ui/logfood/LogFoodViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/logfood/LogFoodViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "analyzeFrame", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "bindResults", "state", "Lcom/ayurbalance/ui/logfood/LogFoodState;", "checkCameraPermission", "detectMealType", "imageProxyToBitmap", "Landroid/graphics/Bitmap;", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupConfirmButton", "setupResultRows", "setupTabs", "showPermissionDenied", "startCamera", "updateTabHighlight", "activeTab", "Lcom/ayurbalance/ui/logfood/LogFoodState$Tab;", "app_debug"})
public final class LogFoodActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivityLogFoodBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ayurbalance.ui.logfood.FoodClassifier classifier;
    private java.util.concurrent.ExecutorService cameraExecutor;
    private long lastInferenceMs = 0L;
    private final long inferenceIntervalMs = 900L;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> cameraPermissionLauncher = null;
    
    public LogFoodActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.logfood.LogFoodViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void checkCameraPermission() {
    }
    
    private final void showPermissionDenied() {
    }
    
    private final void startCamera() {
    }
    
    private final void analyzeFrame(androidx.camera.core.ImageProxy imageProxy) {
    }
    
    private final android.graphics.Bitmap imageProxyToBitmap(androidx.camera.core.ImageProxy imageProxy) {
        return null;
    }
    
    private final void observeViewModel() {
    }
    
    private final void bindResults(com.ayurbalance.ui.logfood.LogFoodState state) {
    }
    
    private final void setupResultRows() {
    }
    
    private final void setupConfirmButton() {
    }
    
    private final java.lang.String detectMealType() {
        return null;
    }
    
    private final void setupTabs() {
    }
    
    private final void updateTabHighlight(com.ayurbalance.ui.logfood.LogFoodState.Tab activeTab) {
    }
}