package com.application.testtask_best_app.domain.central.model

import java.time.ZonedDateTime

data class WeatherModel(
    val date: ZonedDateTime,
    val minCelsius: Int,
    val maxCelsius: Int,
    val currentCelsius: Int,
    val text: String = ""
)

