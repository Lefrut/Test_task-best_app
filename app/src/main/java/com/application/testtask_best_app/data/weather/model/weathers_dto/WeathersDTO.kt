package com.application.testtask_best_app.data.weather.model.weathers_dto


import androidx.annotation.Keep
import com.application.testtask_best_app.data.weather.model.current_weather_dto.CurrentWeatherDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class WeathersDTO(
    @SerialName("city")
    val city: City? = null,
    @SerialName("cnt")
    val cnt: Int? = null,
    @SerialName("cod")
    val cod: String? = null,
    @SerialName("list")
    val list: List<CurrentWeatherDTO>? = null,
    @SerialName("message")
    val message: Int? = null
)