package com.yousufjamil.myjconnect_to_web

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var etText = url_txt.toString()
        etText = "https://www.google.com"
        url_txt.setText(etText)

        webView.loadUrl("https://www.google.com")
        webView.settings.javaScriptEnabled = true
        webView.canGoBack()
        webView.webViewClient = webClient(this)
        search_button.setOnClickListener{
            val URL: String = url_txt.text.toString()
            webView.loadUrl(URL)
        }
        back_button.setOnClickListener{
            webView.goBack()
        }

    }
}

class webClient internal constructor(private val activity:Activity):WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}