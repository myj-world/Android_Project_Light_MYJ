package com.yousufjamil.myjworld

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@Suppress("DEPRECATION")
class InternetProblemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_problem)

        val retryBtn = findViewById<Button>(R.id.retryBtn)
        val noInternetImg = findViewById<ImageView>(R.id.noInternetImg)

        retryBtn.setOnClickListener {
            val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            if (isConnected) {
                val finishLoadingIntent = Intent(this, HomeActivity::class.java)
                startActivity(finishLoadingIntent)
                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("No connection found")
                    .setMessage("Your device does not seem to be connected to internet. Please check your network connection")
                    .setPositiveButton("OK") { dialog, which ->
                        // Do nothing
                    }
                    .show()
            }
        }

        val currentNightMode = (resources.configuration.uiMode
                and Configuration.UI_MODE_NIGHT_MASK)
        val resourceIdDark = this.resources.getIdentifier(R.drawable.ic_baseline_wifi_off_24.toString(), "drawable", this.packageName)
        val resourceIdLight = this.resources.getIdentifier(R.drawable.ic_no_wifi_light.toString(), "drawable", this.packageName)


        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            noInternetImg.setImageResource(resourceIdDark)
            val primaryColorDrawable = ColorDrawable(Color.parseColor("#F47900"))
            supportActionBar?.setBackgroundDrawable(primaryColorDrawable)
        } else {
            noInternetImg.setImageResource(resourceIdLight)
        }

//        when (currentNightMode) {
//            Configuration.UI_MODE_NIGHT_NO -> noInternetImg.setImageResource(resourceIdLight)
//            Configuration.UI_MODE_NIGHT_YES -> noInternetImg.setImageResource(resourceIdDark)
//            Configuration.UI_MODE_NIGHT_UNDEFINED -> noInternetImg.setImageResource(resourceIdLight)
//        }
    }
}