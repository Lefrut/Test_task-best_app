package com.application.testtask_best_app.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalRootNavController = staticCompositionLocalOf<NavHostController>{
    error("no RootNavController")
}