package com.most4dev.recipesapp.domain.usecases

import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.entities.TagEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class SelectTagUseCase(private val recipesRepository: RecipesRepository) {

    operator fun invoke(position: Int): Pair<MutableList<TagEntity>, MutableList<DishEntity>> {
        return recipesRepository.selectTag(position)
    }

}