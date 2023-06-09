package com.most4dev.recipesapp.domain.usecases

import com.most4dev.recipesapp.domain.entities.TagEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class GetTagsUseCase(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke(): MutableList<TagEntity> {
        return recipesRepository.getTags()
    }

}