package com.application.testtask_best_app.domain.central.model

data class WeatherDataModel(
    val cityName: String,
    val currentWeather: WeatherModel,
    val next7Days: List<WeatherModel>
)
