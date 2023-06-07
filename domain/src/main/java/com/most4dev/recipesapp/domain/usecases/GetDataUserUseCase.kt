package com.most4dev.recipesapp.domain.usecases

import com.most4dev.recipesapp.domain.entities.ProfileEntity
import com.most4dev.recipesapp.domain.repositories.ProfileRepository

class GetDataUserUseCase(private val profileRepository: ProfileRepository) {

    operator fun invoke(): ProfileEntity {
        return profileRepository.getDataUser()
    }

}