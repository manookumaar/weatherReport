package com.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.weather.R
import com.weather.databinding.LayoutWeatherReportBinding
import com.weather.request
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import java.util.concurrent.TimeUnit

class WeatherReportFragment : Fragment(), KoinComponent, LifecycleOwner {
    val viewModel by inject<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val bindingImpl: LayoutWeatherReportBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_weather_report,
            container, false
        )

        bindingImpl.viewModel = viewModel

        viewModel.fetchReport(request)

        context?.let {

            val recurringWork =  PeriodicWorkRequest.Builder(RecurringWork::class.java,
                1, TimeUnit.MINUTES, 1, TimeUnit.MINUTES).build()
            context?.let {
                WorkManager.getInstance(it).enqueue(recurringWork)
                WorkManager.getInstance(it).getWorkInfoByIdLiveData(recurringWork.id)
                    .observe(viewLifecycleOwner, Observer { workInfo: WorkInfo? ->
                        if (workInfo != null) {
                            if(workInfo.state == WorkInfo.State.SUCCEEDED){
                                Log.i("", "work SUCCEEDED")

                            }
                            Log.i("", "work info")
                            // Do something with progress information
                        }
                    })
            }

        }

        return bindingImpl.root

    }
}