package com.application.testtask_best_app.data.weather.model.weathers_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Snow(
    @SerialName("3h")
    val h: Double? = null
)