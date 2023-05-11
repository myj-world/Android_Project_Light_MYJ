package com.yousufjamil.myjmemorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val difficultySelector: RadioGroup = findViewById(R.id.difficultySelector)
        var currentdifficulty = ""
        lateinit var selectedbutton: RadioButton
        val proceedbutton: Button = findViewById(R.id.proceedbutton)

        difficultySelector.setOnCheckedChangeListener { group, checkedid ->
            selectedbutton = group.findViewById(checkedid) as RadioButton
            currentdifficulty = selectedbutton.text.toString()
        }

        proceedbutton.setOnClickListener {
            if (currentdifficulty.lowercase() == "easy") {
                println("Difficulty: Easy selected")
                val easyIntent = Intent(this, EasyActivity::class.java)
                startActivity(easyIntent)
            } else if (currentdifficulty.lowercase() == "medium") {
                println("Difficulty: Medium selected")
                val mediumIntent = Intent(this, MediumActivity::class.java)
                startActivity(mediumIntent)
            } else if (currentdifficulty.lowercase() == "hard") {
                println("Difficulty: Hard selected")
                val hardIntent = Intent(this, HardActivity::class.java)
                startActivity(hardIntent)
            } else if (currentdifficulty == "") {
                Toast.makeText(this, "Please select a difficulty", Toast.LENGTH_SHORT).show()
            } else {
                println("Difficulty: Unknown")
                Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}