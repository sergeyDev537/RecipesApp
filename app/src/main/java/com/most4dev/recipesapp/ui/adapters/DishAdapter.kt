package com.most4dev.recipesapp.ui.adapters

import com.bumptech.glide.Glide
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.ItemDishBinding
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.ui.base.BaseAdapter
import com.most4dev.recipesapp.ui.base.BaseViewHolder

class DishAdapter : BaseAdapter<DishEntity, ItemDishBinding>(ItemDishBinding::inflate) {

    var clickDish: ((DishEntity?) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding as ItemDishBinding
        val root = binding.root
        val context = root.context

        binding.apply {
            Glide.with(context).load(item.image_url)
                .centerInside()
                .placeholder(R.drawable.no_image_placeholder)
                .into(ivDish)

            tvNameDish.text = item.name
        }

        root.setOnClickListener {
            clickDish?.invoke(item)
        }
    }
}