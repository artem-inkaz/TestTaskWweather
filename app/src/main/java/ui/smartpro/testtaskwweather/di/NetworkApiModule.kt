package ui.smartpro.testtaskwweather.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.smartpro.testtaskwweather.BuildConfig
import ui.smartpro.testtaskwweather.api.OpenWeatherApi
import ui.smartpro.testtaskwweather.api.OpenWeatherApiHeaderInterceptor

val networkApiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(OpenWeatherApiHeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(OpenWeatherApi::class.java) }
}

