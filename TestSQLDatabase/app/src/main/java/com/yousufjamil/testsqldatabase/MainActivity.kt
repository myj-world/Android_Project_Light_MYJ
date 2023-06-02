package com.yousufjamil.testsqldatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idToFind = findViewById<EditText>(R.id.idEt)
        val findBtn = findViewById<Button>(R.id.findBtn)

        findBtn.setOnClickListener {
            TODO("Not implemented")
        }
    }
}