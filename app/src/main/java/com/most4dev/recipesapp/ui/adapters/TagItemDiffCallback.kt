package com.most4dev.recipesapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.most4dev.recipesapp.domain.entities.TagEntity

class TagItemDiffCallback: DiffUtil.ItemCallback<TagEntity>() {

    override fun areItemsTheSame(oldItem: TagEntity, newItem: TagEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TagEntity, newItem: TagEntity): Boolean {
        return oldItem == newItem
    }
}