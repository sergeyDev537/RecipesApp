package com.most4dev.recipesapp.di

import com.most4dev.recipesapp.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HomeViewModel(
            getCategoriesRecipesUseCase = get()
        )
    }

}