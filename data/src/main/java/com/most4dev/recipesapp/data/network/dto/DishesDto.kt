package com.most4dev.recipesapp.data.network.dto

data class DishesDto(
    val description: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)