package com.yousufjamil.myjworld

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        val loadWebProgress = findViewById<ProgressBar>(R.id.loadWebProgress)
        val webView: WebView = findViewById(R.id.webWeb)
        val homeBtn: ImageButton = findViewById(R.id.backHomeBtn)
        val refreshBtn: ImageButton = findViewById(R.id.refreshPageBtn)
        val infoPageBtn: ImageButton = findViewById(R.id.infoPageBtn)
        var webpageTitleTxt: TextView = findViewById(R.id.webpageTitleTxt)
        val url = webView.url
        val settings = webView.settings

        loadWebProgress.visibility = View.INVISIBLE
        settings.setDomStorageEnabled(true)
        settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.myj.rf.gd")
        webView.canGoBack()

        refreshBtn.setOnClickListener {
            if (url != null) {
                webView.loadUrl(url)
            }
        }

        homeBtn.setOnClickListener {
            webView.loadUrl("https://myj.rf.gd")
        }

        infoPageBtn.setOnClickListener {
            if (url != null) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Info")
                    .setMessage("Currently loaded url: $url")
                    .setPositiveButton("Okay") {dialog, which ->
                        // Do nothing
                    }
                    .show()
            }
        }

//        override fun onBackPressed() {
//            if (webView.canGoBack()) {
//                webView.goBack()
//            }
//            super.onBackPressed()
//        }

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
//                super.onReceivedSslError(view, handler, error)
//                var agree = false
//                MaterialAlertDialogBuilder(this@HomeActivity)
//                    .setTitle("SSL Error")
//                    .setMessage("We faced SSL Errors while loading the website. Do you want to continue loading anyway?")
//                    .setPositiveButton("Continue Anyway") {dialog, which ->
//                        agree = true
//                        webView.loadUrl("file:///assets/index.html")
//                    }
//                    .setNegativeButton("Cancel") {dialog, which ->
//                        agree = false
//                    }
//                    .show()
//                if (agree) {
//                    handler?.proceed()
//                } else {
//                    handler?.cancel()
//                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadWebProgress.visibility = View.VISIBLE
                webView.setOnTouchListener(OnTouchListener { v, event -> true })
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadWebProgress.visibility = View.INVISIBLE
                webpageTitleTxt.setText(view?.title)
                webView.setOnTouchListener(OnTouchListener { v, event -> false })
            }
        }
    }
}