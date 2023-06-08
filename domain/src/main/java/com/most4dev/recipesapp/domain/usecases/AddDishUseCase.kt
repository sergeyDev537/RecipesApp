package com.most4dev.recipesapp.domain.usecases

import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class AddDishUseCase(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke(dish: DishEntity) {
        return recipesRepository.addDish(dish)
    }

}