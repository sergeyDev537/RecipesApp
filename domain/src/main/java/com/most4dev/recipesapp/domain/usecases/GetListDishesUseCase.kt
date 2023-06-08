package com.most4dev.recipesapp.domain.usecases

import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class GetListDishesUseCase(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke(): MutableList<DishEntity> {
        return recipesRepository.getListDishes()
    }

}