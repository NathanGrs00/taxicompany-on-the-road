package com.nathan.taxibedrijf_on_the_road.data.model

import java.io.Serializable

class VoertuigDetails(var kenteken: String,
                      var voertuigSoort: String,
                      var vervalDatum: String,
                      var cilinderInhoud: String,
                      var cilinderAantal: String,
                      var massaVoertuig: String,
                      var maxTrekken: String,
                      var Wielbasis: String) : Serializable {

    override fun toString(): String {
        return "kenteken='$kenteken', voertuigSoort='$voertuigSoort', vervalDatum='$vervalDatum', cilinderInhoud='$cilinderInhoud', cilinderAantal='$cilinderAantal', massaVoertuig='$massaVoertuig', maxTrekken='$maxTrekken', Wielbasis='$Wielbasis')"
    }

}