package com.application.testtask_best_app.domain.central

import com.application.testtask_best_app.domain.central.model.WeatherDataModel
import javax.inject.Inject

class LoadWeatherDataUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {


    suspend operator fun invoke(lat: Double, lon: Double) = kotlin.runCatching {
        val cityName = weatherRepository.getCityByCoordinates(lat, lon)
        val currentWeather = weatherRepository.getCurrentWeatherByCity(cityName)
        val next7DaysWeather = weatherRepository.getWeatherForecast(cityName)
        return@runCatching WeatherDataModel(
            cityName = cityName,
            currentWeather = currentWeather,
            next7Days = next7DaysWeather
        )
    }

}