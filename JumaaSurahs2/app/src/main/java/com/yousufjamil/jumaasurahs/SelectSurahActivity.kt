package com.yousufjamil.jumaasurahs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectSurahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_surah)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val jumaBtn: Button = findViewById(R.id.jumaBtn)
        jumaBtn.setOnClickListener {
            val selectIntent = Intent(this, JumaActivity::class.java)
            startActivity(selectIntent)
        }

        val kahfBtn: Button = findViewById(R.id.kahfBtn)
        kahfBtn.setOnClickListener {
            val selectIntent = Intent(this, KahfActivity::class.java)
            startActivity(selectIntent)
        }

        val dukhanBtn: Button = findViewById(R.id.dukhanBtn)
        dukhanBtn.setOnClickListener {
            val selectIntent = Intent(this, DukhanActivity::class.java)
            startActivity(selectIntent)
        }
    }
}