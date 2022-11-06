package com.yousufjamil.myjworldtv

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView: WebView = findViewById(R.id.webWeb)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://myj.rf.gd")

        val loadWebProgress: ProgressBar = findViewById(R.id.loadWebProgress)
        loadWebProgress.visibility = View.INVISIBLE
        webView.canGoBack()

        val webpageTitleTxt: TextView = findViewById(R.id.webpageTitleTxt)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadWebProgress.visibility = View.VISIBLE
                webView.setOnTouchListener(View.OnTouchListener { v, event -> true })
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadWebProgress.visibility = View.INVISIBLE
                webpageTitleTxt.setText(view?.title)
                webView.setOnTouchListener(View.OnTouchListener { v, event -> false })
            }
        }
    }
    fun refreshWeb(view:View) {
        val webView: WebView = findViewById(R.id.webWeb)
        val url = webView.url
        if (url != null) {
            webView.loadUrl(url)
        } else {
            Toast.makeText(this,"Unknown Error", Toast.LENGTH_SHORT).show()
        }
    }
    fun homeWeb(view: View) {
        val webView: WebView = findViewById(R.id.webWeb)
        webView.loadUrl("https://myj.rf.gd")
    }
    fun infoWeb(view: View) {
        val webView: WebView = findViewById(R.id.webWeb)
        val url = webView.url
        MaterialAlertDialogBuilder(this)
            .setTitle("Info")
            .setMessage("Website url: $url")
            .setNegativeButton("Copy") {dialog, which ->
                var clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("url", url.toString())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Copied url to clipboard", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Close") {dialog, which ->
                // Do nothing
            }
            .show()
    }
}