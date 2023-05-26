package com.yousufjamil.jumaasurahs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast

@Suppress("DEPRECATION")
class KahfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kahf)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nextBtn: ImageView = findViewById(R.id.kahfNextBtn)
        val previousBtn: ImageView = findViewById(R.id.kahfBackBtn)
        val surahKahf: ImageView = findViewById(R.id.kahfSurahImg)

        previousBtn.visibility = View.INVISIBLE

        nextBtn.setOnClickListener {
            when (surahKahf.drawable.constantState) {
                resources.getDrawable(R.drawable.light_kahf1).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf2)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf2).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf3)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf3).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf4)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf4).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf5)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf5).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf6)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf6).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf7)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf7).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf8)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf8).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf9)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf9).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf10)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf10).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf11)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.INVISIBLE
                }
                else -> {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        previousBtn.setOnClickListener {
            when (surahKahf.drawable.constantState) {
                resources.getDrawable(R.drawable.light_kahf2).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf1)
                    previousBtn.visibility = View.INVISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf3).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf2)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf4).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf3)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf5).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf4)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf6).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf5)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf7).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf6)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf8).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf7)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf9).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf8)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf10).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf9)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
                }
                resources.getDrawable(R.drawable.light_kahf11).constantState -> {
                    surahKahf.setImageResource(R.drawable.light_kahf10)
                    previousBtn.visibility = View.VISIBLE
                    nextBtn.visibility = View.VISIBLE
            }
                else -> {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.playSurahBtn) {

        } else if (item.itemId == R.id.restartSurahBtn) {

        }
        return super.onOptionsItemSelected(item)
    }
}