package com.application.testtask_best_app.core.navigation

import kotlinx.serialization.Serializable

sealed interface NavScreens {

    @Serializable
    data object Home: NavScreens

}