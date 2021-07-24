package ui.smartpro.testtaskwweather.api

import ui.smartpro.testtaskwweather.model.WeatherModel

sealed class State{
    class Init : State()
    data class Success(val serverResponseData: WeatherModel) : State()
    data class Error(val error: Throwable) : State()
    data class Loading(val progress: Int?) : State()
}
