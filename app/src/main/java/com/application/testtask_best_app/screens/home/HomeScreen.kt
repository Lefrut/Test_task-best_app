package com.application.testtask_best_app.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.application.testtask_best_app.screens.home.components.HomeBody
import com.application.testtask_best_app.screens.home.model.HomeState

@Composable
fun HomeScreen(
    viewState: HomeState
) {
    Scaffold { paddingValues ->
        if (viewState.isLoading) {
            CircularProgressIndicator(
                Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .size(40.dp),
                strokeWidth = 4.dp,
                trackColor = Color.Transparent,
                color = MaterialTheme.colorScheme.onBackground
            )
        } else {
            HomeBody(modifier = Modifier.padding(paddingValues), viewState = viewState)
        }
    }
}