package com.nathan.taxibedrijf_on_the_road.data.model

import java.io.Serializable

//Model klasse, die parameters kenteken en soort als variabele neemt.
class Voertuig (var kenteken: String?,
                var voertuigsoort: String?,
                var vervaldatum_apk: String?,
                var cilinderinhoud: String?,
                var aantal_cilinders: String?,
                var toegestane_maximum_massa_voertuig: String?,
                var maximum_massa_trekken_ongeremd: String?,
                var wielbasis: String?) : Serializable {

    //Functie om een string terug te geven.
    override fun toString(): String {
        //Geeft een string met de parameters terug, dit is hoe ze in de ListView worden weergeven.
        return "Kenteken = '$kenteken', \nVoertuigsoort = '$voertuigsoort'"
    }
}