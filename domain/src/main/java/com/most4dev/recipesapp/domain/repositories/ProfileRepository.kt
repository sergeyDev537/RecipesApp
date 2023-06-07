package com.most4dev.recipesapp.domain.repositories

import com.most4dev.recipesapp.domain.entities.ProfileEntity

interface ProfileRepository {

    fun getDataUser(): ProfileEntity

}