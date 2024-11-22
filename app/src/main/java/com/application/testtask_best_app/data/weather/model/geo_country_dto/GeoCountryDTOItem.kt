package com.application.testtask_best_app.data.weather.model.geo_country_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class GeoCountryDTOItem(
    @SerialName("country")
    val country: String?,
    @SerialName("lat")
    val lat: Double?,
    @SerialName("local_names")
    val localNames: LocalNames?,
    @SerialName("lon")
    val lon: Double?,
    @SerialName("name")
    val name: String?
)