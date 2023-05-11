package com.yousufjamil.myjmemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
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
        assignimages(chosen)
    }

    fun assignimages(chosenlist: MutableList<String>) {
        val card1easy: ImageButton = findViewById(R.id.card1easy)
        val card2easy: ImageButton = findViewById(R.id.card2easy)
        val card3easy: ImageButton = findViewById(R.id.card3easy)
        val card4easy: ImageButton = findViewById(R.id.card4easy)
        var countnumber1 = 0
        var countnumber2 = 0
        var card1asset = ""
        var card2asset = ""
        var card3asset = ""
        var card4asset = ""
        var cardrandom = 0
        cardrandom = random.nextInt(2)
        card1asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
         countnumber1+=1
        } else {
            countnumber2 +=1
        }
        cardrandom = random.nextInt(2)
        card2asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else {
            countnumber2 +=1
        }
        when(countnumber1) {
            0 -> {
                card3asset = chosenlist[0]
                card4asset = chosenlist[0]
            }
            1 -> {
                card3asset = chosenlist[0]
                card4asset = chosenlist[1]
            }
            2 -> {
                card3asset = chosenlist[1]
                card4asset = chosenlist[1]
            }
            else -> Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show()
        }

        card1easy.setOnClickListener {
            val resourceId = resources.getIdentifier(card1asset, "drawable", packageName)
            card1easy.setImageResource(resourceId)
        }

        card2easy.setOnClickListener {
            val resourceId = resources.getIdentifier(card2asset, "drawable", packageName)
            card2easy.setImageResource(resourceId)
        }

        card3easy.setOnClickListener {
            val resourceId = resources.getIdentifier(card3asset, "drawable", packageName)
            card3easy.setImageResource(resourceId)
        }

        card4easy.setOnClickListener {
            val resourceId = resources.getIdentifier(card4asset, "drawable", packageName)
            card4easy.setImageResource(resourceId)
        }
    }
}