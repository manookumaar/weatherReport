package com.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.weather.R
import com.weather.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class WeatherReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reportFragment = WeatherReportFragment()
        supportFragmentManager.beginTransaction().add(R.id.reportFrameLayout, reportFragment).commit()

    }
}