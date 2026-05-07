package com.ayurbalance.ui.location;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u0007J\u001c\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u0016\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u000eJ\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u000eH\u0002R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/ayurbalance/ui/location/LocationPermissionViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "navigateNext", "Landroidx/lifecycle/MutableLiveData;", "", "getNavigateNext", "()Landroidx/lifecycle/MutableLiveData;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "defaultRegion", "Lcom/ayurbalance/ui/location/model/RegionData;", "isRegionAlreadySaved", "mapToAyurvedaRegion", "countryCode", "", "adminArea", "onPermissionDenied", "", "onPermissionGranted", "resolveLocationToRegion", "lat", "", "lon", "saveManualRegion", "region", "saveResolvedRegion", "app_debug"})
public final class LocationPermissionViewModel extends androidx.lifecycle.AndroidViewModel {
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> navigateNext = null;
    
    public LocationPermissionViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getNavigateNext() {
        return null;
    }
    
    public final boolean isRegionAlreadySaved() {
        return false;
    }
    
    public final void onPermissionGranted() {
    }
    
    public final void onPermissionDenied() {
    }
    
    public final void resolveLocationToRegion(double lat, double lon) {
    }
    
    public final void saveManualRegion(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.location.model.RegionData region) {
    }
    
    private final void saveResolvedRegion(com.ayurbalance.ui.location.model.RegionData region) {
    }
    
    private final com.ayurbalance.ui.location.model.RegionData defaultRegion() {
        return null;
    }
    
    private final com.ayurbalance.ui.location.model.RegionData mapToAyurvedaRegion(java.lang.String countryCode, java.lang.String adminArea) {
        return null;
    }
}