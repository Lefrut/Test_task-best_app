package com.application.testtask_best_app.data.weather.model.current_weather_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Rain(
    @SerialName("1h")
    val h: Double? = null
)