package com.application.testtask_best_app.screens.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.application.testtask_best_app.core.android.getCurrentLocation
import com.application.testtask_best_app.screens.home.model.HomeState

@Composable
fun HomeDestination(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val viewState: HomeState by homeViewModel.state.collectAsStateWithLifecycle()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = onResult@{ isGranted ->
            if (!isGranted) return@onResult

            getCurrentLocation(context) { lat, lon ->
                homeViewModel.loadWeatherData(lat, lon)
            }
        }
    )

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            getCurrentLocation(context) { lat, lon ->
                homeViewModel.loadWeatherData(lat, lon)
            }
        }

    }


    HomeScreen(viewState)
}

