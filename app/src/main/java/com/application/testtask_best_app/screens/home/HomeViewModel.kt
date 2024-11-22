package com.application.testtask_best_app.screens.home

import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.testtask_best_app.domain.central.LoadWeatherDataUseCase
import com.application.testtask_best_app.screens.home.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadWeatherDataUseCase: LoadWeatherDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun loadWeatherData(lat: Double, lon: Double) = viewModelScope.launch {
        loadWeatherDataUseCase(lat, lon).onSuccess { weatherData ->
            _state.update { homeState ->
                homeState.copy(
                    cityName = weatherData.cityName,
                    currentWeather = weatherData.currentWeather,
                    isLoading = false,
                    forecastList = weatherData.next7Days
                )
            }
        }.onFailure { exception ->
            println(exception)
        }
    }


}