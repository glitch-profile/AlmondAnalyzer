package com.glitchdev.almondanalyzer

import android.app.Application
import com.glitchdev.almondanalyzer.core.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelsModule
                )
            )
        }
    }
}