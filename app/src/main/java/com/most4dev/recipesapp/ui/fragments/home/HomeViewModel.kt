package com.most4dev.recipesapp.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.usecases.GetCategoriesRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCategoriesRecipesUseCase: GetCategoriesRecipesUseCase,
) : ViewModel() {

    private var _listCategories = MutableLiveData<List<CategoryRecipesEntity>>()
    val listCategories: LiveData<List<CategoryRecipesEntity>> = _listCategories

    private var _listCategoriesError = MutableLiveData<String>()
    val listCategoriesError: LiveData<String> = _listCategoriesError

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _listCategories.postValue(getCategoriesRecipesUseCase() ?: emptyList())
            } catch (e: Exception) {
                _listCategoriesError.postValue(
                    e.message
                )
            }
        }
    }

}