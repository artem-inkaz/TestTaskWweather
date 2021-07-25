package ui.smartpro.testtaskwweather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ui.smartpro.testtaskwweather.api.OpenWeatherApi
import ui.smartpro.testtaskwweather.api.State
import ui.smartpro.testtaskwweather.api.convertWeatherDtoToDomain
import ui.smartpro.testtaskwweather.model.WeatherModel

class WeatherViewModel(private val apiService: OpenWeatherApi) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> get() = _state

    private var _stateBoolean = MutableLiveData<Boolean>(false)
    val stateBoolean: LiveData<Boolean> get() = _stateBoolean

    private val _mutableLiveDataWeather = MutableLiveData<WeatherModel>()
    val weatherLive: LiveData<WeatherModel> get() = _mutableLiveDataWeather

    private val saveEnabled = MutableLiveData(false)
    fun saveEnabled(): LiveData<Boolean>? {
        return saveEnabled
    }
init {
    _stateBoolean.value = false;
}
//    var listWeather = listOf<WeatherModel>()

    fun validateInput(newName: String) {
        saveEnabled.setValue(!newName.isEmpty())
    }

    fun updateData(zipCode: Long) {

        viewModelScope.launch {
            try {
//                _stateBoolean.value = true;
                _state.value = State.Loading(null)
                // get data Weather
                val weather = apiService.getWeatherSuspend(zipCode)
                // get Weather domain data
                val weatherData = convertWeatherDtoToDomain(weather)
                _mutableLiveDataWeather.value = weatherData
                _stateBoolean.value = false
                _state.value = State.Success(weatherData)
            } catch (e: Exception) {
                val message = "Ошибка загрузки данных"
                _state.value = State.Error(Throwable(message))
                _stateBoolean.value = true
                Log.e(
                        ViewModel::class.java.simpleName,
                        "Error grab data from openweathermap.org data ${e.message}"
                )
            }
        }
    }
}