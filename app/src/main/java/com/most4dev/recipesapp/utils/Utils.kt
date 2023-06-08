package com.most4dev.recipesapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}