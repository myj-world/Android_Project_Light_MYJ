package com.yousufjamil.qiblafinder

import android.Manifest
import android.R.attr.country
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale


class MainActivity : ComponentActivity() {

    private lateinit var locationRequest: LocationRequest
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var longitude = 2468.0
    private var latitude = 2468.0
    private var bearTo = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        checkLocationPermission()

        setContent {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                    if (longitude != 2468.0 && latitude != 2468.0) {
                        val userLoc = Location("service Provider")
                        userLoc.longitude = longitude
                        userLoc.latitude = latitude

                        val destinationLoc = Location("service Provider")
                        destinationLoc.latitude = 21.422487
                        destinationLoc.longitude = 39.826206

                        bearTo = userLoc.bearingTo(destinationLoc)

                        if (bearTo < 0) {
                            bearTo += 360
                        }

                        Image(
                            painter = painterResource(id = R.drawable.kaaba_direction_nobg),
                            contentDescription = "Kaaba Direction",
                            modifier = Modifier
                                .rotate(bearTo)
                                .size(250.dp)
                        )
                        Text(text = "Ensure you are pointing your device northwards to get exact Qiblah.", textAlign = TextAlign.Center)

//                    } else {
//                        Text(text = "Couldn't retrieve location.")
//                    }
            }
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            checkGPS()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
    }

    private fun checkGPS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude

                    } else {
                        latitude = 2468.0
                        longitude = 2468.0
                    }
                }
                .addOnFailureListener {
                    println("Errorss $it")
                }
        }

//        locationRequest = LocationRequest.create()
//        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest.interval = 5000
//        locationRequest.fastestInterval = 2000
//
//        val builder = LocationSettingsRequest.Builder()
//            .addLocationRequest(locationRequest)
//
//        builder.setAlwaysShow(true)
//
//        val result = LocationServices.getSettingsClient(this.applicationContext)
//            .checkLocationSettings(builder.build())
//
//        result.addOnCompleteListener {task ->
//            try {
//                val response = task.getResult(ApiException::class.java)
//
//                getUserLocation()
//            } catch (e: ApiException) {
//                when (e.statusCode) {
//                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
//                        val resolveApiException = e as ResolvableApiException
//                        resolveApiException.startResolutionForResult(this, 200)
//                    } catch (sendIntentException: IntentSender.SendIntentException) {
//                        Toast.makeText(this, "Couldn't retrieve location", Toast.LENGTH_SHORT).show()
//                    }
//                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
//                        Toast.makeText(this, "Couldn't retrieve location", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                Toast.makeText(this, "Couldn't retrieve location", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun getUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task ->
            val location = task.result
            if (location != null) {
                longitude = location.longitude
                latitude = location.latitude
            } else {
                longitude = 2468.0
                latitude = 2468.0
            }
            println("Loggies: $longitude, $latitude")
        }
    }
}

