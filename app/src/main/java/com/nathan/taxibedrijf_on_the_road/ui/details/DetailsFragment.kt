package com.nathan.taxibedrijf_on_the_road.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nathan.taxibedrijf_on_the_road.R
import com.nathan.taxibedrijf_on_the_road.data.model.Voertuig

//Fragment klasse (bij een fragment kan je gelijk de layout aan het type geven omdat in een fragment setContextView geen ding is.
class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var lstVoertuigDetails: ListView

    //Functie om handmatig het juiste navbar item te selecteren.
    override fun onResume() {
        super.onResume()
        //Vind de navigatiebalk
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bnvNavigatieBalk)
        //Zet nav_details optie handmatig naar geselecteerd.
        bottomNav.menu.findItem(R.id.nav_details)?.isChecked = true
    }

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

            val terugKnop : Button = view.findViewById(R.id.btnGaTerug)
            terugKnop.setOnClickListener(){
                parentFragmentManager.popBackStackImmediate()
            }
        }
    }
}