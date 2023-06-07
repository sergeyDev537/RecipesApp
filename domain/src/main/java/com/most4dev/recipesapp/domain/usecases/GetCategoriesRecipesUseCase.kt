package com.most4dev.recipesapp.domain.usecases

import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class GetCategoriesRecipesUseCase(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke(): List<CategoryRecipesEntity> {
        return recipesRepository.getCategoriesRecipes()
    }

}