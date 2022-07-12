package com.marvelapp

import android.app.Application
import com.marvelapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModule)

        }
    }
}