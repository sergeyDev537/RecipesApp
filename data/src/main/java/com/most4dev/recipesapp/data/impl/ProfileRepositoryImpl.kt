package com.most4dev.recipesapp.data.impl

import com.most4dev.recipesapp.data.R
import com.most4dev.recipesapp.domain.entities.ProfileEntity
import com.most4dev.recipesapp.domain.repositories.ProfileRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileRepositoryImpl(): ProfileRepository {

    override fun getDataUser() = ProfileEntity(
        city = getCity(),
        date = getCurrentDate(),
        photo = R.drawable.ic_photo_placeholder
    )

    private fun getCity(): String{
        //TODO
        return ""
    }

    private fun getCurrentDate(): String{
        val date = Date(System.currentTimeMillis())
        val format = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        return format.format(date)
    }

    companion object{

        const val DATE_FORMAT = "dd MMMM yyyy"

    }
}