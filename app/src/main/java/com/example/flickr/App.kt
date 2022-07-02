package com.example.flickr

import android.app.Application
import com.example.flickr.common.CommonModule
import com.example.flickr.main.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import timber.log.Timber


    class App : Application() {
        override fun onCreate() {
            super.onCreate()
            Timber.plant(Timber.DebugTree())
            setupKoin()
        }

        private fun setupKoin() {
            GlobalContext.startKoin {
                androidContext(this@App)
                modules(
                    CommonModule.createRetrofit(),
                    MainModule.create()
                )
            }
        }
    }