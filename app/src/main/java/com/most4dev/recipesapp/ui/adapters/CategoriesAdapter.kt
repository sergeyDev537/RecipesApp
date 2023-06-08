package com.most4dev.recipesapp.ui.adapters

import com.bumptech.glide.Glide
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.ItemCategoryBinding
import com.most4dev.recipesapp.domain.entities.CategoryRecipesEntity
import com.most4dev.recipesapp.ui.base.BaseAdapter
import com.most4dev.recipesapp.ui.base.BaseViewHolder

class CategoriesAdapter :
    BaseAdapter<CategoryRecipesEntity, ItemCategoryBinding>(ItemCategoryBinding::inflate) {

    var clickCategory: ((CategoryRecipesEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding as ItemCategoryBinding
        val root = binding.root
        val context = root.context

        binding.tvNameCategory.text = item.name

        Glide.with(context).load(item?.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.no_image_placeholder)
            .into(binding.ivCategory)

        root.setOnClickListener {
            clickCategory?.invoke(item)
        }

    }
}