package com.nathan.taxibedrijf_on_the_road.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.nathan.taxibedrijf_on_the_road.R
import com.nathan.taxibedrijf_on_the_road.data.model.Voertuig
import com.nathan.taxibedrijf_on_the_road.data.model.VoertuigDetails
import com.nathan.taxibedrijf_on_the_road.data.remote.APIController
import com.nathan.taxibedrijf_on_the_road.ui.details.DetailsFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    //Initialiseren van variabele, geen constructor nodig.
    lateinit var kentekenInvoer: EditText
    lateinit var lstVoertuigen: ListView
    lateinit var voertuigen: ArrayList<Voertuig>
    lateinit var adapter: ArrayAdapter<Voertuig>

    // Functie die nodig is om Fragment inhoud te schrijven.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Vinden van de juiste ID's in de xml file.
        kentekenInvoer = view.findViewById(R.id.ptKentekenInvoer)
        lstVoertuigen = view.findViewById(R.id.lstVoertuigen)
        val verzendKentekenKnop = view.findViewById<Button>(R.id.btnVerzendKenteken)

        // Ophalen van data uit de API. Roep de controller klasse aan.
        val ac = APIController(requireContext())
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
                val filteredList =
                    voertuigen.filter { it.kenteken.contains(gebruikersInput, ignoreCase = true) }
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

        lstVoertuigen.setOnItemClickListener { _, _, position, _ ->
            val geselecteerdVoertuig = voertuigen[position]

            val voertuigDetails = VoertuigDetails(
                geselecteerdVoertuig.kenteken,
                "Auto", //TODO: dummydata vervangen met data uit API
                "01-01-2026",
                "2000cc",
                "4",
                "1500kg",
                "750kg",
                "270cm"
            )

            val bundle = Bundle().apply {
                putSerializable("voertuigDetails", voertuigDetails)
            }

            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment) // Zorg dat fragmentContainer correct is in je layout
                .addToBackStack(null)
                .commit()
        }
    }
}
