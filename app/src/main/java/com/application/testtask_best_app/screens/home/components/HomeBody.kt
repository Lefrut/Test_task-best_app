package com.application.testtask_best_app.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.testtask_best_app.R
import com.application.testtask_best_app.domain.central.model.WeatherModel
import com.application.testtask_best_app.screens.home.model.HomeState
import com.application.testtask_best_app.ui.theme.BestAppTheme
import java.time.format.TextStyle

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    viewState: HomeState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = viewState.cityName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.weight(1f))
        WeatherDisplay(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            currentWeather = viewState.currentWeather
        )
        Spacer(Modifier.weight(1.5f))
        ForecastColumn(
            modifier = Modifier.padding(bottom = 20.dp),
            forecastList = viewState.forecastList
        )
        Spacer(Modifier.weight(0.5f))
    }
}

@Composable
fun WeatherDisplay(modifier: Modifier = Modifier, currentWeather: WeatherModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(R.string.celsius, currentWeather.currentCelsius),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = currentWeather.text.replaceFirstChar { it.uppercase() },
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall
        )
    }

}

@Composable
fun ForecastColumn(modifier: Modifier = Modifier, forecastList: List<WeatherModel>) {
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        forecastList.forEach { forecast ->
            ForecastItem(forecast = forecast)
        }
    }
}

@Composable
fun ForecastItem(modifier: Modifier = Modifier, forecast: WeatherModel) {
    Card(
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical =  14.dp, horizontal = 18.dp)
        ) {
            val monthName = forecast.date.dayOfWeek.getDisplayName(
                TextStyle.SHORT_STANDALONE,
                java.util.Locale.getDefault()
            )
            Text(
                text = monthName.uppercase(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(
                    R.string.min_max_celsius,
                    forecast.minCelsius,
                    forecast.maxCelsius
                ),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeBodyPreview() {
    BestAppTheme {
        HomeBody(
            viewState = HomeState()
        )
    }
}