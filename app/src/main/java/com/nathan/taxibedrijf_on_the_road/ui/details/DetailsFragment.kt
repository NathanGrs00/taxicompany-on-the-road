package com.nathan.taxibedrijf_on_the_road.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.nathan.taxibedrijf_on_the_road.R
import com.nathan.taxibedrijf_on_the_road.data.model.VoertuigDetails
import java.nio.file.WatchEvent.Modifier

//Fragment klasse (bij een fragment kan je gelijk de layout aan het type geven omdat in een fragment setContextView geen ding is.
class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var lstVoertuigDetails: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Koppel listview
        lstVoertuigDetails = view.findViewById(R.id.lvVoertuigDetails)

        // Haal de voertuigdetails op via arguments
        val voertuigDetails = arguments?.getSerializable("voertuigDetails") as? VoertuigDetails

        voertuigDetails?.let {
            val detailsLijst = listOf(
                "Kenteken: ${it.kenteken}",
                "Voertuigsoort: ${it.voertuigSoort}",
                "Vervaldatum: ${it.vervalDatum}",
                "Cilinderinhoud: ${it.cilinderInhoud}",
                "Aantal cilinders: ${it.cilinderAantal}",
                "Massa voertuig: ${it.massaVoertuig}",
                "Max trekken: ${it.maxTrekken}",
                "Wielbasis: ${it.Wielbasis}"
            )

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, detailsLijst)
            lstVoertuigDetails.adapter = adapter
        }
    }
}