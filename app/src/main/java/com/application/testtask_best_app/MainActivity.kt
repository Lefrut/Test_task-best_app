package com.application.testtask_best_app

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.application.testtask_best_app.core.navigation.AppNavigation
import com.application.testtask_best_app.data.weather.WeatherApi
import com.application.testtask_best_app.screens.home.HomeViewModel
import com.application.testtask_best_app.ui.theme.BestAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var weatherService: WeatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            BestAppTheme {
                AppNavigation()
            }
        }
    }
}
