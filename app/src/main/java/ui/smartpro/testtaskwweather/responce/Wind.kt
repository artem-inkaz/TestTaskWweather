package ui.smartpro.testtaskwweather.responce

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    @SerializedName("speed")
    val speed: Double
): Parcelable {

    fun getWindString(): String {
        return speed.toString() + " mph"
    }

}