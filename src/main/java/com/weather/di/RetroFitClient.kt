package com.weather.di

import com.weather.repository.WeatherService
import okhttp3.OkHttpClient

interface RetroFitClient {

    fun weatherService(): WeatherService
}