package com.application.testtask_best_app.data.weather

import com.application.testtask_best_app.core.network.annotations.NeedApiKey
import com.application.testtask_best_app.data.weather.model.current_weather_dto.CurrentWeatherDTO
import com.application.testtask_best_app.data.weather.model.geo_country_dto.GeoCityDTO
import com.application.testtask_best_app.data.weather.model.weathers_dto.WeathersDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @NeedApiKey
    @GET("data/2.5/weather?")
    suspend fun getCurrentWeatherByCoordinates(
       @Query("lat") lat: Double,
       @Query("lon") lon: Double,
       @Query("mode") mode: String = "",
       @Query("units") units: String = "metric",
       @Query("lang") lang: String = "ru"
    ): CurrentWeatherDTO

    @NeedApiKey
    @GET("data/2.5/weather?")
    suspend fun getCurrentWeatherByCity(
        @Query("q") q: String,
        @Query("mode") mode: String = "",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): CurrentWeatherDTO

    @NeedApiKey
    @GET("geo/1.0/reverse?")
    suspend fun getCityByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "ru"
    ): GeoCityDTO

    @NeedApiKey
    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("cnt") count: Int = 100,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric",
    ): WeathersDTO


}