package com.most4dev.recipesapp.ui.fragments.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.usecases.AddDishUseCase
import com.most4dev.recipesapp.domain.usecases.GetListCartUseCase
import com.most4dev.recipesapp.domain.usecases.RemoveDishUseCase
import kotlinx.coroutines.launch

class CartViewModel(
    private val getListCartUseCase: GetListCartUseCase,
    private val addDishUseCase: AddDishUseCase,
    private val removeDishUseCase: RemoveDishUseCase,
) : ViewModel() {

    val listCart = getListCartUseCase()

    fun plusCountDish(itemDish: DishEntity){
        viewModelScope.launch {
            addDishUseCase(dish = itemDish)
        }
    }

    fun minusCountDish(itemDish: DishEntity){
        viewModelScope.launch {
            removeDishUseCase(dish = itemDish)
        }
    }

}