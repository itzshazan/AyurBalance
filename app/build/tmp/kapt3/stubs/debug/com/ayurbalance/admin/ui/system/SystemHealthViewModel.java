package com.ayurbalance.admin.ui.system;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000b \u0006*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lcom/ayurbalance/admin/ui/system/SystemHealthViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_avgLatency", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_dbConnections", "_errorRate", "_services", "", "Lcom/ayurbalance/admin/data/SystemService;", "_uptime", "avgLatency", "Landroidx/lifecycle/LiveData;", "getAvgLatency", "()Landroidx/lifecycle/LiveData;", "dbConnections", "getDbConnections", "errorRate", "getErrorRate", "services", "getServices", "uptime", "getUptime", "sampleServices", "app_debug"})
public final class SystemHealthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.ayurbalance.admin.data.SystemService>> _services = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ayurbalance.admin.data.SystemService>> services = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _uptime = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> uptime = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _avgLatency = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> avgLatency = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorRate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> errorRate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _dbConnections = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> dbConnections = null;
    
    public SystemHealthViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ayurbalance.admin.data.SystemService>> getServices() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getUptime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAvgLatency() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorRate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getDbConnections() {
        return null;
    }
    
    private final java.util.List<com.ayurbalance.admin.data.SystemService> sampleServices() {
        return null;
    }
}