package com.example.androidtp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private val housesList: ArrayList<Home> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tlogin = intent.getStringExtra("TOKEN")
        if (tlogin.isNullOrEmpty()) {
            Toast.makeText(this, "Erreur : Token manquant.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Fetch houses using the token
        fetchHomes(tlogin)

        // Manage access button click listener
        val manageAccessButton = findViewById<Button>(R.id.manageAccessButton)
        manageAccessButton.setOnClickListener {
            val ownerHouse = housesList.find { it.owner }
            if (ownerHouse != null) {
                val intent = Intent(this, MainHouseAccessActivity::class.java)
                intent.putExtra("HOUSE_ID", ownerHouse.houseId) // Pass the house ID
                intent.putExtra("TOKEN", tlogin) // Pass the token
                startActivity(intent)
            } else {
                Toast.makeText(this, "Aucune maison principale trouvée.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchHomes(token: String) {
        Api().get<List<Home>>(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses",
            onSuccess = { responseCode, homes ->
                runOnUiThread {
                    when (responseCode) {
                        200 -> {
                            if (homes != null && homes.isNotEmpty()) {
                                housesList.clear()
                                housesList.addAll(homes)
                                displayHomes()
                            } else {
                                Toast.makeText(this, "Aucune maison trouvée.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        403 -> Toast.makeText(
                            this,
                            "Accès interdit. Veuillez vérifier votre token.",
                            Toast.LENGTH_SHORT
                        ).show()
                        400 -> Toast.makeText(
                            this,
                            "Les données fournies sont incorrectes.",
                            Toast.LENGTH_SHORT
                        ).show()
                        500 -> Toast.makeText(
                            this,
                            "Erreur serveur. Réessayez plus tard.",
                            Toast.LENGTH_SHORT
                        ).show()
                        else -> Toast.makeText(this, "Erreur inconnue : $responseCode.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            },
            securityToken = token
        )
    }

    private fun displayHomes() {
        val gridLayout = findViewById<GridLayout>(R.id.housesGrid)
        gridLayout.removeAllViews() // Clear previous items if any

        housesList.forEach { home ->
            val homeView = createHouseView(home)
            gridLayout.addView(homeView)
        }
    }

    private fun createHouseView(home: Home): View {
        val context = this
        val imageView = ImageView(context).apply {
            layoutParams = GridLayout.LayoutParams().apply {
                width = 200
                height = 200
                setMargins(16, 16, 16, 16)
            }
            setImageResource(if (home.owner) R.drawable.main_house_icon else R.drawable.secondary_house_icon)
            contentDescription = if (home.owner) "Maison Principale" else "Propriété Secondaire"
            setOnClickListener {
                navigateToHomePropertyActivity(home)
            }
        }
        return imageView
    }

    private fun navigateToHomePropertyActivity(home: Home) {
        val tlogin = intent.getStringExtra("TOKEN")
        val intent = Intent(this, HomePropertyActivity::class.java)
        intent.putExtra("HOUSE_ID", home.houseId) // Pass the house ID
        intent.putExtra("IS_OWNER", home.owner) // Indicate ownership
        intent.putExtra("TOKEN", tlogin) // Pass the token
        startActivity(intent)
    }
}
