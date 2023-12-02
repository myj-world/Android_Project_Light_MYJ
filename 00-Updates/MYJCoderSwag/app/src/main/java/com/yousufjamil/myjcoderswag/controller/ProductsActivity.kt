package com.yousufjamil.myjcoderswag.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yousufjamil.myjcoderswag.R
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yousufjamil.myjcoderswag.adapters.CategoryAdapter
import com.yousufjamil.myjcoderswag.constants.EXTRA_CATEGORY
import com.yousufjamil.myjcoderswag.model.DataService

class ProductsActivity : AppCompatActivity() {
    lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
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

        val categoryNameExtra = intent.getStringExtra(EXTRA_CATEGORY)
        val productRecyclerView = findViewById<RecyclerView>(R.id.categoryProductListRecyclerView)

        adapter = CategoryAdapter(this, DataService.getProducts(categoryNameExtra))

        val orientation = resources.configuration.orientation
        val screenSize = resources.configuration.screenWidthDp
        var spancount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spancount = 3
        }
        if (screenSize >= 720) {
            spancount = 3
        }

        val layoutManager = GridLayoutManager(this, spancount)
        productRecyclerView.layoutManager = layoutManager
        productRecyclerView.adapter = adapter
    }
}