package com.weather

import android.app.Application
import com.weather.di.weatherRepostModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()
            androidContext(this@WeatherApplication)
            modules(listOf( weatherRepostModule))
        }
    }
}