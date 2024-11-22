package com.application.testtask_best_app.data.weather.di

import com.application.testtask_best_app.BuildConfig
import com.application.testtask_best_app.core.network.annotations.NeedApiKey
import com.application.testtask_best_app.data.weather.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
data object WeatherApiModule {

    private const val BASE_URL = "https://api.openweathermap.org/"

    private val apikeyInterceptor = ApiKeyInterceptor(BuildConfig.API_KEY)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val jsonConverter = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
            jsonConverter.asConverterFactory("application/json".toMediaType())
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(apikeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

}


class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        originalRequest.tag(Invocation::class.java)?.method()?.annotations?.firstOrNull {
            it is NeedApiKey
        } ?: return chain.proceed(originalRequest)

        val originalUrl = originalRequest.url
        val modifiedUrl = originalUrl.newBuilder()
            .addQueryParameter("appid", apiKey)
            .build()

        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(modifiedRequest)
    }
}
