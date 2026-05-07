package com.ayurbalance.ui.prakriti;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0002J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u000eH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0002J\u0012\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010$\u001a\u00020\u000eH\u0002J\b\u0010%\u001a\u00020\u000eH\u0002J\b\u0010&\u001a\u00020\u000eH\u0002J\b\u0010\'\u001a\u00020\u000eH\u0002J\b\u0010(\u001a\u00020\u000eH\u0002J\b\u0010)\u001a\u00020\u000eH\u0002J\b\u0010*\u001a\u00020\u000eH\u0002J\u0012\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u0010\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u0006H\u0002J\u0010\u00100\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u00061"}, d2 = {"Lcom/ayurbalance/ui/prakriti/PrakritiQuestionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/ayurbalance/databinding/ActivityPrakritiQuestionBinding;", "isFirstLoad", "", "viewModel", "Lcom/ayurbalance/ui/prakriti/PrakritiViewModel;", "getViewModel", "()Lcom/ayurbalance/ui/prakriti/PrakritiViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "animateCardTap", "", "card", "Landroid/view/View;", "animateDoshaBar", "barView", "trackLayout", "percent", "", "animateProgressBar", "index", "animateQuestionChange", "applyWindowInsets", "configureEdgeToEdge", "loadQuestionDirect", "navigateToPrakritiResult", "observeViewModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setQuestionContent", "question", "Lcom/ayurbalance/ui/prakriti/PrakritiQuestion;", "setupBackNavigation", "setupNextButton", "setupOptionCards", "setupPrevButton", "setupSaveExitButton", "showResumeDialog", "startEntryAnimations", "updateCardStates", "selected", "Lcom/ayurbalance/ui/prakriti/DoshaType;", "updateNextButtonState", "enabled", "updatePrevButtonVisibility", "app_debug"})
public final class PrakritiQuestionActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ayurbalance.databinding.ActivityPrakritiQuestionBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean isFirstLoad = true;
    
    public PrakritiQuestionActivity() {
        super();
    }
    
    private final com.ayurbalance.ui.prakriti.PrakritiViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void configureEdgeToEdge() {
    }
    
    private final void applyWindowInsets() {
    }
    
    private final void showResumeDialog() {
    }
    
    private final void setupBackNavigation() {
    }
    
    private final void setupOptionCards() {
    }
    
    private final void animateCardTap(android.view.View card) {
    }
    
    private final void setupNextButton() {
    }
    
    private final void setupPrevButton() {
    }
    
    private final void setupSaveExitButton() {
    }
    
    private final void updateNextButtonState(boolean enabled) {
    }
    
    private final void updatePrevButtonVisibility(int index) {
    }
    
    private final void observeViewModel() {
    }
    
    private final void loadQuestionDirect(int index) {
    }
    
    private final void animateQuestionChange(int index) {
    }
    
    private final void setQuestionContent(com.ayurbalance.ui.prakriti.PrakritiQuestion question, int index) {
    }
    
    private final void updateCardStates(com.ayurbalance.ui.prakriti.DoshaType selected) {
    }
    
    private final void animateProgressBar(int index) {
    }
    
    private final void animateDoshaBar(android.view.View barView, android.view.View trackLayout, int percent) {
    }
    
    private final void navigateToPrakritiResult() {
    }
    
    private final void startEntryAnimations() {
    }
}