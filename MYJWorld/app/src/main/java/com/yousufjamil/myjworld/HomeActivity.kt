package com.yousufjamil.myjworld

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
    }
}