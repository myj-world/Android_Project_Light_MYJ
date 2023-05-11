package com.yousufjamil.myjmemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import java.util.Random

class EasyActivity : AppCompatActivity() {

    val images = mutableListOf("down", "up", "right", "left", "house", "heart", "thunder", "smiley")
    val random = Random()
    var chosen1 = 0
    var chosen2 = 0
    val chosen = mutableListOf("temporary")
    val card1easy: ImageButton = findViewById(R.id.card1easy)
    val card2easy: ImageButton = findViewById(R.id.card2easy)
    val card3easy: ImageButton = findViewById(R.id.card3easy)
    val card4easy: ImageButton = findViewById(R.id.card4easy)

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
        assignimages(chosen)
    }

    fun assignimages(chosenlist: MutableList<String>) {
        var countnumber1 = 0
        var countnumber2 = 0
        var card1asset = 0
        var card2asset = 0
        var card3asset = 0
        var card4asset = 0
        while (countnumber1+countnumber2 != 4) {
            TODO("Will be done later")
        }
    }
}