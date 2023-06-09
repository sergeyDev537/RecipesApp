package com.most4dev.recipesapp.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.usecases.AddDishUseCase
import kotlinx.coroutines.launch

class ItemDishViewModel(
    private val addDishUseCase: AddDishUseCase,
) : ViewModel() {

    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    fun addDish(itemDish: DishEntity){
        viewModelScope.launch {
            addDishUseCase(dish = itemDish)
            finishWork()
        }
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}