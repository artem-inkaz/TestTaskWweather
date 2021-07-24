package ui.smartpro.testtaskwweather.responce

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Sys(
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
): Parcelable {

    @SuppressLint("SimpleDateFormat")
    fun getSunriseString(): String {
        val date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date (sunrise*1000));
        return date
    }
    @SuppressLint("SimpleDateFormat")
    fun getSunsetString(): String {
        val date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date (sunset*1000));
        return date
    }


}

//	String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (unix время*1000));
//      "sunrise":1627045535,
//      "sunset":1627097027
