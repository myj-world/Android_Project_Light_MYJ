package com.yousufjamil.jumaasurahs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class JumaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juma)
        val nextBtn: ImageView = findViewById(R.id.jumaNextBtn)
        val previousBtn: ImageView = findViewById(R.id.jumaBackBtn)
        val surahJuma: ImageView = findViewById(R.id.jumaSurahImg)

        previousBtn.visibility = View.INVISIBLE

        nextBtn.setOnClickListener {
            when (surahJuma.drawable.constantState) {
                resources.getDrawable(R.drawable.light_jumah1).constantState -> {
                    surahJuma.setImageResource(R.drawable.light_jumah2)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.INVISIBLE
                }
                else -> {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        previousBtn.setOnClickListener {
            when (surahJuma.drawable.constantState) {
                resources.getDrawable(R.drawable.light_jumah2).constantState -> {
                    surahJuma.setImageResource(R.drawable.light_jumah1)
                    previousBtn.visibility = View.INVISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                else -> {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}