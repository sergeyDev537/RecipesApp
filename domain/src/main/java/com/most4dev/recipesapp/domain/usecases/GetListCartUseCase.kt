package com.most4dev.recipesapp.domain.usecases

import androidx.lifecycle.LiveData
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class GetListCartUseCase(private val recipesRepository: RecipesRepository) {

    operator fun invoke(): LiveData<List<DishEntity>> {
        return recipesRepository.getCartListDishes()
    }
}