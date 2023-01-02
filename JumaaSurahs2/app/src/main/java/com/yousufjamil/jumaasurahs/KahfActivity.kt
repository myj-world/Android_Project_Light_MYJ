package com.yousufjamil.jumaasurahs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

@Suppress("DEPRECATION")
class KahfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kahf)

        val nextBtn: ImageView = findViewById(R.id.kahfNextBtn)
        val previousBtn: ImageView = findViewById(R.id.kahfBackBtn)
        val surahKahf: ImageView = findViewById(R.id.kahfSurahImg)

        nextBtn.setOnClickListener {
            when (surahKahf.drawable) {
                resources.getDrawable(R.drawable.light_kahf1) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf2)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf2) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf3)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf3) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf4)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf4) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf5)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf5) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf6)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf6) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf7)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf7) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf8)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf8) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf9)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf9) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf10)
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf10) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf11)
                    nextBtn.visibility = View.INVISIBLE
                }
                else -> {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        previousBtn.setOnClickListener {
            when (surahKahf.drawable) {
                resources.getDrawable(R.drawable.light_kahf2) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf1)
                    previousBtn.visibility = View.INVISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf3) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf2)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf4) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf3)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf5) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf4)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf6) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf5)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf7) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf6)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf8) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf7)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf9) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf8)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf10) -> {
                    surahKahf.setImageResource(R.drawable.light_kahf9)
                    previousBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf11) -> {
                surahKahf.setImageResource(R.drawable.light_kahf10)
                previousBtn.visibility = View.VISIBLE
            }
                else -> {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}