package ui.smartpro.testtaskwweather.api

import retrofit2.http.GET
import retrofit2.http.Query
import ui.smartpro.testtaskwweather.BuildConfig
import ui.smartpro.testtaskwweather.responce.OpenWeatherMapResponse

interface OpenWeatherApi {
    /**
     * api.openweathermap.org/data/2.5/weather?zip=94040&appid={API key}
     */

    @GET("data/2.5/weather")
    suspend fun getWeatherSuspend(
        @Query("zip") zip: Long,
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): OpenWeatherMapResponse
}