package com.weather.repository

import com.weather.repository.model.WeatherData
import retrofit2.Response

open class WeatherRepository(val webService: WeatherService) {

    var TAG: String = WeatherRepository::class.java.simpleName

    var service : WeatherService = webService

     fun fetchCurrentWeatherReport(request : HashMap<String, String>) : Response<WeatherData> {
        return webService.getCurrentWeatherData(request)
    }

}