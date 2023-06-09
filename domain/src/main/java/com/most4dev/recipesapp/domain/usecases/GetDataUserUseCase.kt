package com.most4dev.recipesapp.domain.usecases

import com.google.android.gms.location.FusedLocationProviderClient
import com.most4dev.recipesapp.domain.entities.ProfileEntity
import com.most4dev.recipesapp.domain.repositories.ProfileRepository

class GetDataUserUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(fusedLocationClient: FusedLocationProviderClient): ProfileEntity {
        return profileRepository.getDataUser(fusedLocationClient)
    }

}