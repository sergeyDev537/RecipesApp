package com.most4dev.recipesapp.domain.repositories

import androidx.lifecycle.LiveData
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.entities.TagEntity

interface RecipesRepository {

    suspend fun getCategoriesRecipes(): MutableList<CategoryRecipesEntity>

    suspend fun getListDishes(): MutableList<DishEntity>

    suspend fun getTags(): MutableList<TagEntity>

    fun selectTag(position: Int): Pair<MutableList<TagEntity>, MutableList<DishEntity>>

    fun getCartListDishes(): LiveData<List<DishEntity>>

    suspend fun addDish(dish: DishEntity)

    suspend fun removeDish(dish: DishEntity)

}