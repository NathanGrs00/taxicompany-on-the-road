package com.nathan.taxibedrijf_on_the_road

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class APIController(var context : Context) {
    fun getData(){
        //URL die gaat naar de data in een API, in json format.
        val url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json"
        var gson = Gson()
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
                //Als je een specifieke array in de JSON file nodig hebt gebruik je:
                //var jArray = JSONObject(response.toString()).getJSONArray()

                //Maakt een Arraylist van type voertuig objecten.
                var arrayVoertuig = object: TypeToken<ArrayList<Voertuig>>(){}.type
                //Maakt een variabele dat de zojuist gemaakte Arraylist is, gevuld met van Json omgezette gson resultaten.
                var voertuigen : ArrayList<Voertuig> = gson.fromJson(response.toString(), arrayVoertuig)
                //Print de kentekens uit van alle Voertuigen in de Arraylist
                voertuigen.forEach(){
                    println(it.kenteken)
                }
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