package ui.smartpro.testtaskwweather.di

import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import ui.smartpro.testtaskwweather.ui.WeatherViewModel


//val dataModule = module {
//   single<OpenWeatherApi> { RetrofitModule.apiClient}
    // retrofit service
//    single {
//    RetrofitModule.create(OpenWeatherApi::class.java) as OpenWeatherApi
//    }
//}

val viewModelModules = module {
    viewModel { WeatherViewModel(get()) }
//    viewModel { params -> WeatherViewModel(get()) }// injected params
}

