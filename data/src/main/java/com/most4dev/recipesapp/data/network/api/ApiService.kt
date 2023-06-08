package com.most4dev.recipesapp.data.network.api

import com.most4dev.recipesapp.data.network.dto.CategoriesRecipesDto
import com.most4dev.recipesapp.data.network.dto.DishesDto
import com.most4dev.recipesapp.data.network.dto.ListCategoriesDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): Response<ListCategoriesDto>

    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): Response<List<DishesDto>>

}