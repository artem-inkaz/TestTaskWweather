package ui.smartpro.testtaskwweather.responce

import com.google.gson.annotations.SerializedName

data class OpenWeatherMapResponse(
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("main")
    val main: Main,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Long,
    @SerializedName("name")
    val name: String
)


