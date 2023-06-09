package com.most4dev.recipesapp.data.impl

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.most4dev.recipesapp.data.R
import com.most4dev.recipesapp.data.utils.getAddress
import com.most4dev.recipesapp.domain.entities.ProfileEntity
import com.most4dev.recipesapp.domain.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume


class ProfileRepositoryImpl(
    private val context: Context,
) : ProfileRepository {

    override suspend fun getDataUser(fusedLocationClient: FusedLocationProviderClient) =
        ProfileEntity(
            city = getCity(fusedLocationClient),
            date = getCurrentDate(),
            photo = R.drawable.ic_photo_placeholder
        )

    private suspend fun getCity(fusedLocationClient: FusedLocationProviderClient): String {
        var currentCity = context.getString(R.string.unknown_city)
        var mainLooper: Looper? = null
        withContext(Dispatchers.Main) {
            mainLooper = Looper.myLooper()
        }
        val location = getLocation(fusedLocationClient, mainLooper)
        val geoCoder = Geocoder(context, Locale.getDefault())

        location?.let { currentLocation ->
            geoCoder.getAddress(
                latitude = currentLocation.latitude,
                longitude = currentLocation.longitude
            ) {
                it?.let { address ->
                    currentCity = if (address.locality != null) {
                        address.locality
                    } else if (address.adminArea != null) {
                        address.adminArea
                    } else {
                        context.getString(R.string.unknown_city)
                    }
                }
            }
        }
        return currentCity
    }

    private suspend fun getLocation(
        fusedLocationClient: FusedLocationProviderClient,
        mainLooper: Looper?,
    ) =
        suspendCancellableCoroutine { continuation ->
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                val locationRequest = LocationRequest.Builder(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    TimeUnit.SECONDS.toMillis(3)
                ).build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        result.lastLocation.run {
                            val loc = this
                            continuation.resume(this)
                        }
                        fusedLocationClient.removeLocationUpdates(this)
                    }
                }

                fusedLocationClient.requestLocationUpdates(
                    locationRequest, locationCallback, mainLooper
                )
            } else {
                continuation.resume(null)
            }
        }

    private fun getCurrentDate(): String {
        val date = Date(System.currentTimeMillis())
        val format = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        return format.format(date)
    }

    companion object {

        const val DATE_FORMAT = "dd MMMM yyyy"
        val EMPTY_PROFILE = ProfileEntity(
            "", "", R.drawable.ic_photo_placeholder
        )

    }
}