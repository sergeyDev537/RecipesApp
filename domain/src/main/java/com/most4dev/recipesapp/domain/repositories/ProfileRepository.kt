package com.most4dev.recipesapp.domain.repositories

import com.google.android.gms.location.FusedLocationProviderClient
import com.most4dev.recipesapp.domain.entities.ProfileEntity

interface ProfileRepository {

    suspend fun getDataUser(fusedLocationClient: FusedLocationProviderClient): ProfileEntity

}