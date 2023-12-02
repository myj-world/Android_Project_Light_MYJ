package com.yousufjamil.myjcoderswag.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yousufjamil.myjcoderswag.R
import com.yousufjamil.myjcoderswag.adapters.ProductAdapter
import com.yousufjamil.myjcoderswag.constants.EXTRA_CATEGORY
import com.yousufjamil.myjcoderswag.model.DataService

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        adapter = ProductAdapter(this, DataService.categories) { category ->
            val productScreenIntentCoderSwag = Intent(this, ProductsActivity::class.java)
            productScreenIntentCoderSwag.putExtra(EXTRA_CATEGORY, category.title)
            startActivity(productScreenIntentCoderSwag)
        }
        productRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        productRecyclerView.layoutManager = layoutManager
        productRecyclerView.setHasFixedSize(true)
    }
}