package com.weather.di

import com.weather.BASEURL
import com.weather.repository.WeatherService
import com.weather.repository.model.WeatherData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientImpl : RetroFitClient {

    /*weather?id=7778677&amp;appid=5ad72
    18f2e11df834b0eaf3a33a39d2a*/
    override fun weatherService() : WeatherService {
        val okhttp = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().baseUrl(BASEURL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

}