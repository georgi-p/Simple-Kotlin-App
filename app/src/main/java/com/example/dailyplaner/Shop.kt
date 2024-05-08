package com.example.dailyplanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dailyplaner.R

class Shop : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        val button: Button = view.findViewById(R.id.openWebButton)
        button.setOnClickListener {

            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://dayviewer.com/"))
            startActivity(urlIntent)
        }

        val instagramButton: Button = view.findViewById(R.id.otherButton)
        instagramButton.setOnClickListener {
            val instagramUrl = "https://friday.app/software/online-daily-planner"
            openWebPage(instagramUrl)
        }


        val twitterButton: Button = view.findViewById(R.id.anotherButton)
        twitterButton.setOnClickListener {
            val twitterUrl = "https://www.bb-team.org/"
            openWebPage(twitterUrl)
        }

        return view
    }


    private fun openWebPage(url: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(urlIntent)
    }
}