package com.weather.repository

import com.weather.repository.model.WeatherData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {

    @GET("weather")
    fun getCurrentWeatherData(@QueryMap request: HashMap<String, String>): Response<WeatherData>

}