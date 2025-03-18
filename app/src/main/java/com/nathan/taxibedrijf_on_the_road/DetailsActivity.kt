package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)

        val kentekenInvoer = intent.getStringExtra("KENTEKEN_INVOER")

        val textView: TextView = findViewById(R.id.tvKentekenInvoer)
        textView.text = kentekenInvoer
    }
}