package com.most4dev.recipesapp.di

import com.most4dev.recipesapp.domain.usecases.AddDishUseCase
import com.most4dev.recipesapp.domain.usecases.GetCategoriesRecipesUseCase
import com.most4dev.recipesapp.domain.usecases.GetDataUserUseCase
import com.most4dev.recipesapp.domain.usecases.GetListDishesUseCase
import com.most4dev.recipesapp.domain.usecases.GetTagsUseCase
import com.most4dev.recipesapp.domain.usecases.SelectTagUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetCategoriesRecipesUseCase(recipesRepository = get())
    }

    factory {
        GetListDishesUseCase(recipesRepository = get())
    }

    factory {
        GetTagsUseCase(recipesRepository = get())
    }

    factory {
        SelectTagUseCase(recipesRepository = get())
    }

    factory {
        AddDishUseCase(recipesRepository = get())
    }

    factory {
        GetDataUserUseCase(profileRepository = get())
    }
}