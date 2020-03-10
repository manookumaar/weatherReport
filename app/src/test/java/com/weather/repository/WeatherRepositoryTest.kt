package com.weather.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.weather.repository.model.WeatherData
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Response.error

class WeatherRepositoryTest {

    private lateinit var repository: WeatherRepository
    private lateinit var service : WeatherService

    val data = WeatherData().apply { base = "base" }
    val response =  Response.success(data)

    lateinit var responseBody : ResponseBody
    private lateinit var failedResponse : Response<WeatherData>

    private val validAppID = HashMap<String, String>().apply {
        put("id", "2172797")
        put("appid", "5ad7218f2e11df834b0eaf3a33a39d2a")
    }
    private val inValidAppID = HashMap<String, String>().apply {
        put("id", "asdf")
        put("appid", "appid")
    }

    @Before
    fun setUp(){
        service = mock()
        responseBody = mock()
        failedResponse = error(500, responseBody)

        repository = WeatherRepository(service)
        runBlocking {
            whenever(repository.fetchCurrentWeatherReport(inValidAppID)).thenReturn(failedResponse)
            whenever(repository.fetchCurrentWeatherReport(validAppID)).thenReturn(response)
        }

    }

    @Test
    fun with_valid_creditials() = runBlocking {
        assertEquals(response, repository.fetchCurrentWeatherReport(validAppID))
    }

    @Test
    fun with_InValid_creditials() = runBlocking {
        assertEquals(failedResponse, repository.fetchCurrentWeatherReport(inValidAppID))
    }

    companion object {
        val APPID = ""
        val ID = ""
    }

}