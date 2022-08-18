package com.yousufjamil.myjdemos.controller.coderswag

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yousufjamil.myjdemos.R
import com.yousufjamil.myjdemos.adapters.CoderSwagProductRecyclerAdapter
import com.yousufjamil.myjdemos.model.DataService

@Suppress("DEPRECATION")
class CoderSwagMainActivity : AppCompatActivity() {

    lateinit var adapter: CoderSwagProductRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coder_swag_main)

        val primaryColorDrawable = ColorDrawable(Color.parseColor("#fd1923"))
        supportActionBar?.setBackgroundDrawable(primaryColorDrawable)
        setTitle("Coderswag")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.DevProfileColorMain)
        }

        val productRecyclerView = findViewById<RecyclerView>(R.id.productListRecyclerView)
        adapter = CoderSwagProductRecyclerAdapter(this, DataService.categories)
        productRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        productRecyclerView.layoutManager = layoutManager
        productRecyclerView.setHasFixedSize(true)
    }
}