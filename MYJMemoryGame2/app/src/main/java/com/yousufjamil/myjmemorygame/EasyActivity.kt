package com.yousufjamil.myjmemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Random

class EasyActivity : AppCompatActivity() {

    val images = mutableListOf("down", "up", "right", "left", "house", "heart", "thunder", "smiley")
    val random = Random()
    var chosen1 = 0
    var chosen2 = 0
    val chosen = mutableListOf("temporary")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy)

        chooseimages()
    }

    fun chooseimages() {
        chosen1 = random.nextInt(images.count())
        chosen.add(images[chosen1])
        chosen.removeAt(0)
        images.removeAt(chosen1)
        chosen2 = random.nextInt(images.count())
        chosen.add(images[chosen2])
        println("CurrentList: $chosen")
    }
}