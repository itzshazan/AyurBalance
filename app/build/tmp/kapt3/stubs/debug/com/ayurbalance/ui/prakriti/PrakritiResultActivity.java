package com.ayurbalance.ui.prakriti;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\u0012\u0010\u0016\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\fH\u0002J\b\u0010\u001a\u001a\u00020\fH\u0002J\b\u0010\u001b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001d"}, d2 = {"Lcom/ayurbalance/ui/prakriti/PrakritiResultActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityPrakritiResultBinding;", "viewModel", "Lcom/ayurbalance/ui/prakriti/ResultViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/prakriti/ResultViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "animateBar", "", "fill", "Landroid/view/View;", "track", "percent", "", "bindResult", "result", "Lcom/ayurbalance/ui/prakriti/ResultModel;", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "overrideTransition", "runEntryAnimations", "setupButtons", "Companion", "app_debug"})
public final class PrakritiResultActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivityPrakritiResultBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_VATA = "VATA_SCORE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_PITTA = "PITTA_SCORE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KAPHA = "KAPHA_SCORE";
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.prakriti.PrakritiResultActivity.Companion Companion = null;
    
    public PrakritiResultActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.prakriti.ResultViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void bindResult(com.ayurbalance.ui.prakriti.ResultModel result) {
    }
    
    private final void setupButtons() {
    }
    
    private final void runEntryAnimations() {
    }
    
    private final void animateBar(android.view.View fill, android.view.View track, int percent) {
    }
    
    private final void overrideTransition() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/ayurbalance/ui/prakriti/PrakritiResultActivity$Companion;", "", "()V", "EXTRA_KAPHA", "", "EXTRA_PITTA", "EXTRA_VATA", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}