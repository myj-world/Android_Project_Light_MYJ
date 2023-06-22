package com.yousufjamil.myjconnect_to_web

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoInternetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        supportActionBar?.hide()

        findViewById<Button>(R.id.internetProblemRetryBtn).setOnClickListener {
            val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

            if (isConnected) {
                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("No Internet Connection")
                    .setMessage("No connection found. Please check your internet connection.")
                    .setPositiveButton("Ok") {dialog, which ->
                        dialog.cancel()
                    }.show()
            }
        }
    }
}