package com.yousufjamil.myjdemos.legal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yousufjamil.myjdemos.R
import com.yousufjamil.myjdemos.controller.AppNamesActivity

class AgreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agree)
        supportActionBar?.show()

        val agreeBtn = findViewById<Button>(R.id.agreeBtn)
        agreeBtn.setOnClickListener{
            val appNamActivityIntent = Intent(this, AppNamesActivity::class.java)
            startActivity(appNamActivityIntent)
            finish()
        }
    }
}