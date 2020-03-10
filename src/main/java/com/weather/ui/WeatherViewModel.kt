package com.weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.repository.WeatherRepository
import com.weather.repository.model.WeatherData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherViewModel(val repository: WeatherRepository) : ViewModel(){

    val weatherData = MutableLiveData<WeatherData>()

    fun fetchReport(requestMap : Map<String, String>) {

        GlobalScope.launch {
            val response = repository.fetchCurrentWeatherReport(requestMap)
            //response.enqueue(callback)
            weatherData.postValue(response.body())
            println(response)

        }

    }


}