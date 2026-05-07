package com.ayurbalance.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J,\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u00060\rH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000eJ\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\n \u0019*\u0004\u0018\u00010\u00170\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/ayurbalance/ui/profile/PdfExportManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "drawPage", "", "canvas", "Landroid/graphics/Canvas;", "state", "Lcom/ayurbalance/ui/profile/ProfileState;", "exportReport", "onDone", "Lkotlin/Function1;", "Landroid/net/Uri;", "(Lcom/ayurbalance/ui/profile/ProfileState;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdf", "openShareSheet", "uri", "savePdf", "document", "Landroid/graphics/pdf/PdfDocument;", "filename", "", "timestamp", "kotlin.jvm.PlatformType", "app_debug"})
public final class PdfExportManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public PdfExportManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportReport(@org.jetbrains.annotations.NotNull()
    com.ayurbalance.ui.profile.ProfileState state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onDone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final android.net.Uri generatePdf(com.ayurbalance.ui.profile.ProfileState state) {
        return null;
    }
    
    private final void drawPage(android.graphics.Canvas canvas, com.ayurbalance.ui.profile.ProfileState state) {
    }
    
    private final android.net.Uri savePdf(android.graphics.pdf.PdfDocument document, java.lang.String filename) {
        return null;
    }
    
    private final java.lang.String timestamp() {
        return null;
    }
    
    public final void openShareSheet(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
}