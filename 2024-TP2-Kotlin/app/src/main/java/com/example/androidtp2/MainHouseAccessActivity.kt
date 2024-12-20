package com.example.androidtp2

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainHouseAccessActivity : AppCompatActivity() {

    private var houseId: Int = -1
    private var token: String = ""
    private val usersWithAccess = mutableListOf<UserData>() // Store users with access

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_access)

        // Retrieve house ID and token from the intent
        houseId = intent.getIntExtra("HOUSE_ID", -1)
        token = intent.getStringExtra("TOKEN") ?: ""

        if (houseId == -1 || token.isEmpty()) {
            Toast.makeText(this, "Erreur : Données manquantes.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val grantPermissionButton: Button = findViewById(R.id.grantPermissionButton)
        val removePermissionButton: Button = findViewById(R.id.removePermissionButton)

        // Grant Permission
        grantPermissionButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un nom d'utilisateur.", Toast.LENGTH_SHORT).show()
            } else {
                grantPermission(username)
            }
        }

        // Remove Permission
        removePermissionButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un nom d'utilisateur.", Toast.LENGTH_SHORT).show()
            } else {
                revokePermission(username)
            }
        }

        // Fetch initial users with access
        fetchUsersWithAccess()
    }

    private fun grantPermission(username: String) {
        Api().post(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$houseId/users",
            data = mapOf("username" to username),
            onSuccess = { responseCode ->
                runOnUiThread {
                    if (responseCode == 200) {
                        Toast.makeText(this, "$username a été ajouté avec succès.", Toast.LENGTH_SHORT).show()
                        fetchUsersWithAccess()
                    } else {
                        Toast.makeText(this, "Erreur : Impossible d'ajouter l'utilisateur.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            securityToken = token
        )
    }

    private fun revokePermission(username: String) {
        Api().delete(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$houseId/users/$username",
            onSuccess = { responseCode ->
                runOnUiThread {
                    if (responseCode == 200) {
                        Toast.makeText(this, "$username n'a plus accès.", Toast.LENGTH_SHORT).show()
                        fetchUsersWithAccess()
                    } else {
                        Toast.makeText(this, "Erreur : Impossible de supprimer l'accès.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            securityToken = token
        )
    }

    private fun fetchUsersWithAccess() {
        Api().get<List<UserData>>(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$houseId/users",
            onSuccess = { responseCode, users ->
                runOnUiThread {
                    if (responseCode == 200 && users != null) {
                        usersWithAccess.clear()
                        usersWithAccess.addAll(users)
                        displayUsersWithAccess()
                    } else {
                        Toast.makeText(this, "Erreur : Impossible de récupérer les utilisateurs.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            securityToken = token
        )
    }

    private fun displayUsersWithAccess() {
        val usersContainer: LinearLayout = findViewById(R.id.usersContainer)
        usersContainer.removeAllViews()

        for (user in usersWithAccess) {
            val userView = LayoutInflater.from(this).inflate(R.layout.item_user_access, usersContainer, false)
            val usernameTextView: TextView = userView.findViewById(R.id.usernameTextView)
            val removeAccessButton: Button = userView.findViewById(R.id.removeAccessButton)

            usernameTextView.text = user.username
            removeAccessButton.setOnClickListener { revokePermission(user.username) }

            usersContainer.addView(userView)
        }
    }
}
