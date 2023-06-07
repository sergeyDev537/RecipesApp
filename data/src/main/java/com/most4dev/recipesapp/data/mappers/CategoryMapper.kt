package com.most4dev.recipesapp.data.mappers

import com.most4dev.recipesapp.data.network.dto.CategoriesRecipesDto
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity

class CategoryMapper {

    fun mapListCategoriesDtoToEntity(listDto: List<CategoriesRecipesDto>) =
        listDto.map {
            mapCategoriesDtoToEntity(it)
        }

    //CategoriesRecipesDto
    private fun mapCategoriesDtoToEntity(dto: CategoriesRecipesDto) = CategoryRecipesEntity(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.image_url
    )

}