package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)

        val intent = this.intent
        val bundle = intent.extras
        val tvKentekenInvoer: TextView = findViewById(R.id.tvKentekenInvoer)

        tvKentekenInvoer.text = bundle!!.get("KENTEKEN_INVOER").toString()

        val btnTerug: Button = findViewById(R.id.btnTerug)
        btnTerug.setOnClickListener(){
            finish()
        }
    }
}