package ui.smartpro.testtaskwweather.responce

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("humidity")
    val humidity: Int
) : Parcelable {

    fun getTempString(): String {
        return temp.toString() + " F"
    }

    fun getHumidityString(): String {
        return humidity.toString() + " %"
    }
}
