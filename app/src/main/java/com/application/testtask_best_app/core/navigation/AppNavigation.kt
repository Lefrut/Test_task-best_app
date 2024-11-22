package com.application.testtask_best_app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.testtask_best_app.screens.home.HomeDestination
import com.application.testtask_best_app.screens.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalRootNavController provides navController) {
        NavHost(
            modifier = modifier,
            navController = LocalRootNavController.current,
            startDestination = NavScreens.Home
        ) {

            composable<NavScreens.Home> {
                HomeDestination()
            }

        }
    }

}