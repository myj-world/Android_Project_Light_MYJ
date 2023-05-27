package com.yousufjamil.jumaasurahs

import android.R.attr.path
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class JumaActivity : AppCompatActivity() {
    var audioplaying = false
    lateinit var mediaPlayer: MediaPlayer

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_AUDIO_JUMAH, audioplaying)
        outState.putInt(AUDIO_POINT_JUMAH, mediaPlayer.currentPosition)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juma)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mediaPlayer = MediaPlayer.create(this, R.raw.jumah)

        if (savedInstanceState != null) {
            audioplaying = savedInstanceState.getBoolean(EXTRA_AUDIO_JUMAH)
            mediaPlayer.seekTo(savedInstanceState.getInt(AUDIO_POINT_JUMAH))
            if (audioplaying) {
                mediaPlayer.start()
            }
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.playSurahBtn) {
            if (!audioplaying) {
                mediaPlayer.start()

                item.setIcon(R.drawable.baseline_pause_circle_24)
                audioplaying = true
            } else {
                mediaPlayer.pause()
                item.setIcon(R.drawable.baseline_play_circle_24)
                audioplaying = false
            }
        } else if (item.itemId == R.id.restartSurahBtn) {
            mediaPlayer.seekTo(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        mediaPlayer.pause()
        super.onPause()
    }
}