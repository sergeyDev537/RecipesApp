package com.most4dev.recipesapp.di

import com.most4dev.recipesapp.data.db.dao.AppDatabase
import com.most4dev.recipesapp.data.impl.ProfileRepositoryImpl
import com.most4dev.recipesapp.data.impl.RecipesRepositoryImpl
import com.most4dev.recipesapp.data.mappers.CategoryMapper
import com.most4dev.recipesapp.data.mappers.DishesMapper
import com.most4dev.recipesapp.data.network.api.ApiFactory
import com.most4dev.recipesapp.domain.repositories.ProfileRepository
import com.most4dev.recipesapp.domain.repositories.RecipesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        AppDatabase.getInstance(application = get())
    }

    single {
        ApiFactory.apiService
    }

    single {
        val database = get<AppDatabase>()
        database.accountDao()
    }

    single {
        CategoryMapper()
    }

    single {
        DishesMapper()
    }

    single<RecipesRepository> {
        RecipesRepositoryImpl(
            apiService = get(),
            categoryMapper = get(),
            dishesMapper = get(),
            recipesDao = get()
        )
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(context = androidContext())
    }

}