package com.most4dev.recipesapp.data.db.dao

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.most4dev.recipesapp.data.db.dto.CartDishesDto

@Database(entities = [CartDishesDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): RecipesDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        const val DB_NAME = "recipes.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}