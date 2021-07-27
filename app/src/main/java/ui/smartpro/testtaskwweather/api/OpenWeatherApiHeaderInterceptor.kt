package ui.smartpro.testtaskwweather.api

import okhttp3.Interceptor
import okhttp3.Response
import ui.smartpro.testtaskwweather.BuildConfig

private const val API_KEY_HEADER = "x-api-key"

class OpenWeatherApiHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val request = originalRequest.newBuilder()
            .url(originalHttpUrl)
            .addHeader(API_KEY_HEADER, BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }
}