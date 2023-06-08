package com.most4dev.recipesapp.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.most4dev.recipesapp.data.db.dao.RecipesDao
import com.most4dev.recipesapp.data.mappers.CategoryMapper
import com.most4dev.recipesapp.data.mappers.DishesMapper
import com.most4dev.recipesapp.data.network.api.ApiService
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.domain.entities.TagEntity
import com.most4dev.recipesapp.domain.repositories.RecipesRepository

class RecipesRepositoryImpl(
    private val apiService: ApiService,
    private val categoryMapper: CategoryMapper,
    private val dishesMapper: DishesMapper,
    private val recipesDao: RecipesDao,
) : RecipesRepository {

    var listDishes = mutableListOf<DishEntity>()
    private var listTags = mutableListOf<TagEntity>()

    override suspend fun getCategoriesRecipes(): MutableList<CategoryRecipesEntity> {
        return apiService.getCategories().body()?.let {
            categoryMapper.mapListCategoriesDtoToEntity(it.сategories).toMutableList()
        } ?: mutableListOf()
    }

    override suspend fun getListDishes(): MutableList<DishEntity> {
        checkListDishes {}
        return listDishes
    }

    override suspend fun getTags(): MutableList<TagEntity> {
        checkListDishes {
            getListTags()
        }
        return listTags
    }

    override fun selectTag(position: Int): Pair<MutableList<TagEntity>, MutableList<DishEntity>> {
        val listTags = selectItem(position)
        val listDishes = updateListDishes(listTags[position].name)
        return Pair(listTags, listDishes)
    }

    override fun getCartListDishes(): LiveData<List<DishEntity>> =
        recipesDao.getCartListDishes().map { listCartDishes ->
            listCartDishes.map { itemCartDishes ->
                dishesMapper.mapDishesDbToEntity(itemCartDishes)
            }
        }

    override suspend fun addDish(dish: DishEntity) {
        val listCart = getCartListDishes().value
        val containDish = checkDish(dish.id)
        containDish?.let {
            recipesDao.addDish(dishesMapper.mapEntityToDishesDb(dish.copy(count = it.count + 1)))
        } ?: run {
            recipesDao.addDish(dishesMapper.mapEntityToDishesDb(dish))
        }
    }

    private suspend fun checkDish(id: Int): DishEntity? {
        return recipesDao.checkDish(id)?.let {
            dishesMapper.mapDishesDbToEntity(it)
        } ?: kotlin.run {
            null
        }
    }

    private fun getListTags(): List<TagEntity> {
        val listTags = mutableSetOf<TagEntity>()
        for (itemDish in listDishes) {
            for (itemTag in itemDish.tags) {
                listTags.add(TagEntity(itemTag, false))
            }
        }
        val currentList = listTags.toMutableList()
        this.listTags = currentList
        return selectItem(0)
    }

    private fun selectItem(position: Int): MutableList<TagEntity> {
        for (tagPosition in 0 until listTags.size) {
            val tag = listTags[tagPosition]
            if (tag.pressed) {
                setPressed(tagPosition, false)
            }
        }
        setPressed(position, true)
        return listTags
    }

    private fun updateListDishes(tagSearch: String): MutableList<DishEntity> {
        val updatedList = listDishes.filter { dish ->
            dish.tags.contains(tagSearch)
        }
        return updatedList.toMutableList()
    }

    private fun setPressed(position: Int, pressed: Boolean) {
        listTags[position] = listTags[position].copy(pressed = pressed)
    }

    private suspend inline fun checkListDishes(nextAction: () -> Unit) {
        if (listDishes.isEmpty()) {
            listDishes = getListDishNetwork()

        } else {
            nextAction()
        }
    }

    private suspend fun getListDishNetwork(): MutableList<DishEntity> {
        return apiService.getDishes().body()?.let {
            dishesMapper.mapListDishesDtoToEntity(it.dishes).toMutableList()
        } ?: mutableListOf()
    }

    companion object {

        const val VALUE_EMPTY_STRING = ""
        const val VALUE_EMPTY_INT = -1

    }

}