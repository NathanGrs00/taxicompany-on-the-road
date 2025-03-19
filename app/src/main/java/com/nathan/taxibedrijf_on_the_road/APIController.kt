package com.nathan.taxibedrijf_on_the_road

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class APIController(var context : Context) {
    fun getData(){
        //URL die gaat naar de data in een API, in json format.
        val url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json"
        //Aanmaken van een wachtrij om de verzoeken in te zetten. Dit komt uit de library Volley.
        //We gebruiken de context van de applicatie die nodig is.
        //Dit zorgt ervoor dat de RequestQueue gekoppeld is aan de gehele applicatie, niet alleen de activity.
        val queue : RequestQueue = Volley.newRequestQueue(context)
        //Vervolgens is er een StringRequest variabele aangemaakt, die een GET functie uitvoert. Dit betekent dat er data wordt opgehaald. Ook de API url is nodig.
        val request = StringRequest(
            Request.Method.GET, url,
            //Dan voeren we een print in de console uit als we response krijgen.
            {response->
                println(response.toString())
                var jArray = JSONObject(response.toString()).getJSONArray("")
            },
            //Zo niet, dat geven we een error.
            {error->
                //Dit doen we door Toast te gebruiken, over de gehele applicatie, met als text "Geen Reactie!" en de duur is lang. .show() laat de Toast gelijk zien.
                Toast.makeText(context, "Geen reactie!", Toast.LENGTH_LONG).show()
            })
        // Vervolgens moeten we nog wel de GET request in de RequestQueue zetten.
        queue.add(request)
    }
}