package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var kentekenInvoer: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        kentekenInvoer = findViewById(R.id.ptKentekenInvoer)

        val verzendKentekenKnop = findViewById<Button>(R.id.btnVerzendKenteken)
        verzendKentekenKnop.setOnClickListener(){
            val gebruikersInput = kentekenInvoer.text.toString()

            val fragment = ResultFragment()
            val bundle = Bundle()
            bundle.putString("GEBRUIKERS_INPUT", gebruikersInput)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fmResultaat, fragment)
                .commit()
        }
    }
}