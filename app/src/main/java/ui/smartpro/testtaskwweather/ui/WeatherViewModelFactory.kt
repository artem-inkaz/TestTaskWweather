package ui.smartpro.testtaskwweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.create
import ui.smartpro.testtaskwweather.api.RetrofitModule

class WeatherViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
  //      WeatherViewModel::class.java -> WeatherViewModel(apiService = RetrofitModule.retrofit.create())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}