package com.ayurbalance.admin.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lcom/ayurbalance/admin/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_alerts", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/ayurbalance/admin/data/ActivityLogEntry;", "_metrics", "Lcom/ayurbalance/admin/data/DashboardMetrics;", "kotlin.jvm.PlatformType", "alerts", "Landroidx/lifecycle/LiveData;", "getAlerts", "()Landroidx/lifecycle/LiveData;", "metrics", "getMetrics", "loadAlerts", "", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ayurbalance.admin.data.DashboardMetrics> _metrics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ayurbalance.admin.data.DashboardMetrics> metrics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.ayurbalance.admin.data.ActivityLogEntry>> _alerts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ayurbalance.admin.data.ActivityLogEntry>> alerts = null;
    
    public DashboardViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ayurbalance.admin.data.DashboardMetrics> getMetrics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ayurbalance.admin.data.ActivityLogEntry>> getAlerts() {
        return null;
    }
    
    private final void loadAlerts() {
    }
}