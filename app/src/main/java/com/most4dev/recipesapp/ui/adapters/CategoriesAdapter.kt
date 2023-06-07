package com.most4dev.recipesapp.ui.adapters

import com.bumptech.glide.Glide
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.ItemCategoryBinding
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.ui.base.BaseAdapter
import com.most4dev.recipesapp.ui.base.BaseViewHolder

class CategoriesAdapter :
    BaseAdapter<CategoryRecipesEntity, ItemCategoryBinding>(ItemCategoryBinding::inflate) {

    var clickCategory: ((CategoryRecipesEntity?) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        context?.let {
            binding?.ivCategory?.let { imageView ->
                Glide.with(it).load(item?.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.no_image_placeholder)
                    .into(imageView)
            }
        }
        root?.setOnClickListener {
            clickCategory?.invoke(item)
        }

    }
}