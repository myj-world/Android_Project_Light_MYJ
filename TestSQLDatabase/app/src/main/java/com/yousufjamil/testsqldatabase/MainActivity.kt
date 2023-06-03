package com.yousufjamil.testsqldatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val findBtn = findViewById<Button>(R.id.findBtn)

        findBtn.setOnClickListener {
            val idToFind = findViewById<EditText>(R.id.idEt).text.toString()
            val backgroundWorker = BackgroundWorker()
            BackgroundWorker().backgroundWorker(this)
            backgroundWorker.execute("fetchData", idToFind)
        }
    }
}