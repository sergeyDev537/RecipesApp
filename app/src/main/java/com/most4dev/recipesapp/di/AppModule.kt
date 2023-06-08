package com.most4dev.recipesapp.di

import com.most4dev.recipesapp.ui.fragments.home.HomeViewModel
import com.most4dev.recipesapp.ui.fragments.listDishes.ListDishesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HomeViewModel(
            getCategoriesRecipesUseCase = get()
        )
    }

    viewModel {
        ListDishesViewModel(
            getListDishesUseCase = get(),
            getTagsUseCase = get(),
            selectTagUseCase = get()
        )
    }

}