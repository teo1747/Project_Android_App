package com.example.androidtp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePropertyActivity : AppCompatActivity() {

    private var homeId: Int = -1
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_property)

        // Retrieve token and home ID from the intent
        homeId = intent.getIntExtra("HOUSE_ID", -1)
        token = intent.getStringExtra("TOKEN") ?: ""

        if (homeId == -1 || token.isEmpty()) {
            // Handle missing data
            finish()
            return
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set default fragment (DownFloorFragment)
        loadFragment(DownFloorFragment(), homeId, token)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_down_floor -> {
                    loadFragment(DownFloorFragment(), homeId, token)
                    true
                }
                R.id.navigation_up_floor -> {
                    loadFragment(UpFloorFragment(), homeId, token)
                    true
                }
                R.id.navigation_settings -> {
                    loadFragment(SettingsFragment(), homeId, token)
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment, homeId: Int, token: String) {
        val bundle = Bundle()
        bundle.putInt("HOME_ID", homeId)
        bundle.putString("TOKEN", token)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
