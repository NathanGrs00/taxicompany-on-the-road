package com.nathan.taxibedrijf_on_the_road

class Voertuig (var kenteken: String, var voertuigsoort: String){

    override fun toString(): String {
        return "Kenteken = '$kenteken', Voertuigsoort = '$voertuigsoort'"
    }
}