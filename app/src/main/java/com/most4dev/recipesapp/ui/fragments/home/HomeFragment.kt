package com.most4dev.recipesapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.most4dev.recipesapp.databinding.FragmentHomeBinding
import com.most4dev.recipesapp.ui.adapters.CategoriesAdapter
import com.most4dev.recipesapp.ui.base.BaseFragment
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

        }
        listCategoriesError.observe(viewLifecycleOwner){

        }
    }

    private fun FragmentHomeBinding.setAdapters() {
        rvCategories.adapter = adapter

        adapter.clickCategory = {
//            findNavController().navigate(R.id.action_nav_bottom_home_to_itemProduct)
        }
    }
}