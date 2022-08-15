package com.yousufjamil.ginastic

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.getSystemService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
@Suppress("DEPRECATION")
class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }

    fun openWebView(view: View) {
        val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected == true) {
            val intentWeb = Intent(this, WebViewActivity::class.java)
            startActivity(intentWeb)
        } else {
            Toast.makeText(this, "Check Your Network Connection", Toast.LENGTH_SHORT).show()
//            MaterialAlertDialogBuilder(this)
//                    .setTitle("Check Your Network Connection")
//                    .setMessage("Your device does not seem to be connected to internet. Please check your network connection")
//                    .setPositiveButton("Yes") { dialog, which ->
//                        // Do nothing
//                    }
//                    .show()
        }

    }
}