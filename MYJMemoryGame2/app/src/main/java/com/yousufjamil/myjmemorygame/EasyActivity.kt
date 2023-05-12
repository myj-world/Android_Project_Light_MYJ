package com.yousufjamil.myjmemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import java.util.Random

@Suppress("DiscouragedAPI")
class EasyActivity : AppCompatActivity() {

    val images = mutableListOf("down", "up", "right", "left", "house", "heart", "thunder", "smiley")
    val random = Random()
    var chosen1 = 0
    var chosen2 = 0
    val chosen = mutableListOf("temporary")
    lateinit var card1asset: String
    lateinit var card2asset: String
    lateinit var card3asset: String
    lateinit var card4asset: String

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
        gamestart()
    }

    fun gamestart() {
        val card1easy: ImageButton = findViewById(R.id.card1easy)
        val card2easy: ImageButton = findViewById(R.id.card2easy)
        val card3easy: ImageButton = findViewById(R.id.card3easy)
        val card4easy: ImageButton = findViewById(R.id.card4easy)
        val opencards = mutableListOf<Int>()
        val solvedcards = mutableListOf<Int>()
        card1easy.setOnClickListener {
            if (opencards.count() >= 2) {
                card2easy.setImageResource(R.drawable.logo)
                card3easy.setImageResource(R.drawable.logo)
                card4easy.setImageResource(R.drawable.logo)
                opencards.remove(2)
                opencards.remove(3)
                opencards.remove(4)
            }
            val resourceIdcard1 = resources.getIdentifier(card1asset, "drawable", packageName)
            opencards.add(1)
            card1easy.setImageResource(resourceIdcard1)
            opencards.add(1)

            println("OpenCards: $opencards")

            when (card1easy.drawable) {
                card2easy.drawable -> {
                    card1easy.visibility = View.INVISIBLE
                    card2easy.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(2)
                    solvedcards.add(1)
                    solvedcards.add(2)
                }
                card3easy.drawable -> {
                    card1easy.visibility = View.INVISIBLE
                    card3easy.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(3)
                    solvedcards.add(1)
                    solvedcards.add(3)
                }
                card4easy.drawable -> {
                    card1easy.visibility = View.INVISIBLE
                    card4easy.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(4)
                    solvedcards.add(1)
                    solvedcards.add(4)
                }
            }
        }

        card2easy.setOnClickListener {
            if (opencards.count() >= 2) {
                card1easy.setImageResource(R.drawable.logo)
                card3easy.setImageResource(R.drawable.logo)
                card4easy.setImageResource(R.drawable.logo)
                opencards.remove(1)
                opencards.remove(3)
                opencards.remove(4)
            }
            val resourceIdcard2 = resources.getIdentifier(card2asset, "drawable", packageName)
            card2easy.setImageResource(resourceIdcard2)
            opencards.add(2)

            println("OpenCards: $opencards")

            when (card2easy.drawable) {
                card1easy.drawable -> {
                    card2easy.visibility = View.INVISIBLE
                    card1easy.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(1)
                    solvedcards.add(2)
                    solvedcards.add(1)
                }
                card3easy.drawable -> {
                    card2easy.visibility = View.INVISIBLE
                    card3easy.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(3)
                    solvedcards.add(2)
                    solvedcards.add(3)
                }
                card4easy.drawable -> {
                    card2easy.visibility = View.INVISIBLE
                    card4easy.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(4)
                    solvedcards.add(2)
                    solvedcards.add(4)
                }
            }
        }

        card3easy.setOnClickListener {
            if (opencards.count() >= 2) {
                card1easy.setImageResource(R.drawable.logo)
                card2easy.setImageResource(R.drawable.logo)
                card4easy.setImageResource(R.drawable.logo)
                opencards.remove(1)
                opencards.remove(2)
                opencards.remove(4)
            }
            val resourceIdcard3 = resources.getIdentifier(card3asset, "drawable", packageName)
            card3easy.setImageResource(resourceIdcard3)
            opencards.add(3)

            println("OpenCards: $opencards")

            when (card3easy.drawable) {
                card2easy.drawable -> {
                    card1easy.visibility = View.INVISIBLE
                    card2easy.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(2)
                    solvedcards.add(3)
                    solvedcards.add(2)
                }
                card1easy.drawable -> {
                    card3easy.visibility = View.INVISIBLE
                    card1easy.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(1)
                    solvedcards.add(3)
                    solvedcards.add(1)
                }
                card4easy.drawable -> {
                    card3easy.visibility = View.INVISIBLE
                    card4easy.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(4)
                    solvedcards.add(3)
                    solvedcards.add(4)
                }
            }
        }

        card4easy.setOnClickListener {
            if (opencards.count() >= 2) {
                card1easy.setImageResource(R.drawable.logo)
                card2easy.setImageResource(R.drawable.logo)
                card3easy.setImageResource(R.drawable.logo)
                opencards.remove(1)
                opencards.remove(2)
                opencards.remove(3)
            }
            val resourceIdcard4 = resources.getIdentifier(card4asset, "drawable", packageName)
            card4easy.setImageResource(resourceIdcard4)
            opencards.add(4)

            println("OpenCards: $opencards")

            when (card4easy.drawable) {
                card2easy.drawable -> {
                    card4easy.visibility = View.INVISIBLE
                    card2easy.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(2)
                    solvedcards.add(4)
                    solvedcards.add(2)
                }
                card3easy.drawable -> {
                    card4easy.visibility = View.INVISIBLE
                    card3easy.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(3)
                    solvedcards.add(4)
                    solvedcards.add(3)
                }
                card1easy.drawable -> {
                    card1easy.visibility = View.INVISIBLE
                    card4easy.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(1)
                    solvedcards.add(4)
                    solvedcards.add(1)
                }
            }
        }
    }
}