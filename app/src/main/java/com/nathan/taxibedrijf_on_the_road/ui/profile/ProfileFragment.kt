package com.nathan.taxibedrijf_on_the_road.ui.profile

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

}