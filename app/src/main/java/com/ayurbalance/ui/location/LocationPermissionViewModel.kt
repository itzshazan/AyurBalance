package com.ayurbalance.ui.location

import android.app.Application
import android.content.Context
import android.location.Geocoder
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayurbalance.ui.location.model.RegionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class LocationPermissionViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("ayur_onboarding", Context.MODE_PRIVATE)

    val navigateNext = MutableLiveData<Boolean>()

    fun isRegionAlreadySaved(): Boolean = prefs.contains("region_code")

    fun onPermissionGranted() {
        prefs.edit().putBoolean("location_permission_granted", true).apply()
    }

    fun onPermissionDenied() {
        prefs.edit().putBoolean("location_permission_granted", false).apply()
    }

    fun resolveLocationToRegion(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val geocoder = Geocoder(getApplication(), Locale.getDefault())
            try {
                if (Build.VERSION.SDK_INT >= 33) {
                    geocoder.getFromLocation(lat, lon, 1) { addresses ->
                        val addr = addresses.firstOrNull()
                        val region = if (addr != null) {
                            mapToAyurvedaRegion(addr.countryCode, addr.adminArea)
                        } else {
                            defaultRegion()
                        }
                        viewModelScope.launch(Dispatchers.Main) { saveResolvedRegion(region) }
                    }
                } else {
                    @Suppress("DEPRECATION")
                    val addresses = geocoder.getFromLocation(lat, lon, 1)
                    val addr = addresses?.firstOrNull()
                    val region = if (addr != null) {
                        mapToAyurvedaRegion(addr.countryCode, addr.adminArea)
                    } else {
                        defaultRegion()
                    }
                    withContext(Dispatchers.Main) { saveResolvedRegion(region) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { navigateNext.value = true }
            }
        }
    }

    fun saveManualRegion(region: RegionData) {
        prefs.edit()
            .putString("region_code", region.code)
            .putString("region_label", region.label)
            .putString("climate_zone", region.climateZone)
            .putBoolean("region_auto_detected", false)
            .apply()
        navigateNext.value = true
    }

    private fun saveResolvedRegion(region: RegionData) {
        prefs.edit()
            .putString("region_code", region.code)
            .putString("region_label", region.label)
            .putString("climate_zone", region.climateZone)
            .putBoolean("region_auto_detected", true)
            .apply()
        navigateNext.value = true
    }

    private fun defaultRegion() = RegionData("IN_NORTH", "North India", "temperate")

    private fun mapToAyurvedaRegion(countryCode: String?, adminArea: String?): RegionData {
        return when {
            countryCode == "IN" && adminArea in listOf(
                "Kerala", "Tamil Nadu", "Karnataka", "Andhra Pradesh", "Telangana", "Goa"
            ) -> RegionData("IN_SOUTH", "South India", "tropical")

            countryCode == "IN" && adminArea in listOf(
                "Rajasthan", "Gujarat"
            ) -> RegionData("IN_WEST_ARID", "West India (Arid)", "arid")

            countryCode == "IN" && adminArea in listOf(
                "West Bengal", "Odisha", "Jharkhand", "Bihar", "Assam",
                "Meghalaya", "Manipur", "Nagaland", "Mizoram", "Tripura", "Sikkim", "Arunachal Pradesh"
            ) -> RegionData("IN_EAST", "East India", "tropical")

            countryCode == "IN" -> RegionData("IN_NORTH", "North India", "temperate")

            countryCode in listOf("US", "CA", "GB", "AU", "NZ", "DE", "FR", "NL", "SE", "NO") ->
                RegionData("INTL_TEMPERATE", "International (Temperate)", "temperate")

            countryCode in listOf("AE", "SA", "QA", "KW", "BH", "OM", "EG", "MA") ->
                RegionData("INTL_ARID", "International (Arid)", "arid")

            else -> RegionData("INTL_TROPICAL", "International (Tropical)", "tropical")
        }
    }
}
