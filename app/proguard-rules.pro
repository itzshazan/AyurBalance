# AyurBalance ProGuard Rules

# Keep Kotlin serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# TensorFlow Lite
-keep class org.tensorflow.** { *; }
-keep class org.tensorflow.lite.** { *; }
-keep class org.tensorflow.lite.support.** { *; }
-dontwarn org.tensorflow.**

# CameraX
-keep class androidx.camera.** { *; }
-dontwarn androidx.camera.**

# AyurBalance data models (Supabase serialization)
-keep class com.ayurbalance.data.models.** { *; }
-keep class com.ayurbalance.data.local.** { *; }
-keep class com.ayurbalance.ui.dashboard.** { *; }
-keep class com.ayurbalance.ui.prakriti.** { *; }
-keep class com.ayurbalance.ui.logfood.** { *; }
-keep class com.ayurbalance.ui.meals.** { *; }

# Room
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }
-dontwarn androidx.room.**
