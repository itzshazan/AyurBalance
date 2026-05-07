package com.ayurbalance.ui.profile;

/**
 * ViewModel for multi-step Health Profile form.
 *
 * Holds all user-entered metrics across the 4-step flow.
 * Data is kept in memory during the flow and persisted
 * to Supabase once all steps are complete.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u001c\u0018\u00002\u00020\u0001:\u0001]B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010>0=J\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010>0=J\u0014\u0010@\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010>0=J\u000e\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005J\u0015\u0010D\u001a\u00020B2\b\u0010E\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010FJ\u000e\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u00020\u0005J\u000e\u0010I\u001a\u00020B2\u0006\u0010E\u001a\u00020\u0005J\u000e\u0010J\u001a\u00020B2\u0006\u0010K\u001a\u00020\u0005J\u0015\u0010L\u001a\u00020B2\b\u0010E\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010MJ\u000e\u0010N\u001a\u00020B2\u0006\u0010O\u001a\u00020\u0005J\u000e\u0010P\u001a\u00020B2\u0006\u0010Q\u001a\u00020\u000fJ\u000e\u0010R\u001a\u00020B2\u0006\u0010S\u001a\u00020\u0005J\u0015\u0010T\u001a\u00020B2\b\u0010E\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010MJ\u000e\u0010U\u001a\u00020B2\u0006\u0010O\u001a\u00020\u0005J\u0006\u0010V\u001a\u00020BJ\u000e\u0010W\u001a\u00020B2\u0006\u0010X\u001a\u00020\u0005J\b\u0010Y\u001a\u00020BH\u0002J\b\u0010Z\u001a\u00020BH\u0002J\b\u0010[\u001a\u00020BH\u0002J\b\u0010\\\u001a\u00020BH\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00120\u00120\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00120\u00120\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00120\u00120\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00120\u00120\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000f0\u000f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001fR\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0019\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001fR\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001fR\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001fR\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001fR\u0019\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010\u001fR\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010\u001f\u00a8\u0006^"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_activityLevel", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_age", "", "_dietType", "_gender", "_goal", "_healthConditions", "", "_height", "", "_heightUnit", "_isStep1Valid", "", "_isStep2Valid", "_isStep3Valid", "_isStep4Valid", "_sleepHours", "_sleepQuality", "_submissionState", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState;", "_weight", "_weightUnit", "activityLevel", "Landroidx/lifecycle/LiveData;", "getActivityLevel", "()Landroidx/lifecycle/LiveData;", "age", "getAge", "dietType", "getDietType", "gender", "getGender", "goal", "getGoal", "healthConditions", "getHealthConditions", "height", "getHeight", "heightUnit", "getHeightUnit", "isStep1Valid", "isStep2Valid", "isStep3Valid", "isStep4Valid", "sleepHours", "getSleepHours", "sleepQuality", "getSleepQuality", "submissionState", "getSubmissionState", "weight", "getWeight", "weightUnit", "getWeightUnit", "getStep1Data", "", "", "getStep2Data", "getStep3Data", "setActivityLevel", "", "level", "setAge", "value", "(Ljava/lang/Integer;)V", "setDietType", "type", "setGender", "setGoal", "selectedGoal", "setHeight", "(Ljava/lang/Double;)V", "setHeightUnit", "unit", "setSleepHours", "hours", "setSleepQuality", "quality", "setWeight", "setWeightUnit", "submitProfile", "toggleHealthCondition", "condition", "validateStep1", "validateStep2", "validateStep3", "validateStep4", "SubmissionState", "app_debug"})
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _age = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> age = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _gender = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> gender = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _height = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> height = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _heightUnit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> heightUnit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _weight = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> weight = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _weightUnit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> weightUnit = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isStep1Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isStep1Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _activityLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> activityLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _sleepHours = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> sleepHours = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _sleepQuality = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> sleepQuality = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isStep2Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isStep2Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _dietType = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> dietType = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Set<java.lang.String>> _healthConditions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Set<java.lang.String>> healthConditions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isStep3Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isStep3Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _goal = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> goal = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isStep4Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isStep4Valid = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState> _submissionState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState> submissionState = null;
    
    public ProfileViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getAge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getGender() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getHeight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getHeightUnit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getWeight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getWeightUnit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isStep1Valid() {
        return null;
    }
    
    public final void setAge(@org.jetbrains.annotations.Nullable()
    java.lang.Integer value) {
    }
    
    public final void setGender(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setHeight(@org.jetbrains.annotations.Nullable()
    java.lang.Double value) {
    }
    
    public final void setHeightUnit(@org.jetbrains.annotations.NotNull()
    java.lang.String unit) {
    }
    
    public final void setWeight(@org.jetbrains.annotations.Nullable()
    java.lang.Double value) {
    }
    
    public final void setWeightUnit(@org.jetbrains.annotations.NotNull()
    java.lang.String unit) {
    }
    
    private final void validateStep1() {
    }
    
    /**
     * Returns Step 1 data as a map for serialization.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> getStep1Data() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getActivityLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getSleepHours() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSleepQuality() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isStep2Valid() {
        return null;
    }
    
    public final void setActivityLevel(@org.jetbrains.annotations.NotNull()
    java.lang.String level) {
    }
    
    public final void setSleepHours(double hours) {
    }
    
    public final void setSleepQuality(@org.jetbrains.annotations.NotNull()
    java.lang.String quality) {
    }
    
    private final void validateStep2() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> getStep2Data() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getDietType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Set<java.lang.String>> getHealthConditions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isStep3Valid() {
        return null;
    }
    
    public final void setDietType(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    public final void toggleHealthCondition(@org.jetbrains.annotations.NotNull()
    java.lang.String condition) {
    }
    
    private final void validateStep3() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> getStep3Data() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getGoal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isStep4Valid() {
        return null;
    }
    
    public final void setGoal(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedGoal) {
    }
    
    private final void validateStep4() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState> getSubmissionState() {
        return null;
    }
    
    public final void submitProfile() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState;", "", "()V", "Error", "Idle", "Loading", "Success", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Error;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Idle;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Loading;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Success;", "app_debug"})
    public static abstract class SubmissionState {
        
        private SubmissionState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Error;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Idle;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState;", "()V", "app_debug"})
        public static final class Idle extends com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Loading;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState;", "()V", "app_debug"})
        public static final class Loading extends com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState$Success;", "Lcom/ayurbalance/ui/profile/ProfileViewModel$SubmissionState;", "()V", "app_debug"})
        public static final class Success extends com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ayurbalance.ui.profile.ProfileViewModel.SubmissionState.Success INSTANCE = null;
            
            private Success() {
            }
        }
    }
}