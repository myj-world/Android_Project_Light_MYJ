package com.yousufjamil.ginastic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView: WebView = findViewById(R.id.webWeb)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://ginastic.co")



    }

    fun refreshWeb(view:View) {
        val webView: WebView = findViewById(R.id.webWeb)
        val url = webView.url
        if (url != null) {
            webView.loadUrl(url)
        } else {
            Toast.makeText(this,"Unknown Error",Toast.LENGTH_SHORT).show()
        }
    }



    fun homeWeb(view: View) {
        val webView: WebView = findViewById(R.id.webWeb)
        webView.loadUrl("https://ginastic.co")
    }

//    fun webCheckWeb(view: View) {
//        val webView: WebView = findViewById(R.id.webWeb)
//        val btnBack: Button = findViewById(R.id.homeWeb)
//        val url = webView.url
//        if (url != null) {
//            if (url == "https://ginastic.co") {
//                btnBack.visibility = View.GONE
//            } else {
//                btnBack.visibility = View.VISIBLE
//            }
//        } else {
//            Toast.makeText(this,"Unknown Error",Toast.LENGTH_SHORT).show()
//        }
//    }

}