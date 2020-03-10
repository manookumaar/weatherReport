package com.weather.repository

import com.weather.repository.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {

    @GET("weather")
    suspend fun getCurrentWeatherData(@QueryMap request: Map<String, String>): Response<WeatherData>

}