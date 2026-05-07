package com.ayurbalance.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\u0012\u0010\u001c\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0010H\u0002J\b\u0010 \u001a\u00020\u0010H\u0002J\b\u0010!\u001a\u00020\u0010H\u0002J\b\u0010\"\u001a\u00020\u0010H\u0002J\b\u0010#\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006$"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityProfileBinding;", "notifManager", "Lcom/ayurbalance/ui/profile/NotificationSettingsManager;", "pdfExporter", "Lcom/ayurbalance/ui/profile/PdfExportManager;", "viewModel", "Lcom/ayurbalance/ui/profile/ProfileScreenViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/profile/ProfileScreenViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "animateEntrance", "", "bindDoshaBar", "fill", "Landroid/view/View;", "track", "pct", "", "bindState", "state", "Lcom/ayurbalance/ui/profile/ProfileState;", "hideSkeleton", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupActions", "setupBottomNav", "showEmpty", "showSkeleton", "signOut", "app_debug"})
public final class ProfileActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivityProfileBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ayurbalance.ui.profile.NotificationSettingsManager notifManager;
    private com.ayurbalance.ui.profile.PdfExportManager pdfExporter;
    
    public ProfileActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.profile.ProfileScreenViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void bindState(com.ayurbalance.ui.profile.ProfileState state) {
    }
    
    private final void bindDoshaBar(android.view.View fill, android.view.View track, int pct) {
    }
    
    private final void setupActions() {
    }
    
    private final void signOut() {
    }
    
    private final void animateEntrance() {
    }
    
    private final void showSkeleton() {
    }
    
    private final void hideSkeleton() {
    }
    
    private final void showEmpty() {
    }
    
    private final void setupBottomNav() {
    }
}