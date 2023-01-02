package com.yousufjamil.myjtallycounter

import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentnum: TextView = findViewById(R.id.num)
        val addbtn: FloatingActionButton = findViewById(R.id.addbtn)
        val subbtn: FloatingActionButton = findViewById(R.id.subtractbtn)
        val targettxt: TextView = findViewById(R.id.target)
        val changetargettxt: EditText = findViewById(R.id.changetargettxt)
        val settargetbtn: Button = findViewById(R.id.settargetbtn)
        val resetbtn: FloatingActionButton = findViewById(R.id.resetbtn)

        var currentnumber = 0
        var currenttarget = 10

        addbtn.setOnClickListener {
            currentnumber+=1
            currentnum.text = currentnumber.toString()
            if (currentnumber >= currenttarget) {
                currentnum.setTextColor(Color.parseColor("#22b14c"))
            } else {
                currentnum.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        subbtn.setOnClickListener {
            currentnumber -= 1
            currentnum.text = currentnumber.toString()
            if (currentnumber >= currenttarget) {
                currentnum.setTextColor(Color.parseColor("#22b14c"))
            } else {
                currentnum.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        settargetbtn.setOnClickListener {
            var changetargettxtvalue = changetargettxt.text.toString()
            if (changetargettxtvalue == "") {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            } else {
                targettxt.text = "Target: $changetargettxtvalue"
                currenttarget = changetargettxtvalue.toInt()
                changetargettxt.text.clear()
            }
            if (currentnumber >= currenttarget) {
                currentnum.setTextColor(Color.parseColor("#22b14c"))
            } else {
                currentnum.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }

        resetbtn.setOnClickListener {
            currentnumber = 0
            currentnum.text = "0"
            if (currentnumber >= currenttarget) {
                currentnum.setTextColor(Color.parseColor("#22b14c"))
            } else {
                currentnum.setTextColor(Color.parseColor("#FFFFFF"))
            }
            currenttarget = 10
            targettxt.text = "Target: $currenttarget"
        }
    }
}