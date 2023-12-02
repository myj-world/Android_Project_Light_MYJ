package com.yousufjamil.inteclick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.yousufjamil.inteclick.ui.theme.InteClickTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InteClickTheme {
                var backEnabled by remember { mutableStateOf(false) }
                var webView by remember {
                    mutableStateOf(WebView(this))
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
                            }
                            loadUrl("https://inteclick.net")
                            settings.javaScriptEnabled = true
                            webView = this
                        }
                    }, update = {
                        it.loadUrl("https://inteclick.net")
                    }
                )
                BackHandler(enabled = backEnabled) {
                    webView.goBack()
                }
            }
        }
    }
}