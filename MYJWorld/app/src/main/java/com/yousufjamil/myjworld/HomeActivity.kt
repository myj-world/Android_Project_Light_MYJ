package com.yousufjamil.myjworld

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val currentNightMode = (resources.configuration.uiMode
                and Configuration.UI_MODE_NIGHT_MASK)

        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            val primaryColorDrawable = ColorDrawable(Color.parseColor("#F47900"))
            supportActionBar?.setBackgroundDrawable(primaryColorDrawable)
        }

        val webView: WebView = findViewById(R.id.webWeb)
        val homeBtn: ImageButton = findViewById(R.id.backHomeBtn)
        val refreshBtn: ImageButton = findViewById(R.id.refreshPageBtn)
        val url = webView.url
        val settings = webView.settings

        settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://myj.rf.gd")

        refreshBtn.setOnClickListener {
            if (url != null) {
                webView.loadUrl(url)
            }
        }

        homeBtn.setOnClickListener {
            webView.loadUrl("https://myj.rf.gd")
        }
    }
}