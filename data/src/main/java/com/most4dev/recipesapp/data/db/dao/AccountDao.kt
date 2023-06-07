package com.most4dev.recipesapp.data.db.dao

import androidx.room.*
import com.most4dev.recipesapp.data.db.dto.CartDishesDto

@Dao
interface AccountDao {

    @Query("SELECT * FROM cart_dishes")
    suspend fun getListDishes(): List<CartDishesDto>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDishes(cartDishesDto: CartDishesDto)

    @Delete
    suspend fun removeDishes(cartDishes: CartDishesDto)

}