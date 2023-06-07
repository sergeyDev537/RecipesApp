package com.most4dev.recipesapp.domain.repositories

import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.entities.DishEntity

interface RecipesRepository {

    suspend fun getCategoriesRecipes() : List<CategoryRecipesEntity>

    suspend fun getListDishes() : List<DishEntity>

}