package com.ayurbalance

import android.app.Application
import com.ayurbalance.ui.reminders.ReminderNotificationHelper
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

/**
 * AyurBalance Application class.
 *
 * Responsible for app-wide initialization including:
 * - Supabase client setup
 * - TFLite model preloading (future)
 * - Analytics initialization (future)
 */
class AyurBalanceApp : Application() {

    companion object {
        /** Global Supabase client — initialized in [onCreate]. */
        lateinit var supabaseClient: SupabaseClient
            private set
    }

    override fun onCreate() {
        super.onCreate()

        ReminderNotificationHelper.createChannels(this)

        supabaseClient = createSupabaseClient(
            supabaseUrl = "https://tvlfpzwtowijdtmueywt.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR2bGZwend0b3dpamR0bXVleXd0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzYxMDQ2MTMsImV4cCI6MjA5MTY4MDYxM30.umEV72jFBPU1sKd58khrxdNqde9niAQEzYdvSbEdth0"
        ) {
            install(Auth) {
                scheme = "ayurbalance"
                host = "login-callback"
            }
            install(Postgrest)
        }
    }
}
