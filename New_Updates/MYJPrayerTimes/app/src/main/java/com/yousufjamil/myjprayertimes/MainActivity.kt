package com.yousufjamil.myjprayertimes

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.postDelayed
import com.yousufjamil.myjprayertimes.ui.theme.MYJPrayerTimesTheme
import org.json.JSONException
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    var city = ""
    var date = ""
    var fajr = ""
    var ishraq = ""
    var dhuhr = ""
    var asr = ""
    var maghrib = ""
    var isha = ""
    var statusMessage = ""
    lateinit var backgroundWorker: BackgroundWorker

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val barlow = FontFamily(
            Font(R.font.barlow_med, FontWeight.Medium)
        )

        setContent {
            var inputCity by remember {
                mutableStateOf("")
            }
            var recompose by remember {
                mutableStateOf(0)
            }

            if (fajr == "" && statusMessage == "") {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(recompose.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = inputCity,
                        label = {
                            Text(text = "City")
                        },
                        onValueChange = {
                            inputCity = it
                        },
                        modifier = Modifier.fillMaxWidth(0.85f),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            statusMessage = ""

                            backgroundWorker = BackgroundWorker(this@MainActivity, inputCity)
                            backgroundWorker.execute()

                            checkStatus()

                            city = "Loading..."
                            date = "Loading..."
                            fajr = "Loading..."
                            ishraq = "Loading..."
                            dhuhr = "Loading..."
                            asr = "Loading..."
                            maghrib = "Loading..."
                            isha = "Loading..."

                            if (recompose <= 10) {
                                recompose++
                            } else {
                                recompose--
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.85f)
                    ) {
                        Text(text = "Get Prayer Times")
                    }
                }
            } else {
                Box (modifier = Modifier.fillMaxSize()) {
                    Image(painter = painterResource(id = R.drawable.bg), contentDescription = "Background", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(recompose.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (statusMessage == "") {

                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                contentScale = ContentScale.FillBounds
                            )

                            Text(
                                text = "City:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = city,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Date:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = date,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Fajr:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = fajr,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Sharooq:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = ishraq,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Dhuhr:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = dhuhr,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Asr:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = asr,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Maghrib:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = maghrib,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Isha:",
                                modifier = Modifier.fillMaxWidth(0.4f),
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = isha,
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.End,
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                        } else {
                            Text(
                                text = "Error: $statusMessage",
                                fontFamily = barlow,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                Handler().postDelayed(
                    {
                        if (recompose <= 10) {
                            recompose++
                        } else {
                            recompose--
                        }
                    }, 1000
                )
            }
        }
    }

    private fun checkStatus() {
        Handler().postDelayed({
            if (backgroundWorker.status.toString() == "FINISHED") {
                try {
                    val result = backgroundWorker.finalresult
                    val jsonPars = JSONObject(result!!)
                    city = jsonPars.getString("city")
                    date = jsonPars.getString("date")

                    val today = jsonPars.getJSONObject("today")
                    fajr = today.getString("Fajr")
                    ishraq = today.getString("Sunrise")
                    dhuhr = today.getString("Dhuhr")
                    asr = today.getString("Asr")
                    maghrib = today.getString("Maghrib")
                    isha = today.getString("Isha'a")

                    println("Result: $city, $date, $fajr, $ishraq, $dhuhr, $asr, $maghrib, $isha")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                try {
                    val result = backgroundWorker.finalresult
                    val jsonPars = JSONObject(result!!)
                    statusMessage = jsonPars.getString("Error")
                } catch (_: JSONException) {
                }
            } else {
                checkStatus()
            }
        }, 1000)
    }
}