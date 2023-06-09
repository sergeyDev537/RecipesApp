package com.most4dev.recipesapp.ui.fragments.listDishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.entities.TagEntity
import com.most4dev.recipesapp.domain.usecases.GetListDishesUseCase
import com.most4dev.recipesapp.domain.usecases.GetTagsUseCase
import com.most4dev.recipesapp.domain.usecases.SelectTagUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListDishesViewModel(
    private val getListDishesUseCase: GetListDishesUseCase,
    private val getTagsUseCase: GetTagsUseCase,
    private val selectTagUseCase: SelectTagUseCase,
) : ViewModel() {

    private var _listDishes = MutableLiveData<MutableList<DishEntity>>()
    val listDishes: LiveData<MutableList<DishEntity>> = _listDishes

    private var _listTags = MutableLiveData<MutableList<TagEntity>>()
    val listTags: LiveData<MutableList<TagEntity>> = _listTags

    private var _dishesError = MutableLiveData<String>()
    val dishesError: LiveData<String> = _dishesError

    init {
        getListDishes()
    }

    private fun getListDishes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _listDishes.postValue(getListDishesUseCase() ?: mutableListOf())
                _listTags.postValue(getTagsUseCase() ?: mutableListOf())
            } catch (e: Exception) {
                _dishesError.postValue(
                    e.message
                )
            }
        }
    }

    fun selectTab(position: Int){
        var list = selectTagUseCase(position = position)
        _listTags.value = list.first ?: mutableListOf()
        _listDishes.value = list.second ?: mutableListOf()
    }

}