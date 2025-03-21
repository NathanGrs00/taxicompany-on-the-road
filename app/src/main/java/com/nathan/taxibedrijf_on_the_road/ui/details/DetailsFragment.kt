package com.nathan.taxibedrijf_on_the_road.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
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
        val tvGeenKenteken = view.findViewById<TextView>(R.id.tvGeenKenteken)

        // Haal de voertuigdetails op via arguments
        val voertuigDetails = arguments?.getSerializable("voertuig") as? Voertuig

        if (voertuigDetails != null) {
            // Zet de placeholder tekst op onzichtbaar.
            tvGeenKenteken.visibility = View.GONE
            //Maakt een lijst van een labelstring samen met de variabele uit voertuigDetails.
            val detailsLijst = listOf(
                "Kenteken: ${voertuigDetails.kenteken ?: "Niet beschikbaar"}",
                "Voertuigsoort: ${voertuigDetails.voertuigsoort ?: "Niet beschikbaar"}",
                "Vervaldatum: ${voertuigDetails.vervaldatum_apk ?: "Niet beschikbaar"}",
                "Cilinderinhoud: ${voertuigDetails.cilinderinhoud ?: "Niet beschikbaar"}",
                "Aantal cilinders: ${voertuigDetails.aantal_cilinders ?: "Niet beschikbaar"}",
                "Massa voertuig: ${voertuigDetails.toegestane_maximum_massa_voertuig ?: "Niet beschikbaar"}",
                "Max trekken: ${voertuigDetails.maximum_massa_trekken_ongeremd ?: "Niet beschikbaar"}",
                "Wielbasis: ${voertuigDetails.wielbasis ?: "Niet beschikbaar"}"
            )

            // Zorgt ervoor dat de lijst in een ListView formaat gezet wordt.
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, detailsLijst)
            //Vult de ListView met de adapter hierboven
            lstVoertuigDetails.adapter = adapter
        } else {
            tvGeenKenteken.visibility = View.VISIBLE
        }

        //Terugknop
        val terugKnop : Button = view.findViewById(R.id.btnGaTerug)
        terugKnop.setOnClickListener {
            // Gaat terug naar de homepagina.
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}