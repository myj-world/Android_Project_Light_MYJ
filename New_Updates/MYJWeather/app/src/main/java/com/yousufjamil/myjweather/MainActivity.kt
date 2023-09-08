package com.yousufjamil.myjweather

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yousufjamil.myjweather.background.GetCurrentWeather
import com.yousufjamil.myjweather.ui.theme.MYJWeatherTheme

class MainActivity : ComponentActivity() {
    private lateinit var getCurrentWeatherData: GetCurrentWeather
    private var currentWeatherResult = ""

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCurrentWeather(getLocation())

        setContent {
            MYJWeatherTheme {
                var recompose by remember {
                    mutableStateOf(0)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(recompose.dp - recompose.dp)
                ) {
                    if (getLocation() == "") {
                        var locationInput by remember {
                            mutableStateOf("")
                        }

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                value = locationInput,
                                onValueChange = { newvalue ->
                                    locationInput = newvalue
                                },
                                label = {
                                    Text(text = "City")
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = { setLocation(locationInput) }) {
                                Text(text = "Submit")
                            }
                        }
                    } else {
                        Text(text = currentWeatherResult)
                    }
                    Handler().postDelayed({ if (recompose > 0) recompose-- else recompose++ }, 1000)
                }
            }
        }
    }

    private fun getLocation(): String {
        return getSharedPreferences("myj_weather_info", MODE_PRIVATE).getString("loc_info", "")
            .toString()
    }

    private fun setLocation(location: String) {
        getSharedPreferences("myj_weather_info", MODE_PRIVATE).edit()
            .putString("loc_info", location).apply()
    }

    private fun getCurrentWeather(city: String) {
        val currentCity = getLocation()
        getCurrentWeatherData = GetCurrentWeather(this@MainActivity, currentCity)
        if (currentCity != "") getCurrentWeatherData.execute()

        checkStatus()
    }

    private fun checkStatus() {
        Handler().postDelayed(
            {
                if (getCurrentWeatherData.status.toString() == "FINISHED") {
                    currentWeatherResult = getCurrentWeatherData.finalresult.toString()
                } else {
                    checkStatus()
                }
            }, 1000
        )
    }
}