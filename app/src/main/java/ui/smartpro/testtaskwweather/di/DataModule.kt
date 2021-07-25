package ui.smartpro.testtaskwweather.di

import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import ui.smartpro.testtaskwweather.api.OpenWeatherApi
import ui.smartpro.testtaskwweather.api.RetrofitModule
import ui.smartpro.testtaskwweather.ui.WeatherViewModel


val dataModule = module {
   single<OpenWeatherApi> { RetrofitModule.apiClient}
//    single {
//    RetrofitModule.create(OpenWeatherApi::class.java) as OpenWeatherApi
//    }
}



