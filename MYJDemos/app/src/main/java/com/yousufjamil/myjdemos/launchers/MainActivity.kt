package com.yousufjamil.myjdemos.launchers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.yousufjamil.myjdemos.legal.AgreeActivity
import com.yousufjamil.myjdemos.R

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed(
            {
                val legalIntent = Intent(this, AgreeActivity::class.java)
                startActivity(legalIntent)
                finish()
            }, 3000
        )
    }
}