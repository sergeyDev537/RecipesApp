package com.most4dev.recipesapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DishEntity(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val image_url: String,
    val tags: List<String>,
    val weight: Int,
) : Parcelable