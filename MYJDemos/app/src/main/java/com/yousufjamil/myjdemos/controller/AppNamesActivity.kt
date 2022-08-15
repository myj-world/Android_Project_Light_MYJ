package com.yousufjamil.myjdemos.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.yousufjamil.myjdemos.R
import com.yousufjamil.myjdemos.controller.dinnerdecider.DinnerDeciderMainActivity
import com.yousufjamil.myjdemos.legal.AgreeActivity
import com.yousufjamil.myjdemos.model.AppNames
import com.yousufjamil.myjdemos.model.Apps

class AppNamesActivity : AppCompatActivity() {

    lateinit var adapter : ArrayAdapter<Apps>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_names)

        val appNameListView = findViewById<ListView>(R.id.appNameListView)
        adapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,
        AppNames.apps)
        appNameListView.adapter = adapter

        appNameListView.setOnItemClickListener{adapterView, view, i, l ->
            val appClicked = AppNames.apps[i]
            if (appClicked.name == "Dinner Decider") {
                val startDinnerDecider = Intent(this, DinnerDeciderMainActivity::class.java)
                startActivity(startDinnerDecider)
            }
        }
    }
}