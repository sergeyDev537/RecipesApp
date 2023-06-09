package com.most4dev.recipesapp.ui.adapters

import com.bumptech.glide.Glide
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.ItemCartBinding
import com.most4dev.recipesapp.domain.entities.DishEntity
import com.most4dev.recipesapp.ui.base.BaseAdapter
import com.most4dev.recipesapp.ui.base.BaseViewHolder

class CartAdapter :
    BaseAdapter<DishEntity, ItemCartBinding>(ItemCartBinding::inflate) {

    var clickPlusCount: ((DishEntity) -> Unit)? = null
    var clickMinusCount: ((DishEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding as ItemCartBinding
        val root = binding.root
        val context = root.context

        binding.apply {
            Glide.with(context).load(item?.image_url)
                .centerCrop()
                .placeholder(R.drawable.no_image_placeholder)
                .into(ivDish)

            tvNameItemDish.text = item.name
            tvPriceDish.text = String.format(
                context.getString(R.string.price),
                item.price
            )
            tvWeightDish.text = String.format(
                context.getString(R.string.weight),
                item.weight
            )
            layoutCounter.tvCounter.text = item.count.toString()

            layoutCounter.btnPlus.setOnClickListener {
                clickPlusCount?.invoke(item)
            }
            layoutCounter.btnMinus.setOnClickListener {
                clickMinusCount?.invoke(item)
            }
        }





    }
}