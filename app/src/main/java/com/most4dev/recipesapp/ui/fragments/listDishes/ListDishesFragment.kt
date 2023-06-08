package com.most4dev.recipesapp.ui.fragments.listDishes

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.FragmentHomeBinding
import com.most4dev.recipesapp.databinding.FragmentListDishesBinding
import com.most4dev.recipesapp.ui.adapters.CategoriesAdapter
import com.most4dev.recipesapp.ui.adapters.DishAdapter
import com.most4dev.recipesapp.ui.adapters.TagsAdapter
import com.most4dev.recipesapp.ui.base.BaseFragment
import com.most4dev.recipesapp.ui.base.MarginItemDecorationGrid
import com.most4dev.recipesapp.ui.fragments.home.HomeViewModel
import com.most4dev.recipesapp.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListDishesFragment :
    BaseFragment<FragmentListDishesBinding>(FragmentListDishesBinding::inflate) {

    private val viewModel: ListDishesViewModel by viewModel()

    private val tagAdapter: TagsAdapter by lazy {
        TagsAdapter()
    }

    private val dishAdapter: DishAdapter by lazy {
        DishAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setAdapters()
        }
        viewModel.apply {
            setObserves()
        }
    }

    private fun ListDishesViewModel.setObserves(){
        listDishes.observe(viewLifecycleOwner){
            dishAdapter.submitList(it)
        }
        listTags.observe(viewLifecycleOwner){
            tagAdapter.submitList(it.toMutableList())
        }
        dishesError.observe(viewLifecycleOwner){
            binding.root.showSnackbar(it)
        }
    }

    private fun FragmentListDishesBinding.setAdapters() {
        rvTags.adapter = tagAdapter
        tagAdapter.onTagClick = {tag ->
            val position = viewModel.listTags.value?.indexOf(tag)
            position?.let {
                viewModel.selectTab(it)
            } ?: binding.root.showSnackbar(getString(R.string.not_found_element))
        }

        rvDishes.adapter = dishAdapter
        rvDishes.addItemDecoration(MarginItemDecorationGrid(8))
        dishAdapter.clickDish = {

        }
    }

}