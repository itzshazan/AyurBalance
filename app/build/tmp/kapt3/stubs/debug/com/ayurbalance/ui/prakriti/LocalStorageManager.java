package com.ayurbalance.ui.prakriti;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0012J\u001c\u0010\u0015\u001a\u00020\u000e2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/ayurbalance/ui/prakriti/LocalStorageManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "clearState", "", "deserializeAnswers", "", "", "Lcom/ayurbalance/ui/prakriti/DoshaType;", "raw", "", "hasSavedState", "", "loadState", "Lcom/ayurbalance/ui/prakriti/PrakritiState;", "saveState", "state", "serializeAnswers", "answers", "", "Companion", "app_debug"})
public final class LocalStorageManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREF_NAME = "ayur_prakriti_state";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_HAS_STATE = "has_state";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_INDEX = "current_index";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_VATA_SCORE = "vata_score";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PITTA_SCORE = "pitta_score";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_KAPHA_SCORE = "kapha_score";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ANSWERS = "answers";
    @org.jetbrains.annotations.NotNull()
    public static final com.ayurbalance.ui.prakriti.LocalStorageManager.Companion Companion = null;
    
    public LocalStorageManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void saveState(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.prakriti.PrakritiState state) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ayurbalance.ui.prakriti.PrakritiState loadState() {
        return null;
    }
    
    public final void clearState() {
    }
    
    public final boolean hasSavedState() {
        return false;
    }
    
    private final java.lang.String serializeAnswers(java.util.Map<java.lang.Integer, ? extends com.ayurbalance.ui.prakriti.DoshaType> answers) {
        return null;
    }
    
    private final java.util.Map<java.lang.Integer, com.ayurbalance.ui.prakriti.DoshaType> deserializeAnswers(java.lang.String raw) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/ayurbalance/ui/prakriti/LocalStorageManager$Companion;", "", "()V", "KEY_ANSWERS", "", "KEY_CURRENT_INDEX", "KEY_HAS_STATE", "KEY_KAPHA_SCORE", "KEY_PITTA_SCORE", "KEY_VATA_SCORE", "PREF_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}