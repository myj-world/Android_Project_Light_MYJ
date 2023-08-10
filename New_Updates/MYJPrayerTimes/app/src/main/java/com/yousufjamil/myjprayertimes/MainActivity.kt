package com.yousufjamil.myjprayertimes

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yousufjamil.myjprayertimes.ui.theme.MYJPrayerTimesTheme
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    var finalresult: String? = "Loading..."
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJPrayerTimesTheme {

            }
        }
        val backgroundWorker = BackgroundWorker(this, "Jeddah")
        backgroundWorker.execute()
        Handler().postDelayed(
            {
                val result = backgroundWorker.finalresult
                val jsonPars = JSONObject(result!!)
                val city = jsonPars.getString("city")
                val date = jsonPars.getString("date")
//                val today = jsonPars.getJSONArray("today")
//                val todaytimes = JSONObject(today.toString())
//                val fajr = today.getString(2)
                println("Result: $city")
            }, 10000
        )
    }
}