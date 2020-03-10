package com.weather.repository

import com.weather.repository.model.WeatherData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class WeatherRepository(private val webService: WeatherService) {

    var TAG: String = WeatherRepository::class.java.simpleName

     suspend fun fetchCurrentWeatherReport(request : Map<String, String>) : Response<WeatherData> {
        return webService.getCurrentWeatherData(request)
    }

}