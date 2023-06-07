package com.most4dev.recipesapp.data.mappers

import com.most4dev.recipesapp.data.network.dto.DishesDto
import com.most4dev.recipesapp.domain.entities.DishEntity

class DishesMapper {

    fun mapListDishesDtoToEntity(listDto: List<DishesDto>) =
        listDto.map {
            mapDishesDtoToEntity(it)
        }

    private fun mapDishesDtoToEntity(dto: DishesDto) = DishEntity(
        id = dto.id,
        name = dto.name,
        price = dto.price,
        description = dto.description,
        image_url = dto.image_url,
        tegs = dto.tegs,
        weight = dto.weight
    )

}