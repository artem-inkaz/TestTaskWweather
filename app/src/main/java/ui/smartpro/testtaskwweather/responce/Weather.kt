package ui.smartpro.testtaskwweather.responce

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main")
    val main: String
)
