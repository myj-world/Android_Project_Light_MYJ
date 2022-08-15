package com.yousufjamil.sweetfeetzltd.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.yousufjamil.sweetfeetzltd.EXTRA_PLAYER
import com.yousufjamil.sweetfeetzltd.Player
import com.yousufjamil.sweetfeetzltd.R

@Suppress("DEPRECATION")
class GeneraterActivity : AppCompatActivity() {
    lateinit var sockStyled: Player

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_PLAYER, sockStyled)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generater)
        sockStyled = intent.getParcelableExtra(EXTRA_PLAYER)!!

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val DoneActivityIntent = Intent(this, DoneActivity::class.java)
            DoneActivityIntent.putExtra(EXTRA_PLAYER, sockStyled)
            startActivity(DoneActivityIntent)
            finish()
        }, 10000)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            sockStyled = savedInstanceState.getParcelable(EXTRA_PLAYER)!!
        }
    }
}