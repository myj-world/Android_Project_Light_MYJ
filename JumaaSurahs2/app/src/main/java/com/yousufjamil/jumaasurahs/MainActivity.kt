package com.yousufjamil.jumaasurahs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val letsGoBtn: Button = findViewById(R.id.homepageBtn)
        letsGoBtn.setOnClickListener {
            val selectIntent = Intent(this, SelectSurahActivity::class.java)
            startActivity(selectIntent)
        }
    }
}