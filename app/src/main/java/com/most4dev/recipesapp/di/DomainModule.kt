package com.most4dev.recipesapp.di

import com.most4dev.recipesapp.domain.usecases.GetCategoriesRecipesUseCase
import com.most4dev.recipesapp.domain.usecases.GetDataUserUseCase
import com.most4dev.recipesapp.domain.usecases.GetListDishesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetCategoriesRecipesUseCase(recipesRepository = get())
    }

    factory {
        GetListDishesUseCase(recipesRepository = get())
    }

    factory {
        GetDataUserUseCase(profileRepository = get())
    }
}