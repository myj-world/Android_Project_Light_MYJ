package com.yousufjamil.myjdemos.controller.dinnerdecider

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yousufjamil.myjdemos.R
import com.yousufjamil.myjdemos.controller.AppNamesActivity
import java.util.*

class DinnerDeciderMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinner_decider_main)

        val primaryColorDrawable = ColorDrawable(Color.parseColor("#5a7e8c"))
        supportActionBar?.setBackgroundDrawable(primaryColorDrawable)
        setTitle("Dinner Decider")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.dinnerDeciderPrimaryDark)
        }

        val dinnerDeciderFoodTxt = findViewById<TextView>(R.id.dinnerDeciderFoodTxt)
        val dinnerDeciderAddFoodEditTxt = findViewById<EditText>(R.id.dinnerDeciderAddFoodEditTxt)
        val dinnerDeciderAddFoodBtn = findViewById<Button>(R.id.dinnerDeciderAddFoodBtn)
        val dinnerDeciderDecideBtn = findViewById<Button>(R.id.dinnerDeciderDecideBtn)

        val foodList = arrayListOf("Chinese", "Pizza", "Lasagna", "McDonalds", "Burger", "Sandwitch")

        dinnerDeciderDecideBtn.setOnClickListener{
            val random = Random()
            val randomFood = random.nextInt(foodList.count())
            dinnerDeciderFoodTxt.text = foodList[randomFood]
        }

        dinnerDeciderAddFoodBtn.setOnClickListener {
            val addFoodTxt = dinnerDeciderAddFoodEditTxt.text.toString()
            if (addFoodTxt == "") {
                Toast.makeText(this, "Please type a food name", Toast.LENGTH_SHORT).show()
            } else {
                foodList.add(addFoodTxt)
                dinnerDeciderAddFoodEditTxt.text.clear()
            }
        }

    }
}


