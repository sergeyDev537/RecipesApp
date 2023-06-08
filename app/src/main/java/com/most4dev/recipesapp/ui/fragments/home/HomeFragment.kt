package com.most4dev.recipesapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.FragmentHomeBinding
import com.most4dev.recipesapp.ui.adapters.CategoriesAdapter
import com.most4dev.recipesapp.ui.base.BaseFragment
import com.most4dev.recipesapp.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    private val adapter: CategoriesAdapter by lazy {
        CategoriesAdapter()
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

    private fun HomeViewModel.setObserves(){
        listCategories.observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.showList()
        }
        listCategoriesError.observe(viewLifecycleOwner){
            binding.root.showSnackbar(it)
        }
    }

    private fun FragmentHomeBinding.setAdapters() {
        rvCategories.adapter = adapter

        adapter.clickCategory = {
            findNavController().navigate(R.id.action_nav_bottom_home_to_listDishesFragment)
        }
    }

    private fun FragmentHomeBinding.showList(){
        pbCategories.isGone = true
        rvCategories.isVisible = true
    }
}