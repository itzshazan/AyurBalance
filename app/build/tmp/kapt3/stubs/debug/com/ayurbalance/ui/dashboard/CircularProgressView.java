package com.ayurbalance.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0014R$\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0018@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0018@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR$\u0010!\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000e\u00a8\u0006("}, d2 = {"Lcom/ayurbalance/ui/dashboard/CircularProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "arcColor", "getArcColor", "()I", "setArcColor", "(I)V", "", "isDashed", "()Z", "setDashed", "(Z)V", "oval", "Landroid/graphics/RectF;", "paint", "Landroid/graphics/Paint;", "", "progress", "getProgress", "()F", "setProgress", "(F)V", "strokeDp", "getStrokeDp", "setStrokeDp", "trackColor", "getTrackColor", "setTrackColor", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "app_debug"})
public final class CircularProgressView extends android.view.View {
    private float progress = 0.6F;
    private int trackColor;
    private int arcColor;
    private float strokeDp = 7.0F;
    private boolean isDashed = false;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint paint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.RectF oval = null;
    
    @kotlin.jvm.JvmOverloads()
    public CircularProgressView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    public final float getProgress() {
        return 0.0F;
    }
    
    public final void setProgress(float value) {
    }
    
    public final int getTrackColor() {
        return 0;
    }
    
    public final void setTrackColor(int value) {
    }
    
    public final int getArcColor() {
        return 0;
    }
    
    public final void setArcColor(int value) {
    }
    
    public final float getStrokeDp() {
        return 0.0F;
    }
    
    public final void setStrokeDp(float value) {
    }
    
    public final boolean isDashed() {
        return false;
    }
    
    public final void setDashed(boolean value) {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public CircularProgressView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public CircularProgressView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
}