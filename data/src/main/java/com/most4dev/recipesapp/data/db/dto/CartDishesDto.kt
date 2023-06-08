package com.most4dev.recipesapp.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

@Entity(tableName = "cart_dishes")
data class CartDishesDto(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "tegs") val tegs: String,
    @ColumnInfo(name = "image_url") val image_url: String,
)