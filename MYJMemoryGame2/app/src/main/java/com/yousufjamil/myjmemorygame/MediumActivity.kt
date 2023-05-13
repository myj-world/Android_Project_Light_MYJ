package com.yousufjamil.myjmemorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.Random

class MediumActivity : AppCompatActivity() {

    val images = mutableListOf("down", "up", "right", "left", "house", "heart", "thunder", "smiley")
    val random = Random()
    var chosen1 = 0
    var chosen2 = 0
    var chosen3 = 0
    var chosen4 = 0
    val chosen = mutableListOf<String>()
    lateinit var card1asset: String
    lateinit var card2asset: String
    lateinit var card3asset: String
    lateinit var card4asset: String
    lateinit var card5asset: String
    lateinit var card6asset: String
    lateinit var card7asset: String
    lateinit var card8asset: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium)

        val gameovertxtmed: TextView = findViewById(R.id.gameovertxtmed)
        val restartbtnmed: Button = findViewById(R.id.restartbtnmed)

        gameovertxtmed.visibility = View.INVISIBLE
        restartbtnmed.visibility = View.INVISIBLE

        restartbtnmed.setOnClickListener {
            val restartIntentMed = Intent(this, MediumActivity::class.java)
            startActivity(restartIntentMed)
            finish()
        }

        chooseimagesmed()

    }
    fun chooseimagesmed() {
        chosen1 = random.nextInt(images.count())
        chosen.add(images[chosen1])
        images.removeAt(chosen1)
        chosen2 = random.nextInt(images.count())
        chosen.add(images[chosen2])
        chosen3 = random.nextInt(images.count())
        chosen.add(images[chosen3])
        chosen4 = random.nextInt(images.count())
        chosen.add(images[chosen4])
        println("CurrentList: $chosen")
        assignimagesmed(chosen)
    }

    fun assignimagesmed(chosenlist: MutableList<String>) {
        var countnumber1 = 0
        var countnumber2 = 0
        var countnumber3 = 0
        var countnumber4 = 0
        var cardrandom = 0
        var bound = 4

//        Card 1
        cardrandom = random.nextInt(bound)
        card1asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

//        Card2
        cardrandom = random.nextInt(bound)
        card2asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

//        Card 3
        cardrandom = random.nextInt(bound)
        card3asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

        //        Card 4
        cardrandom = random.nextInt(bound)
        card4asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

        //        Card 5
        cardrandom = random.nextInt(bound)
        card5asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

        //        Card 6
        cardrandom = random.nextInt(bound)
        card6asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

        //        Card 7
        cardrandom = random.nextInt(bound)
        card7asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

        //        Card 8
        cardrandom = random.nextInt(bound)
        card8asset = chosenlist[cardrandom]
        if (cardrandom == 0) {
            countnumber1+=1
        } else if (cardrandom == 1){
            countnumber2 +=1
        } else if (cardrandom == 2){
            countnumber3 +=1
        } else {
            countnumber4 +=1
        }

        when(2) {
            countnumber1 -> {
                chosenlist.removeAt(0)
                bound -=1
                countnumber1 = 0
            }
            countnumber2 -> {
                chosenlist.removeAt(1)
                bound -=1
                countnumber2 = 0
            }
            countnumber3 -> {
                chosenlist.removeAt(2)
                bound -=1
                countnumber3 = 0
            }
            countnumber4 -> {
                chosenlist.removeAt(3)
                bound -=1
                countnumber4 = 0
            }
        }

        gamestartmed()
    }

    fun gamestartmed() {
        val card1med: ImageButton = findViewById(R.id.card1med)
        val card2med: ImageButton = findViewById(R.id.card2med)
        val card3med: ImageButton = findViewById(R.id.card3med)
        val card4med: ImageButton = findViewById(R.id.card4med)
        val card5med: ImageButton = findViewById(R.id.card5med)
        val card6med: ImageButton = findViewById(R.id.card6med)
        val card7med: ImageButton = findViewById(R.id.card7med)
        val card8med: ImageButton = findViewById(R.id.card8med)
        val opencards = mutableListOf<Int>()
        val solvedcards = mutableListOf<Int>()
        card1med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard1 = resources.getIdentifier(card1asset, "drawable", packageName)
            card1med.setImageResource(resourceIdcard1)
            opencards.add(1)
            when (card1med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(2)
                    solvedcards.add(1)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(3)
                    solvedcards.add(1)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card4med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(4)
                    solvedcards.add(1)
                    solvedcards.add(4)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(5)
                    solvedcards.add(1)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(6)
                    solvedcards.add(1)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(7)
                    solvedcards.add(1)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(1)
                    opencards.remove(8)
                    solvedcards.add(1)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card2med.setOnClickListener {
            if (opencards.count() >= 2) {
                card1med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard2 = resources.getIdentifier(card2asset, "drawable", packageName)
            card2med.setImageResource(resourceIdcard2)
            opencards.add(2)
            when (card2med.drawable.constantState) {
                card1med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card1med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(1)
                    solvedcards.add(2)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(3)
                    solvedcards.add(2)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card4med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(4)
                    solvedcards.add(2)
                    solvedcards.add(4)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(5)
                    solvedcards.add(2)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(6)
                    solvedcards.add(2)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(7)
                    solvedcards.add(2)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card2med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(2)
                    opencards.remove(8)
                    solvedcards.add(2)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card3med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card1med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard3 = resources.getIdentifier(card3asset, "drawable", packageName)
            card3med.setImageResource(resourceIdcard3)
            opencards.add(3)
            when (card3med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(2)
                    solvedcards.add(3)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card1med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card1med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(1)
                    solvedcards.add(3)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card4med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(4)
                    solvedcards.add(3)
                    solvedcards.add(4)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(5)
                    solvedcards.add(3)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(6)
                    solvedcards.add(3)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(7)
                    solvedcards.add(3)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card3med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(3)
                    opencards.remove(8)
                    solvedcards.add(3)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card4med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card1med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard4 = resources.getIdentifier(card4asset, "drawable", packageName)
            card4med.setImageResource(resourceIdcard4)
            opencards.add(4)
            when (card4med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(2)
                    solvedcards.add(4)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(3)
                    solvedcards.add(4)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card1med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card4med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(1)
                    solvedcards.add(4)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(5)
                    solvedcards.add(4)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(6)
                    solvedcards.add(4)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(7)
                    solvedcards.add(4)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(8)
                    solvedcards.add(4)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card5med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card1med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard5 = resources.getIdentifier(card5asset, "drawable", packageName)
            card5med.setImageResource(resourceIdcard5)
            opencards.add(5)
            when (card5med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card5med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(5)
                    opencards.remove(2)
                    solvedcards.add(5)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card5med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(5)
                    opencards.remove(3)
                    solvedcards.add(5)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card1med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(5)
                    opencards.remove(1)
                    solvedcards.add(5)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(5)
                    solvedcards.add(4)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card5med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(5)
                    opencards.remove(6)
                    solvedcards.add(5)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card5med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(5)
                    opencards.remove(7)
                    solvedcards.add(5)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card5med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(5)
                    opencards.remove(8)
                    solvedcards.add(5)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card6med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card1med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard6 = resources.getIdentifier(card6asset, "drawable", packageName)
            card6med.setImageResource(resourceIdcard6)
            opencards.add(6)
            when (card4med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(2)
                    solvedcards.add(6)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(3)
                    solvedcards.add(6)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card1med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(1)
                    solvedcards.add(6)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(5)
                    solvedcards.add(6)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card6med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(6)
                    solvedcards.add(4)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(7)
                    solvedcards.add(6)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(8)
                    solvedcards.add(6)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card7med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card1med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                card8med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard7 = resources.getIdentifier(card7asset, "drawable", packageName)
            card7med.setImageResource(resourceIdcard7)
            opencards.add(7)
            when (card7med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card7med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(7)
                    opencards.remove(2)
                    solvedcards.add(7)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card7med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(7)
                    opencards.remove(3)
                    solvedcards.add(7)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card1med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(7)
                    opencards.remove(1)
                    solvedcards.add(7)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card7med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(7)
                    opencards.remove(5)
                    solvedcards.add(7)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(7)
                    solvedcards.add(4)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(6)
                    opencards.remove(7)
                    solvedcards.add(6)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                card8med.drawable.constantState -> {
                    card7med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(7)
                    opencards.remove(8)
                    solvedcards.add(7)
                    solvedcards.add(8)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }

        card8med.setOnClickListener {
            if (opencards.count() >= 2) {
                card2med.setImageResource(R.drawable.logo)
                card3med.setImageResource(R.drawable.logo)
                card1med.setImageResource(R.drawable.logo)
                card5med.setImageResource(R.drawable.logo)
                card4med.setImageResource(R.drawable.logo)
                card7med.setImageResource(R.drawable.logo)
                card6med.setImageResource(R.drawable.logo)
                opencards.clear()
            }
            val resourceIdcard8 = resources.getIdentifier(card8asset, "drawable", packageName)
            card8med.setImageResource(resourceIdcard8)
            opencards.add(8)
            when (card8med.drawable.constantState) {
                card2med.drawable.constantState -> {
                    card8med.visibility = View.INVISIBLE
                    card2med.visibility = View.INVISIBLE
                    opencards.remove(8)
                    opencards.remove(2)
                    solvedcards.add(8)
                    solvedcards.add(2)
                    checkend(solvedcards)
                }
                card3med.drawable.constantState -> {
                    card8med.visibility = View.INVISIBLE
                    card3med.visibility = View.INVISIBLE
                    opencards.remove(8)
                    opencards.remove(3)
                    solvedcards.add(8)
                    solvedcards.add(3)
                    checkend(solvedcards)
                }
                card1med.drawable.constantState -> {
                    card1med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(8)
                    opencards.remove(1)
                    solvedcards.add(8)
                    solvedcards.add(1)
                    checkend(solvedcards)
                }
                card5med.drawable.constantState -> {
                    card8med.visibility = View.INVISIBLE
                    card5med.visibility = View.INVISIBLE
                    opencards.remove(8)
                    opencards.remove(5)
                    solvedcards.add(8)
                    solvedcards.add(5)
                    checkend(solvedcards)
                }
                card4med.drawable.constantState -> {
                    card4med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(4)
                    opencards.remove(8)
                    solvedcards.add(4)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card6med.drawable.constantState -> {
                    card6med.visibility = View.INVISIBLE
                    card8med.visibility = View.INVISIBLE
                    opencards.remove(8)
                    opencards.remove(6)
                    solvedcards.add(8)
                    solvedcards.add(6)
                    checkend(solvedcards)
                }
                card7med.drawable.constantState -> {
                    card8med.visibility = View.INVISIBLE
                    card7med.visibility = View.INVISIBLE
                    opencards.remove(8)
                    opencards.remove(7)
                    solvedcards.add(8)
                    solvedcards.add(7)
                    checkend(solvedcards)
                }
                else -> println("None matches")
            }
        }
    }

    fun checkend(currentsolved: MutableList<Int>) {
        if (currentsolved.count() == 8) {
            println("CurrentSolved: $currentsolved")
            val gameovertxtmed: TextView = findViewById(R.id.gameovertxtmed)
            val restartbtnmed: Button = findViewById(R.id.restartbtnmed)
            gameovertxtmed.visibility = View.VISIBLE
            restartbtnmed.visibility = View.VISIBLE
        }
    }
}