package com.yousufjamil.myjconnect_to_web

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.website)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_myj -> webView.loadUrl("https://myj.rf.gd")
                R.id.google -> webView.loadUrl("https://google.com")
                else -> Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawers()
            true
        }

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.canGoBack()
        webView.loadUrl("https://google.com")
        var requestedURL = webView.url

        val loading = ProgressDialog(this)
        loading.setMessage("Loading...")
        loading.setCancelable(false)

        findViewById<ImageButton>(R.id.searchBtn).setOnClickListener {
            val url = findViewById<EditText>(R.id.urlTextEt).text
            if (url.contains(":") && url.contains(".")) {
                webView.loadUrl(url.toString())
            } else {
                Toast.makeText(this, "Invalid Url", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }
        findViewById<ImageButton>(R.id.refreshBtn).setOnClickListener {
            webView.reload()
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if (!checkInternet()) {
                    noInternet()
                } else {
                    loading.show()
                    requestedURL = url
                    super.onPageStarted(view, url, favicon)
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                loading.hide()
                findViewById<TextView>(R.id.titleTV).text = webView.title
                super.onPageFinished(view, url)
            }

//            override fun onReceivedError(
//                view: WebView?,
//                request: WebResourceRequest?,
//                error: WebResourceError?
//            ) {
//                Toast.makeText(this@MainActivity, "Error, loading in another browser...", Toast.LENGTH_SHORT).show()
//                GlobalScope.launch {
//                    val site = Intent(Intent.ACTION_VIEW)
//                    site.data = Uri.parse(requestedURL)
//                    startActivity(site)
//                }
//                super.onReceivedError(view, request, error)
//            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                Toast.makeText(this@MainActivity, "Error, loading in another browser...", Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    val site = Intent(Intent.ACTION_VIEW)
                    site.data = Uri.parse("https://myj.rf.gd")
                    startActivity(site)
                }
                super.onReceivedSslError(view, handler, error)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.website)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    fun checkInternet(): Boolean {
        val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    fun noInternet() {
        val internetIssueIntent = Intent(this, NoInternetActivity::class.java)
        startActivity(internetIssueIntent)
        finish()
    }
}