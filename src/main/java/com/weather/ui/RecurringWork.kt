package com.weather.ui

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.weather.repository.WeatherRepository
import com.weather.request
import org.koin.core.KoinComponent
import org.koin.core.inject

class RecurringWork(context: Context, params: WorkerParameters) : Worker(context, params),
    KoinComponent {

    val repository by inject<WeatherRepository>()

    override fun doWork(): Result {
        val data: Data.Builder = Data.Builder()
        //data.put("key", response.body())


        return Result.success(data.build())
    }
}