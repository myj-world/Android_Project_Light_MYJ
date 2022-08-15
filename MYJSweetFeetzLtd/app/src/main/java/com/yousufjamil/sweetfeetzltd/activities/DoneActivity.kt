package com.yousufjamil.sweetfeetzltd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yousufjamil.sweetfeetzltd.EXTRA_PLAYER
import com.yousufjamil.sweetfeetzltd.Player
import com.yousufjamil.sweetfeetzltd.R

class DoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done)

        val finalSockImageGet = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

    }
}