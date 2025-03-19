package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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

        //URL die gaat naar de data in een API, in json format.
        val url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json"
        //Aanmaken van een wachtrij om de verzoeken in te zetten. Dit komt uit de library Volley.
        //'this' is de instantie van MainActivity die momenteel runt, daarvan is de context van de applicatie nodig.
        //Dit zorgt ervoor dat de RequestQueue gekoppeld is aan de gehele applicatie, niet alleen de activity.
        val queue : RequestQueue = Volley.newRequestQueue(this.applicationContext)
        //Vervolgens is er een StringRequest variabele aangemaakt, die een GET functie uitvoert. Dit betekent dat er data wordt opgehaald. Ook de API url is nodig.
        val request = StringRequest(Request.Method.GET, url,
            //Dan voeren we een print in de console uit als we response krijgen.
            {response->
                println(response.toString())
            },
            //Zo niet, dat geven we een error.
            {error->
                //Dit doen we door Toast te gebruiken, over de gehele applicatie, met als text "Geen Reactie!" en de duur is lang. .show() laat de Toast gelijk zien.
                Toast.makeText(this.applicationContext, "Geen reactie!", Toast.LENGTH_LONG).show()
            })
        // Vervolgens moeten we nog wel de GET request in de RequestQueue zetten.
        queue.add(request)
    }
}