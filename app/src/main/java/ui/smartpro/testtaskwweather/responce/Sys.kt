package ui.smartpro.testtaskwweather.responce

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)

//      "sunrise":1627045535,
//      "sunset":1627097027
