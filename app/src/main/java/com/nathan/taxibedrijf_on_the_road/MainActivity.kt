package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nathan.taxibedrijf_on_the_road.ui.details.DetailsFragment
import com.nathan.taxibedrijf_on_the_road.ui.home.HomeFragment
import com.nathan.taxibedrijf_on_the_road.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    //Functie om inhoud aan een activity te geven.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Zorgt ervoor dat op bepaalde telefoons er geen blackbars zijn.
        enableEdgeToEdge()
        // Koppelt de Activity aan de xml file.
        setContentView(R.layout.activity_main)

        // Zoekt de navigatiebalk op in de xml file.
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnvNavigatieBalk)
        // Zorgt ervoor dat het geselecteerde item in de navigatiebalk het homepagina icoontje is.
        bottomNavigationView.selectedItemId = R.id.nav_home

        // Functie om te switchen van fragments, wordt gebruikt om te navigeren. Vraagt om een fragment als parameter.
        fun replaceFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                // Vervangt de huidige fragment in de fragmentContainer met de nieuwe fragment.
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        // Kijkt naar wat voor item er wordt geselecteerd.
        bottomNavigationView.setOnItemSelectedListener { item ->
            // Checkt of het id van het geselecteerde item hetzelfde is als de id's van een specifiek item.
            // 'when' gebruikt, want als je meerdere checks, met andere uitkomsten moet doen, is het beter om when te gebruiken i.p.v. else if.
            when (item.itemId) {
                // Ga naar het specifieke fragment als de id overeenkomt.
                R.id.nav_details -> {
                    replaceFragment(DetailsFragment())
                    //Afhandelen van de setOnItemSelectedListener event.
                    true
                }
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    //Afhandelen van de setOnItemSelectedListener event.
                    true
                }
                R.id.nav_profiel -> {
                    replaceFragment(ProfileFragment())
                    //Afhandelen van de setOnItemSelectedListener event.
                    true
                }
                //Afhandelen van de setOnItemSelectedListener event.
                else -> false
            }
        }
        //Zorgt ervoor dat het eerste scherm het homescherm is.
        replaceFragment(HomeFragment())
    }
}