package com.most4dev.recipesapp.data.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.most4dev.recipesapp.data.db.dto.CartDishesDto
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
        weight = dto.weight ?: VALUE_EMPTY_INT,
        count = 0
    )

    fun mapDishesDbToEntity(dbDto: CartDishesDto) = DishEntity(
        id = dbDto.id,
        name = dbDto.name,
        price = dbDto.price,
        description = dbDto.description,
        image_url = dbDto.image_url,
        tags = restoreList(dbDto.tegs),
        weight = dbDto.weight,
        count = dbDto.count
    )

    fun mapEntityToDishesDb(entity: DishEntity) = CartDishesDto(
        id = entity.id,
        name = entity.name,
        price = entity.price,
        description = entity.description,
        image_url = (entity.image_url) ?: VALUE_EMPTY_STRING,
        tegs = saveList(entity.tags),
        weight = entity.weight,
        count = entity.count
    )

    @TypeConverter
    fun restoreList(listOfString: String?): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return Gson().toJson(listOfString)
    }

}