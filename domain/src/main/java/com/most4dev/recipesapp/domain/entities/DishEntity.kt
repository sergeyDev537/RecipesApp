package com.most4dev.recipesapp.domain.entities

data class DishEntity(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val image_url: String,
    val tegs: List<String>,
    val weight: Int
)