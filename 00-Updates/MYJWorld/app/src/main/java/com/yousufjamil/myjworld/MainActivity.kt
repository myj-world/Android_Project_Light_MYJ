package com.yousufjamil.myjworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.graphics.Bitmap
import android.net.http.SslError
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.yousufjamil.myjworld.ui.theme.MYJWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJWorldTheme {
                var backEnabled by remember { mutableStateOf(false) }
                var webView by remember {
                    mutableStateOf(WebView(this))
                }
                var webError by remember {
                    mutableStateOf(false)
                }
                var errorDetail by remember {
                    mutableStateOf("")
                }
                AndroidView(
                    factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = object : WebViewClient() {
                                override fun onPageStarted(
                                    view: WebView,
                                    url: String?,
                                    favicon: Bitmap?
                                ) {
                                    backEnabled = view.canGoBack()
                                }

                                override fun onReceivedSslError(
                                    view: WebView?,
                                    handler: SslErrorHandler?,
                                    error: SslError?
                                ) {
                                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                                    webError = true
                                    errorDetail = error.toString()
                                }
                            }
                            loadUrl("https://myj.rf.gd")
                            settings.javaScriptEnabled = true
                            webView = this
                        }
                    }, update = {
                        it.loadUrl("https://myj.rf.gd")
                    }
                )
                BackHandler(enabled = backEnabled) {
                    webView.goBack()
                }
                if (webError) {
                    Column {
                        Text(text = "Error loading webpage")
                        Button(onClick = {
                            Toast.makeText(this@MainActivity, errorDetail, Toast.LENGTH_SHORT).show()
                        }) {
                            Text(text = "View Details")
                        }
                    }
                }
            }
        }
    }
}