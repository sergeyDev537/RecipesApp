package com.most4dev.recipesapp

import android.app.Application
import com.most4dev.recipesapp.di.appModule
import com.most4dev.recipesapp.di.dataModule
import com.most4dev.recipesapp.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}