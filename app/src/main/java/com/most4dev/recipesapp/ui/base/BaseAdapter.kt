package com.most4dev.recipesapp.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : ListAdapter<T, BaseViewHolder>(object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }
}
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: ViewBinding = inflate.invoke(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BaseViewHolder(binding)
    }
}