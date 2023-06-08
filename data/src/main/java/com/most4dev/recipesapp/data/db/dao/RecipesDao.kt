package com.most4dev.recipesapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.most4dev.recipesapp.data.db.dto.CartDishesDto

@Dao
interface RecipesDao {

    @Query("SELECT * FROM cart_dishes")
    fun getCartListDishes(): LiveData<List<CartDishesDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDish(cartDishesDto: CartDishesDto)

    @Query("SELECT * FROM cart_dishes WHERE id LIKE '%' || :id || '%'")
    suspend fun checkDish(id: Int): CartDishesDto?

    @Delete
    suspend fun removeDish(cartDishes: CartDishesDto)

}