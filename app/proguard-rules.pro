# AyurBalance ProGuard Rules
# Add project specific ProGuard rules here.

# Keep Supabase models when integrated
# -keep class com.ayurbalance.data.model.** { *; }

# Keep Kotlin serialization
# -keepattributes *Annotation*, InnerClasses
# -dontnote kotlinx.serialization.AnnotationsKt

# Keep TFLite model classes when integrated
# -keep class org.tensorflow.** { *; }
