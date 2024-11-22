package com.application.testtask_best_app.data.repository.di

import com.application.testtask_best_app.data.repository.WeatherRepositoryImpl
import com.application.testtask_best_app.domain.central.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

}