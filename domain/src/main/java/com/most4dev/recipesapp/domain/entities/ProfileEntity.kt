package com.most4dev.recipesapp.domain.entities

import androidx.annotation.DrawableRes

data class ProfileEntity(
    val city: String,
    val date: String,
    @DrawableRes val photo: Int
)