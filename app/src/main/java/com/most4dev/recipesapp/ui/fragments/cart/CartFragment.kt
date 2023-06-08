package com.most4dev.recipesapp.ui.fragments.cart

import android.os.Bundle
import android.view.View
import com.most4dev.recipesapp.databinding.FragmentCartBinding
import com.most4dev.recipesapp.ui.adapters.CartAdapter
import com.most4dev.recipesapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel: CartViewModel by viewModel()

    private val adapter: CartAdapter by lazy {
        CartAdapter()
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

    private fun CartViewModel.setObserves() {
        listCart.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun FragmentCartBinding.setAdapters() {
        rvCart.adapter = adapter

        adapter.clickPlusCount = {
            viewModel.plusCountDish(it)
        }

        adapter.clickMinusCount = {
            viewModel.minusCountDish(it)
        }
    }

}