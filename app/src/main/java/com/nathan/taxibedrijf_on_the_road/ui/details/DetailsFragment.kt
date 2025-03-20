package com.nathan.taxibedrijf_on_the_road.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nathan.taxibedrijf_on_the_road.R

//Fragment klasse (bij een fragment kan je gelijk de layout aan het type geven omdat in een fragment setContextView geen ding is.
class DetailsFragment : Fragment(R.layout.fragment_details) {
    // Functie die nodig is om inhoud in een fragment te plaatsen.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}