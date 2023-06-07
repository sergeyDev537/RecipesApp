package com.most4dev.recipesapp.data.impl

import com.most4dev.recipesapp.data.mappers.CategoryMapper
import com.most4dev.recipesapp.data.mappers.DishesMapper
import com.most4dev.recipesapp.data.network.api.ApiService
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class RecipesRepositoryImpl(
    private val apiService: ApiService,
    private val categoryMapper: CategoryMapper,
    private val dishesMapper: DishesMapper
): RecipesRepository {

    override suspend fun getCategoriesRecipes(): List<CategoryRecipesEntity> {
        return apiService.getCategories().body()?.let {
            categoryMapper.mapListCategoriesDtoToEntity(it)
        } ?: listOf()
    }

    override suspend fun getListDishes(): List<DishEntity> {
        return apiService.getDishes().body()?.let {
            dishesMapper.mapListDishesDtoToEntity(it)
        } ?: listOf()
    }
}