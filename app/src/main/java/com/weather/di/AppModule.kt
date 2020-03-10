package com.weather.di

import com.weather.repository.WeatherRepository
import com.weather.repository.WeatherService
import com.weather.ui.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherRepostModule = module {

    single { WeatherRepository(get()) }
    viewModel { WeatherViewModel(get()) }

    single<RetroFitClient> { RetrofitClientImpl() }

    fun provideWeatherService(client : RetroFitClient ) : WeatherService {
        return client.weatherService()
    }

    single { provideWeatherService(get()) }
}