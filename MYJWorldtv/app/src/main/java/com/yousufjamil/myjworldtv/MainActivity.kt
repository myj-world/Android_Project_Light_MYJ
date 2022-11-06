package com.yousufjamil.myjworldtv

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_browse_fragment, MainFragment())
//                .commitNow()
//        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        Handler().postDelayed({
//            val homeIntent = Intent(this, WebViewActivity::class.java)
//            startActivity(homeIntent)
//            finish()
//        }, 1000)

        Handler().postDelayed(
            {
                val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
                val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
                if (isConnected) {
                    val finishLoadingIntent = Intent(this, WebViewActivity::class.java)
                    startActivity(finishLoadingIntent)
                    finish()
                } else {
                    val internetIssueIntent = Intent(this, InternetProblemActivity::class.java)
                    startActivity(internetIssueIntent)
                    finish()
                }
            }, 3000
        )

    }
}