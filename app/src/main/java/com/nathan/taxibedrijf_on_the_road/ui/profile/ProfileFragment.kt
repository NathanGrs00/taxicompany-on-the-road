package com.nathan.taxibedrijf_on_the_road.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nathan.taxibedrijf_on_the_road.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onResume() {
        super.onResume()
        //Vind de navigatiebalk
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bnvNavigatieBalk)
        //Zet nav_details optie handmatig naar geselecteerd.
        bottomNav.menu.findItem(R.id.nav_profiel)?.isChecked = true
    }

    // Functie die nodig is om Fragment inhoud te schrijven.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}