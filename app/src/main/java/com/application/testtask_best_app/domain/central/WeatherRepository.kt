package com.application.testtask_best_app.domain.central

import com.application.testtask_best_app.domain.central.model.WeatherModel

interface WeatherRepository {

    suspend fun getCityByCoordinates(lat: Double, lon: Double): String

    suspend fun getCurrentWeatherByCity(cityName: String) : WeatherModel

    suspend fun getWeatherForecast(cityName: String) : List<WeatherModel>

}