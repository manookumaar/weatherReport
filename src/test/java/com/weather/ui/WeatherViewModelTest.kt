package com.weather.ui
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.weather.APP_ID
import com.weather._ID_REQUEST
import com.weather._KEY_APP_ID
import com.weather._KEY_ID
import com.weather.repository.WeatherRepository
import com.weather.repository.WeatherService
import com.weather.repository.model.WeatherData
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call
import retrofit2.Response

@RunWith(JUnit4::class)
class WeatherViewModelTest {

    private lateinit var repository: WeatherRepository
    private lateinit var viewModel: WeatherViewModel
    private lateinit var service: WeatherService
    lateinit var weatherData: Observer<WeatherData>

    val data = WeatherData().apply { base = "base" }
    val response = Response.success(data)

    private val validAppID = HashMap<String, String>().apply {
        put("id", "2172797")
        put("appid", "5ad7218f2e11df834b0eaf3a33a39d2a")
    }
    private val inValidAppID = HashMap<String, String>().apply {
        put("id", "asdf")
        put("appid", "appid")
    }

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
    lateinit var responseBody : ResponseBody
    private lateinit var failedResponse : Response<WeatherData>
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        service = mock()
        responseBody = mock()

        failedResponse = Response.error(500, responseBody)

        repository = WeatherRepository(mock())
        //repository = mock()

        runBlocking {
            whenever(repository.fetchCurrentWeatherReport(inValidAppID)).thenReturn(failedResponse)
            whenever(repository.fetchCurrentWeatherReport(validAppID)).thenReturn(response)
        }

        viewModel = WeatherViewModel(repository)
        weatherData = mock()

    }

    @Test
    fun fetchReport() = runBlocking {
        viewModel.weatherData.observeForever(weatherData)

        viewModel.fetchReport(validAppID)
        delay(10)

        verify(repository).fetchCurrentWeatherReport(validAppID)
        verify(weatherData, timeout(50)).onChanged(data)

    }

}