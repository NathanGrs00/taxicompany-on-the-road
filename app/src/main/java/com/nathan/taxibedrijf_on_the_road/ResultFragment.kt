package com.nathan.taxibedrijf_on_the_road

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        val textView = view.findViewById<TextView>(R.id.tvResultaat)

        val text = arguments?.getString("GEBRUIKERS_INPUT")
        textView.text = text ?: "Er zijn geen auto's gevonden."

        return view
    }
}