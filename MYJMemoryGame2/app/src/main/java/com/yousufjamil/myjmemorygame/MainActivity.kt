package com.yousufjamil.myjmemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

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
            } else if (currentdifficulty.lowercase() == "medium") {
                println("Difficulty: Medium selected")
            } else if (currentdifficulty.lowercase() == "hard") {
                println("Difficulty: Hard selected")
            } else {
                println("Difficulty: Unknown")
            }
        }

    }
}