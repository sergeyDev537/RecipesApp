package com.most4dev.recipesapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.most4dev.recipesapp.ui.toolbar.TypeToolbar
import com.most4dev.recipesapp.ui.toolbar.UpdateToolbar

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
    private val typeToolbar: TypeToolbar
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var updateToolbar: UpdateToolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UpdateToolbar) {
            updateToolbar = context
        } else {
            throw RuntimeException("Activity must implement UpdateToolbar")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar.updateTypeToolbar(typeToolbar = typeToolbar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}