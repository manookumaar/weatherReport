package com.weather.ui

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import com.weather.repository.WeatherRepository
import com.weather.request
import org.koin.core.KoinComponent
import org.koin.core.inject

class RecurringWork(context: Context, params : WorkerParameters) : Worker(context, params), KoinComponent{

    val repository by inject<WeatherRepository>()
    override fun doWork(): Result {
        repository.fetchCurrentWeatherReport(request)
        return  Result.success()
    }
}