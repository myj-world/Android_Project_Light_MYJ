package com.yousufjamil.myjweather

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.yousufjamil.myjweather.background.GetCurrentWeather
import com.yousufjamil.myjweather.ui.theme.MYJWeatherTheme
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private lateinit var getCurrentWeatherData: GetCurrentWeather
    private var currentWeatherResult = ""
    private var name = ""
    private var region = ""
    private var country = ""
    private var lat = ""
    private var lon = ""
    private var last_updated = ""
    private var temp_c = ""
    private var temp_f = ""
    private var isday = ""
    private var weathertext = ""
    private var weather_icon = ""
    private var wind_mph = ""
    private var wind_kph = ""
    private var wind_dir = ""
    private var rain_mm = ""
    private var rain_in = ""
    private var humidity = ""
    private var feelslike_c = ""
    private var feelslike_f = ""
    private var vis_km = ""
    private var vis_mi = ""
    private var uv = ""
    private var gust_mph = ""
    private var gust_kph = ""

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
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(135, 206, 235)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                value = locationInput,
                                onValueChange = { newvalue ->
                                    locationInput = newvalue
                                },
                                label = {
                                    Text(text = "City/Country")
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = { setLocation(locationInput) }) {
                                Text(text = "Get Weather")
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(135, 206, 235))
                        ) {
                            Button(onClick = {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Just a moment...",
                                    Toast.LENGTH_SHORT
                                ).show()
                                getSharedPreferences("myj_weather_info", MODE_PRIVATE).edit()
                                    .putString("loc_info", "").apply()
                                currentWeatherResult = ""
                                name = ""
                                region = ""
                                country = ""
                                lat = ""
                                lon = ""
                                last_updated = ""
                                temp_c = ""
                                temp_f = ""
                                isday = ""
                                weathertext = ""
                                wind_mph = ""
                                wind_kph = ""
                                wind_dir = ""
                                rain_mm = ""
                                rain_in = ""
                                humidity = ""
                                feelslike_c = ""
                                feelslike_f = ""
                                vis_km = ""
                                vis_mi = ""
                                uv = ""
                                gust_mph = ""
                                gust_kph = ""
                                weather_icon = ""
                            }) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Change location",
                                        tint = Color.White
                                    )
                                    Text(text = "Change Location")
                                }
                            }
                            decodeData()
//                            Text(text = currentWeatherResult)
                            Image(
                                painter = rememberAsyncImagePainter(model = weather_icon),
                                contentDescription = "Weather Icon",
                                modifier = Modifier
                                    .size(100.dp)
                            )
                            println("img: $weather_icon")
                            Text(text = "Location: $name, $region, $country")
                            Text(text = "Map position: $lon, $lat")
                            Text(text = "Last updated: $last_updated")
                            Text(text = "Temperature: $temp_c °C ($temp_f °F)")
                            Text(text = "Feels like: $temp_c °C ($temp_f °F)")
                            Text(text = "Weather: $weathertext")
                            Text(text = "Current: " + if (isday == "1") "Morning" else "Night")
                            Text(text = "Wind speed: $wind_kph kph ($wind_mph mph)")
                            Text(text = "Wind direction: $wind_dir °")
                            Text(text = "Precipitation: $rain_mm mm ($rain_in in)")
                            Text(text = "Humdity: $humidity %")
                            Text(text = "Visibily: $vis_km km ($vis_mi miles)")
                            Text(text = "UV: $uv")
                            Text(text = "Gust: $gust_kph kph ($gust_mph mph)")
                        }
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
        getCurrentWeather(getLocation())
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

    private fun decodeData() {
        if (currentWeatherResult != "") {
            try {
                val result = currentWeatherResult
                val parsable = JSONObject(result)
                val loc_info = parsable.getJSONObject("location")
                name = loc_info.getString("name")
                region = loc_info.getString("region")
                country = loc_info.getString("country")
                lat = loc_info.getString("lat")
                lon = loc_info.getString("lon")
                val current = parsable.getJSONObject("current")
                last_updated = current.getString("last_updated")
                temp_c = current.getString("temp_c")
                temp_f = current.getString("temp_f")
                isday = current.getString("is_day")
                val condition = current.getJSONObject("condition")
                weathertext = condition.getString("text")
                weather_icon = condition.getString("icon")
                weather_icon = weather_icon.removeRange(0, 2)
                weather_icon = "https://"+weather_icon
                wind_mph = current.getString("wind_mph")
                wind_kph = current.getString("wind_kph")
                wind_dir = current.getString("wind_degree")
                rain_mm = current.getString("precip_mm")
                rain_in = current.getString("precip_in")
                humidity = current.getString("humidity")
                feelslike_c = current.getString("feelslike_c")
                feelslike_f = current.getString("feelslike_f")
                vis_km = current.getString("vis_km")
                vis_mi = current.getString("vis_miles")
                uv = current.getString("uv")
                gust_mph = current.getString("gust_mph")
                gust_kph = current.getString("gust_kph")
            } catch (e: Throwable) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                println("Errors: $e")

                currentWeatherResult = "Error"
                name = "Error"
                region = "Error"
                country = "Error"
                lat = "Error"
                lon = "Error"
                last_updated = "Error"
                temp_c = "Error"
                temp_f = "Error"
                isday = "Error"
                weathertext = "Error"
                wind_mph = "Error"
                wind_kph = "Error"
                wind_dir = "Error"
                rain_mm = "Error"
                rain_in = "Error"
                humidity = "Error"
                feelslike_c = "Error"
                feelslike_f = "Error"
                vis_km = "Error"
                vis_mi = "Error"
                uv = "Error"
                gust_mph = "Error"
                gust_kph = "Error"
                weather_icon = "Error"
            }
        }
    }
}