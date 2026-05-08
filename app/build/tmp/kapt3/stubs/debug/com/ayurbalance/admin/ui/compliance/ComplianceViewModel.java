package com.ayurbalance.admin.ui.compliance;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0005R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u0014"}, d2 = {"Lcom/ayurbalance/admin/ui/compliance/ComplianceViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_actionResult", "Landroidx/lifecycle/MutableLiveData;", "", "_deletions", "", "Lcom/ayurbalance/admin/data/DeletionRequest;", "actionResult", "Landroidx/lifecycle/LiveData;", "getActionResult", "()Landroidx/lifecycle/LiveData;", "deletions", "getDeletions", "loadDeletions", "", "processAll", "processDeletion", "userId", "app_debug"})
public final class ComplianceViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.ayurbalance.admin.data.DeletionRequest>> _deletions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ayurbalance.admin.data.DeletionRequest>> deletions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _actionResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> actionResult = null;
    
    public ComplianceViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ayurbalance.admin.data.DeletionRequest>> getDeletions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getActionResult() {
        return null;
    }
    
    private final void loadDeletions() {
    }
    
    public final void processDeletion(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    public final void processAll() {
    }
}