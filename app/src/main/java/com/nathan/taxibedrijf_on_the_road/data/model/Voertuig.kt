package com.nathan.taxibedrijf_on_the_road.data.model

//Model klasse, die parameters kenteken en soort als variabele neemt.
class Voertuig (var kenteken: String, var voertuigsoort: String){

    //Functie om een string terug te geven.
    override fun toString(): String {
        //Geeft een string met de parameters terug, dit is hoe ze in de ListView worden weergeven.
        return "Kenteken = '$kenteken', Voertuigsoort = '$voertuigsoort'"
    }
}