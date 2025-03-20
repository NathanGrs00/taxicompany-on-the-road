package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var kentekenInvoer: EditText
    lateinit var lstVoertuigen: ListView
    lateinit var voertuigen: ArrayList<Voertuig>
    lateinit var adapter: ArrayAdapter<Voertuig>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kentekenInvoer = view.findViewById(R.id.ptKentekenInvoer)
        lstVoertuigen = view.findViewById(R.id.lstVoertuigen)
        val verzendKentekenKnop = view.findViewById<Button>(R.id.btnVerzendKenteken)

        // Get vehicle data from the API
        val ac = APIController(requireContext())
        ac.getData { opgehaaldeVoertuigen ->
            voertuigen = opgehaaldeVoertuigen

            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, voertuigen)
            lstVoertuigen.adapter = adapter
        }

        verzendKentekenKnop.setOnClickListener {
            val gebruikersInput = kentekenInvoer.text.toString().trim()

            if (::voertuigen.isInitialized) {
                val filteredList =
                    voertuigen.filter { it.kenteken.contains(gebruikersInput, ignoreCase = true) }
                adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    filteredList
                )
                lstVoertuigen.adapter = adapter
            }
        }
    }
}
