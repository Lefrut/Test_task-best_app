package com.application.testtask_best_app.data.weather.model.weathers_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Sys(
    @SerialName("pod")
    val pod: String? = null
)