package com.application.testtask_best_app.data.repository

import com.application.testtask_best_app.data.weather.WeatherApi
import com.application.testtask_best_app.domain.central.WeatherRepository
import com.application.testtask_best_app.domain.central.model.WeatherModel
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlin.math.roundToInt


class WeatherRepositoryImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRepository {
    override suspend fun getCityByCoordinates(lat: Double, lon: Double): String {
        val cityDTO = weatherApi.getCityByCoordinates(lat, lon).firstOrNull()
            ?: throw IllegalArgumentException("Weather API doesn't have city")
        return cityDTO.localNames?.ru ?: cityDTO.localNames?.en ?: cityDTO.name
        ?: throw throw IllegalArgumentException("CityDTO doesn't have city")
    }

    override suspend fun getCurrentWeatherByCity(cityName: String): WeatherModel {
        val currentWeatherDTO = weatherApi.getCurrentWeatherByCity(cityName)
        val mainData = currentWeatherDTO.main
        return WeatherModel(
            date = ZonedDateTime.now(),
            minCelsius = mainData?.tempMin?.roundToInt()
                ?: throw IllegalArgumentException(),
            maxCelsius = mainData.tempMax?.roundToInt()
                ?: throw IllegalArgumentException(),
            currentCelsius = mainData.temp?.roundToInt()
                ?: throw IllegalArgumentException(),
            text = currentWeatherDTO.weather?.firstOrNull()?.description
                ?: throw IllegalArgumentException()
        )
    }

    override suspend fun getWeatherForecast(cityName: String): List<WeatherModel> {
        val response = weatherApi.getWeatherForecast(cityName)
        val weathers = response.list ?: throw IllegalArgumentException()
        val offsetInSeconds = response.city?.timezone ?: 0
        val offset = ZoneOffset.ofTotalSeconds(offsetInSeconds)
        val zoneId = ZoneId.ofOffset("UTC", offset)

        val weatherModels = weathers.groupBy {
            ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(it.dt?.toLong() ?: 0),
                zoneId
            ).toLocalDate()
        }.map { map ->
            val tempMax = map.value.mapNotNull { it.main?.tempMax }.maxOfOrNull { it } ?: 0.0
            val tempMin = map.value.mapNotNull { it.main?.feelsLike }.maxOfOrNull { it } ?: 0.0
            val avgTemp = map.value.mapNotNull { it.main?.temp }.average()
            WeatherModel(
                date = ZonedDateTime.of(map.key.atTime(12, 10), zoneId),
                minCelsius = tempMin.roundToInt(),
                maxCelsius = tempMax.roundToInt(),
                currentCelsius = avgTemp.roundToInt(),
                text = map.value.firstOrNull()?.weather?.firstOrNull()?.description ?: ""
            )
        }.filterIndexed { index, _ -> index != 0 }
        return weatherModels
    }


}
