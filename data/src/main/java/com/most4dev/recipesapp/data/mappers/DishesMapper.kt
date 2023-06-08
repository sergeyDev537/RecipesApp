package com.most4dev.recipesapp.data.mappers

import com.most4dev.recipesapp.data.impl.RecipesRepositoryImpl.Companion.VALUE_EMPTY_INT
import com.most4dev.recipesapp.data.impl.RecipesRepositoryImpl.Companion.VALUE_EMPTY_STRING
import com.most4dev.recipesapp.data.network.dto.DishesDto
import com.most4dev.recipesapp.domain.entities.DishEntity

class DishesMapper {

    fun mapListDishesDtoToEntity(listDto: List<DishesDto>) =
        listDto.map {
            mapDishesDtoToEntity(it)
        }

    private fun mapDishesDtoToEntity(dto: DishesDto) = DishEntity(
        id = dto.id ?: VALUE_EMPTY_INT,
        name = dto.name ?: VALUE_EMPTY_STRING,
        price = dto.price ?: VALUE_EMPTY_INT,
        description = dto.description ?: VALUE_EMPTY_STRING,
        image_url = dto.image_url,
        tags = dto.tegs ?: listOf(),
        weight = dto.weight?: VALUE_EMPTY_INT
    )

}