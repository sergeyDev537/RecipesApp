package com.most4dev.recipesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.most4dev.recipesapp.databinding.ItemTagsDisableBinding
import com.most4dev.recipesapp.databinding.ItemTagsEnableBinding
import com.most4dev.recipesapp.domain.entities.TagEntity

class TagsAdapter : ListAdapter<TagEntity, TagViewHolder>(TagItemDiffCallback()) {

    var onTagClick: ((TagEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding =
            when (viewType) {
                VIEW_TYPE_ENABLE -> ItemTagsEnableBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                VIEW_TYPE_DISABLE -> ItemTagsDisableBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                else -> throw RuntimeException("Unknown view type $viewType")
            }

        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        val root = binding.root

        root.setOnClickListener {
            onTagClick?.invoke(item)
        }

        when (binding) {
            is ItemTagsEnableBinding -> {
                binding.tvTag.text = item.name
            }
            is ItemTagsDisableBinding -> {
                binding.tvTag.text = item.name
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.pressed) {
            VIEW_TYPE_ENABLE
        } else {
            VIEW_TYPE_DISABLE
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLE = 0
        const val VIEW_TYPE_DISABLE = 1
    }

}