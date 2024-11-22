package com.application.testtask_best_app.screens.home.model

import com.application.testtask_best_app.domain.central.model.WeatherModel
import java.time.ZonedDateTime

data class HomeState(
    val cityName: String = "",
    val currentWeather: WeatherModel = WeatherModel(
        ZonedDateTime.now(),
        1,
        10,
        2,
        ""
    ),
    val isLoading: Boolean = true,
    val forecastList: List<WeatherModel> = emptyList()
)
