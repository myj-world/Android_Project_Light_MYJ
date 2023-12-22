package com.yousufjamil.quranWithTranslation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.InputStream
import java.io.OutputStream


class PdfOpenerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_opener)

        val webView = findViewById<WebView>(R.id.pdfViewer)

        with(webView) {
            settings.javaScriptEnabled = true

            settings.allowFileAccessFromFileURLs = true
            settings.allowUniversalAccessFromFileURLs = true
            settings.builtInZoomControls = true
        }
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl("https://drive.google.com/file/d/1rzJByfgDZe5aFI0r3zP18qKyrfjEgr3Z/view?usp=sharing")
    }
}