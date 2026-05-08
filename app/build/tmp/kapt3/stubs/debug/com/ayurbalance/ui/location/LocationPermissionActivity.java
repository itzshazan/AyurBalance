package com.ayurbalance.ui.location;

/**
 * Sits between Profile Setup Step 4 and Prakriti Assessment.
 * Resolves the user's climate zone (used by the Ritucharya seasonal engine).
 * Two states: permission request UI and manual region picker fallback.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u0012\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\b\u0010\u001e\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/ayurbalance/ui/location/LocationPermissionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityLocationPermissionBinding;", "locationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "viewModel", "Lcom/ayurbalance/ui/location/LocationPermissionViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/location/LocationPermissionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyWindowInsets", "", "configureEdgeToEdge", "fetchDeviceLocation", "navigateToPrakriti", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupButtons", "setupRegionDropdown", "showClimatePreview", "region", "Lcom/ayurbalance/ui/location/model/RegionData;", "showManualFallback", "startEntryAnimations", "app_debug"})
public final class LocationPermissionActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivityLocationPermissionBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> locationPermissionLauncher = null;
    
    public LocationPermissionActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.location.LocationPermissionViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void configureEdgeToEdge() {
    }
    
    private final void applyWindowInsets() {
    }
    
    private final void setupButtons() {
    }
    
    private final void fetchDeviceLocation() {
    }
    
    private final void showManualFallback() {
    }
    
    private final void setupRegionDropdown() {
    }
    
    private final void showClimatePreview(com.ayurbalance.ui.location.model.RegionData region) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void navigateToPrakriti() {
    }
    
    private final void startEntryAnimations() {
    }
}