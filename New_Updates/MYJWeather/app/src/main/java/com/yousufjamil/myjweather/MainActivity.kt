package com.yousufjamil.myjweather

import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidthFloat = ((resources.displayMetrics.widthPixels) * 0.3) / (displayMetrics.density)

        setContent {
            MYJWeatherTheme {
                var recompose by remember {
                    mutableStateOf(0)
                }

                var background by remember {
                    mutableStateOf(Color(255, 255, 255))
                }

                var textColor by remember {
                    mutableStateOf(Color(0, 0, 0))
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
                                .fillMaxSize(),
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
                                .background(background)
                                .padding(15.dp)
                        ) {
                            decodeData()

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        Toast
                                            .makeText(
                                                this@MainActivity,
                                                "Just a moment...",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                        getSharedPreferences(
                                            "myj_weather_info",
                                            MODE_PRIVATE
                                        )
                                            .edit()
                                            .putString("loc_info", "")
                                            .apply()
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
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Change location",
                                    tint = textColor
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(text = "$name, $region, $country", color = textColor)
                                    Text(text = "$lon, $lat", color = textColor)
                                }
                            }

                            Row {
                                Image(
                                    painter = rememberAsyncImagePainter(model = weather_icon),
                                    contentDescription = "Weather Icon",
                                    modifier = Modifier
                                        .size(140.dp)
                                )
                                Column {
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Row {
                                        Text(
                                            text = "$temp_c °C",
                                            color = textColor,
                                            fontSize = 28.sp
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Column {
                                            Spacer(modifier = Modifier.height(14.dp))
                                            Text(text = "$temp_f °F", color = textColor)
                                        }
                                    }
                                    Text(
                                        text = "Feels like: $temp_c °C ($temp_f °F)",
                                        color = textColor
                                    )
                                    Text(text = "Refreshed: $last_updated", color = textColor)
                                }
                            }

                            Text(text = weathertext, color = textColor, fontSize = 40.sp)

                            @Composable
                            fun Condition(
                                resource: Int,
                                text: String,
                                description: String
                            ) {
                                Row {Spacer(modifier = Modifier.width(2.dp))
                                    Column(
                                        modifier = Modifier
//                                        .fillMaxWidth(screenWidthFloat)
                                            .width(screenWidthFloat.dp)
                                            .height(140.dp)
                                            .clip(RoundedCornerShape(corner = CornerSize(25.dp)))
                                            .background(Color(255, 255, 255, 20))
                                            .padding(5.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Icon(
                                            painter = painterResource(id = resource),
                                            contentDescription = text,
                                            tint = textColor,
                                            modifier = Modifier.size(40.dp)
                                        )
                                        Text(
                                            text = text,
                                            color = textColor,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = description,
                                            color = textColor,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))}
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Condition(
                                    resource = R.drawable.baseline_speed_24,
                                    text = "Wind Speed",
                                    description = "$wind_kph kph ($wind_mph mph)"
                                )

                                Condition(
                                    resource = R.drawable.baseline_compass_calibration_24,
                                    text = "Wind Direction",
                                    description = "$wind_dir °"
                                )

                                Condition(
                                    resource = R.drawable.baseline_water_24,
                                    text = "Rain",
                                    description = "$rain_mm mm ($rain_in in)"
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Condition(
                                    resource = R.drawable.baseline_water_drop_24,
                                    text = "Humidity",
                                    description = "$humidity %"
                                )

                                Condition(
                                    resource = R.drawable.baseline_visibility_24,
                                    text = "Visibility",
                                    description = "$vis_km km ($vis_mi miles)"
                                )

                                Condition(
                                    resource = R.drawable.baseline_wb_sunny_24,
                                    text = "UV",
                                    description = uv
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Condition(
                                    resource = R.drawable.baseline_wind_power_24,
                                    text = "Gust",
                                    description = "$gust_kph kph ($gust_mph mph)"
                                )
                            }

                            when (isday) {
                                "1" -> {
                                    background = Color(135, 206, 235)
                                    textColor = Color(0, 0, 0)
                                }

                                "0" -> {
                                    background = Color(43, 44, 90)
                                    textColor = Color(255, 255, 255)
                                }
                            }
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
                weather_icon = "https://" + weather_icon
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