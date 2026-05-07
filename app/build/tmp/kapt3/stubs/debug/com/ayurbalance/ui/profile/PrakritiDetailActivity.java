package com.ayurbalance.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J(\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/ayurbalance/ui/profile/PrakritiDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityPrakritiDetailBinding;", "animateEntrance", "", "bindData", "vata", "", "pitta", "kapha", "type", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupButtons", "Companion", "app_debug"})
public final class PrakritiDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_VATA = "extra_vata_pct";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_PITTA = "extra_pitta_pct";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KAPHA = "extra_kapha_pct";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TYPE = "extra_prakriti_type";
    private com.ayurbalance.databinding.ActivityPrakritiDetailBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.profile.PrakritiDetailActivity.Companion Companion = null;
    
    public PrakritiDetailActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void bindData(int vata, int pitta, int kapha, java.lang.String type) {
    }
    
    private final void setupButtons() {
    }
    
    private final void animateEntrance() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/ayurbalance/ui/profile/PrakritiDetailActivity$Companion;", "", "()V", "EXTRA_KAPHA", "", "EXTRA_PITTA", "EXTRA_TYPE", "EXTRA_VATA", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}