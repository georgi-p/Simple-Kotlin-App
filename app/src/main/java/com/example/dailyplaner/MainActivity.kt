package com.example.dailyplaner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dailyplanner.Notes
import com.example.dailyplanner.Shop
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val page1 = Calendar()
        val page2 = Todo()
        val page3 = Notes()
        val page4 = Shop()

        Smqna_stranici(page1)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomMenu)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.calendar -> Smqna_stranici(page1)
                R.id.todo -> Smqna_stranici(page2)
                R.id.notes -> Smqna_stranici(page3)
                R.id.shop -> Smqna_stranici(page4)
            }
            true
        }
    }

    fun Smqna_stranici(tekushta: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, tekushta)
            commit()
        }

    }
}