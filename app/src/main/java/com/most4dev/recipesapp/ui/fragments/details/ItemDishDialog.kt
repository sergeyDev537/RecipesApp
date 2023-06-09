package com.most4dev.recipesapp.ui.fragments.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.DialogItemDishBinding
import com.most4dev.recipesapp.ui.base.BaseDialogFragment
import com.most4dev.recipesapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDishDialog: BaseDialogFragment<DialogItemDishBinding>(DialogItemDishBinding::inflate) {

    private val args by navArgs<ItemDishDialogArgs>()
    private val viewModel: ItemDishViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setData()
            setClickListeners()
        }
        viewModel.apply {
            setObserve()
        }
    }

    private fun ItemDishViewModel.setObserve() {
        shouldCloseScreen.observe(viewLifecycleOwner){
            binding.loadingSaveDishToDB(false)
            requireContext().showToast(getString(R.string.add_dish_to_cart))
            dismiss()
        }
    }

    private fun DialogItemDishBinding.setClickListeners() {
        btnFavorite.setOnClickListener {
            requireContext().showToast(getString(R.string.add_favorite))
        }
        btnClose.setOnClickListener {
            dismiss()
        }
        btnAddToCart.setOnClickListener {
            loadingSaveDishToDB(true)
            viewModel.addDish(args.dish)
        }
    }

    private fun DialogItemDishBinding.setData(){
        args.dish.let {dishEntity ->
            Glide.with(requireContext()).load(dishEntity.image_url)
                .centerInside()
                .placeholder(R.drawable.no_image_placeholder)
                .into(ivItemDish)
            tvNameItemDish.text = dishEntity.name
            tvPriceDish.text = String.format(
                getString(R.string.price),
                dishEntity.price
            )
            tvWeightDish.text = String.format(
                getString(R.string.weight),
                dishEntity.weight
            )
            tvDescItemDish.text = dishEntity.description
        }
    }

    private fun DialogItemDishBinding.loadingSaveDishToDB(boolean: Boolean){
        pbAddCartDish.isVisible = boolean
        btnAddToCart.isEnabled = !boolean
    }
}