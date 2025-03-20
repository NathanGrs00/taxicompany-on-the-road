package com.nathan.taxibedrijf_on_the_road.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.nathan.taxibedrijf_on_the_road.R
import com.nathan.taxibedrijf_on_the_road.data.model.Voertuig

//Fragment klasse (bij een fragment kan je gelijk de layout aan het type geven omdat in een fragment setContextView geen ding is.
class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var lstVoertuigDetails: ListView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Koppel listview
        lstVoertuigDetails = view.findViewById(R.id.lvVoertuigDetails)

        // Haal de voertuigdetails op via arguments
        val voertuigDetails = arguments?.getSerializable("voertuig") as? Voertuig

        voertuigDetails?.let {
            val detailsLijst = listOf(
                "Kenteken: ${it.kenteken}",
                "Voertuigsoort: ${it.voertuigsoort}",
                "Vervaldatum: ${it.vervaldatum_apk}",
                "Cilinderinhoud: ${it.cilinderinhoud}",
                "Aantal cilinders: ${it.aantal_cilinders}",
                "Massa voertuig: ${it.toegestane_maximum_massa_voertuig}",
                "Max trekken: ${it.maximum_massa_trekken_ongeremd}",
                "Wielbasis: ${it.wielbasis}"
            )

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, detailsLijst)
            lstVoertuigDetails.adapter = adapter
        }
    }
}