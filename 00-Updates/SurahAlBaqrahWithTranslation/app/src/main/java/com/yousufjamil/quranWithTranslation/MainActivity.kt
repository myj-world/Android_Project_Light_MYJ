package com.yousufjamil.quranWithTranslation

import android.R.attr.value
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pdfFiles = "Surah Al Baqrah"

        val surahs = arrayOf("Surah Al Baqrah")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, surahs)

        val pdfListView = findViewById<ListView>(R.id.myPDFList)

        pdfListView.adapter = adapter

        pdfListView.setOnItemClickListener { adapterView, view, i, l ->
            val item: String = pdfListView.getItemAtPosition(i).toString()
            val start = Intent(
                applicationContext,
                PdfOpenerActivity::class.java
            )
            start.putExtra("pdfFileName", item)
            startActivity(start)
        }
    }
}