package com.most4dev.recipesapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}