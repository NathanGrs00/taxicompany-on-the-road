package com.nathan.taxibedrijf_on_the_road.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nathan.taxibedrijf_on_the_road.R
import com.nathan.taxibedrijf_on_the_road.data.model.Voertuig
import com.nathan.taxibedrijf_on_the_road.data.remote.APIController
import com.nathan.taxibedrijf_on_the_road.ui.details.DetailsFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    //Initialiseren van variabele, geen constructor nodig.
    lateinit var kentekenInvoer: EditText
    lateinit var lstVoertuigen: ListView
    lateinit var voertuigen: ArrayList<Voertuig>
    lateinit var adapter: ArrayAdapter<Voertuig>

    override fun onResume() {
        super.onResume()
        //Vind de navigatiebalk
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bnvNavigatieBalk)
        //Zet nav_details optie handmatig naar geselecteerd.
        bottomNav.menu.findItem(R.id.nav_home)?.isChecked = true
    }

    // Functie die nodig is om Fragment inhoud te schrijven.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Vinden van de juiste ID's in de xml file.
        kentekenInvoer = view.findViewById(R.id.ptKentekenInvoer)
        lstVoertuigen = view.findViewById(R.id.lstVoertuigen)
        val tvGeenResultaat = view.findViewById<TextView>(R.id.tvGeenKentekensFilter)
        val verzendKentekenKnop = view.findViewById<Button>(R.id.btnVerzendKenteken)

        // Ophalen van data uit de API. Roep de controller klasse aan.
        val ac = APIController(requireContext())

        tvGeenResultaat.visibility = View.GONE
        lstVoertuigen.visibility = View.VISIBLE

        // In de Controller wordt er door getData een ArrayList teruggegeven. Dit is opgehaaldeVoertuigen.
        ac.getData { opgehaaldeVoertuigen ->
            // Eerst zet je de lijst om in een lokale variabele, om zo de juiste informatie in de ListView te krijgen.
            voertuigen = opgehaaldeVoertuigen

            // ArrayAdapter wordt gebruikt om informatie te weergeven in een ListView.
            // Hiervoor is de applicatiecontext nodig, een ingebouwde layout en de lijst met voertuigen.
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, voertuigen)
            // Zorgt ervoor dat de adapter hierboven gekoppeld wordt aan de spot voor een adapter in de ListView in de xml file.
            lstVoertuigen.adapter = adapter
        }

        //Als de verzendknop wordt ingedrukt:
        verzendKentekenKnop.setOnClickListener {
            // Zet eerst de invoerwaarde om naar een String, en verwijder spaties. Maak er dan een variabele van genaamd gebruikersInput.
            val gebruikersInput = kentekenInvoer.text.toString().trim()

            // Omdat lateinit geinitialiseerd moet worden voordat de variabele gebruikt wordt, checken we eerst of de variabele bestaat.
            // Mocht de API Controller geen Lijst teruggeven, wordt de check dus gefaald.
            if (::voertuigen.isInitialized) {

                //Variabele om de lijst te filteren, op kentekens die alleen de gebruikersinput bevatten.
                // ignoreCase zorgt ervoor dat hoofdletters niet uitmaken in de input.
                val filteredList = voertuigen.filter { it.kenteken!!.contains(gebruikersInput, ignoreCase = true) }

                if (filteredList.isEmpty()){
                    tvGeenResultaat.visibility = View.VISIBLE
                    lstVoertuigen.visibility = View.GONE
                } else {
                    tvGeenResultaat.visibility = View.GONE
                    lstVoertuigen.visibility = View.VISIBLE

                    //Vervolgens moet de lijst nog gevuld worden met de nieuwe gefilterde lijst.
                    adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        filteredList
                    )
                    // Lijst gekoppeld.
                    lstVoertuigen.adapter = adapter
                }
            }
        }

        //Checkt er een item in de listView wordt geselecteerd. Alleen de positie is nodig.
        lstVoertuigen.setOnItemClickListener { _, _, position, _ ->
            // Het geselecteerde voertuig is een variabele, en het is de positie in de lijst van voertuigen.
            val geselecteerdVoertuig = voertuigen[position]

            // Vervolgens wordt er een model gemaakt met de data van het geselecteerde voertuig.
            val voertuig = Voertuig(
                geselecteerdVoertuig.kenteken,
                geselecteerdVoertuig.voertuigsoort,
                geselecteerdVoertuig.vervaldatum_apk,
                geselecteerdVoertuig.cilinderinhoud,
                geselecteerdVoertuig.aantal_cilinders,
                geselecteerdVoertuig.toegestane_maximum_massa_voertuig,
                geselecteerdVoertuig.maximum_massa_trekken_ongeremd,
                geselecteerdVoertuig.wielbasis
            )

            // Dit zorgt ervoor dat de data uit het model omgezet wordt in leesbare tekst voor het volgende scherm.
            val bundle = Bundle().apply {
                putSerializable("voertuig", voertuig)
            }

            // Zorgt ervoor dat de fragment de data meekrijgt.
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = bundle

            //Verandert de fragment naar de DetailsFragment.
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
