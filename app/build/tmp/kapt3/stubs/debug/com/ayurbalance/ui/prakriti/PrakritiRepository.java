package com.ayurbalance.ui.prakriti;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/ayurbalance/ui/prakriti/PrakritiRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "storage", "Lcom/ayurbalance/ui/prakriti/LocalStorageManager;", "clear", "", "hasSavedProgress", "", "load", "Lcom/ayurbalance/ui/prakriti/PrakritiState;", "save", "state", "app_debug"})
public final class PrakritiRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ayurbalance.ui.prakriti.LocalStorageManager storage = null;
    
    public PrakritiRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void save(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.prakriti.PrakritiState state) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ayurbalance.ui.prakriti.PrakritiState load() {
        return null;
    }
    
    public final void clear() {
    }
    
    public final boolean hasSavedProgress() {
        return false;
    }
}