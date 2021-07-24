package ui.smartpro.testtaskwweather.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ui.smartpro.testtaskwweather.model.WeatherModel
import ui.smartpro.testtaskwweather.responce.OpenWeatherMapResponse

// Конвертер, чтобы в результирующем классе WeatherModel были все нужные поля пришедшие из запроса
    suspend fun convertWeatherDtoToDomain(
            openWeatherMapResponse: OpenWeatherMapResponse
    ): WeatherModel =
            withContext(Dispatchers.Default) {


                    WeatherModel(
                            name = openWeatherMapResponse.name,
                            temperature = openWeatherMapResponse.main.getTempString(),
                            wind = openWeatherMapResponse.wind.getWindString(),
                            humidity = openWeatherMapResponse.main.getHumidityString(),
                            visibility = openWeatherMapResponse.weather.firstOrNull()?.main.orEmpty(),
                            sunrise = openWeatherMapResponse.sys.getSunriseString(),
                            sunset = openWeatherMapResponse.sys.getSunsetString(),
                    )

            }
