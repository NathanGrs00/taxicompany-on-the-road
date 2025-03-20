package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var kentekenInvoer: EditText
    lateinit var lstVoertuigen: ListView
    lateinit var voertuigen: ArrayList<Voertuig>
    lateinit var adapter: ArrayAdapter<Voertuig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        kentekenInvoer = findViewById(R.id.ptKentekenInvoer)
        lstVoertuigen = findViewById(R.id.lstVoertuigen)
        val verzendKentekenKnop = findViewById<Button>(R.id.btnVerzendKenteken)

        //Maak een variabele 'ac' die de context van de applicatie stuurt naar de APIController Class.
        val ac = APIController(this.applicationContext)
        ac.getData { opgehaaldeVoertuigen ->
            voertuigen = opgehaaldeVoertuigen

            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, voertuigen)
            lstVoertuigen.adapter = adapter
        }

        verzendKentekenKnop.setOnClickListener(){
            val gebruikersInput = kentekenInvoer.text.toString().trim()

            if (::voertuigen.isInitialized){
                val filteredList = voertuigen.filter { it.kenteken.contains(gebruikersInput, ignoreCase = true) }
                adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
                lstVoertuigen.adapter = adapter

                //Data sturen naar een fragment
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
}