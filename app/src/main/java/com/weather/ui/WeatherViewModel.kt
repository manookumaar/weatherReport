package com.weather.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.APP_ID
import com.weather._ID_REQUEST
import com.weather._KEY_APP_ID
import com.weather._KEY_ID
import com.weather.repository.WeatherRepository
import com.weather.repository.model.WeatherData

class WeatherViewModel(val repository: WeatherRepository) : ViewModel(){

    val weatherData = MutableLiveData<WeatherData>()

    fun fetchReport(request : HashMap<String, String>) {
        val response = repository.fetchCurrentWeatherReport(request)
        weatherData.value =response.body()
    }

}