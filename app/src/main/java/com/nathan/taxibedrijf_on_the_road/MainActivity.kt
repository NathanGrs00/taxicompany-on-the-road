package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var kentekenInvoer: EditText
    lateinit var toonGegevens: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        kentekenInvoer = findViewById(R.id.ptKentekenInvoer)
        toonGegevens = findViewById(R.id.autoGegevensText)

        val verzendKentekenKnop = findViewById<Button>(R.id.btnVerzendKenteken)
        verzendKentekenKnop.setOnClickListener(){
            toonGegevens.text = kentekenInvoer.text
        }
    }
}