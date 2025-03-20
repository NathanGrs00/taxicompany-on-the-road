package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnvNavigatieBalk)
        bottomNavigationView.selectedItemId = R.id.nav_home

        fun replaceFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_details -> {
                    replaceFragment(DetailsFragment()) // Load the Details fragment
                    true
                }
                R.id.nav_home -> {
                    replaceFragment(HomeFragment()) // Load the Home fragment
                    true
                }
                R.id.nav_profiel -> {
                    replaceFragment(ProfileFragment()) // Load the Profile fragment
                    true
                }
                else -> false
            }
        }
        replaceFragment(HomeFragment())
    }
}