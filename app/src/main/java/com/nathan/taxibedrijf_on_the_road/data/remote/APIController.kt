package com.nathan.taxibedrijf_on_the_road.data.remote

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nathan.taxibedrijf_on_the_road.data.model.Voertuig

class APIController(var context : Context) {
    fun getData(callback: (ArrayList<Voertuig>) -> Unit){
        //URL die gaat naar de data in een API, in json format.
        val url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json"
        val gson = Gson()
        //Aanmaken van een wachtrij om de verzoeken in te zetten. Dit komt uit de library Volley.
        //We gebruiken de context van de applicatie die nodig is.
        //Dit zorgt ervoor dat de RequestQueue gekoppeld is aan de gehele applicatie, niet alleen de activity.
        val queue : RequestQueue = Volley.newRequestQueue(context)
        //Vervolgens is er een StringRequest variabele aangemaakt, die een GET functie uitvoert. Dit betekent dat er data wordt opgehaald. Ook de API url is nodig.
        val request = StringRequest(
            Request.Method.GET, url,
            //variabele response doet iets als er een response is van de API
            {response->
                //Als je een specifieke array in de JSON file nodig hebt gebruik je:
                //var jArray = JSONObject(response.toString()).getJSONArray(*)

                //Maakt een Arraylist van type voertuig objecten.
                val arrayVoertuig = object: TypeToken<ArrayList<Voertuig>>(){}.type
                //Maakt een variabele dat de zojuist gemaakte Arraylist is, gevuld met van Json omgezette gson resultaten.
                val voertuigen : ArrayList<Voertuig> = gson.fromJson(response.toString(), arrayVoertuig)
                //Geeft de ArrayList terug.
                callback(voertuigen)
            },
            //Als er geen response is, dan geven we een error.
            {error->
                //Dit doen we door Toast te gebruiken, met als text "Geen Reactie!" en de duur is lang. .show() laat de Toast gelijk zien.
                Toast.makeText(context, "Geen reactie!", Toast.LENGTH_LONG).show()
            })
        // Vervolgens moeten we nog wel de GET request in de RequestQueue zetten.
        queue.add(request)
    }
}