package com.example.androidtp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainHouseAccessActivity : AppCompatActivity() {

    private val usersWithAccess: ArrayList<UserData> = ArrayList() // Store users with access as User objects
    private var houseId: Int = -1 // Placeholder for house ID
    private var token: String = "" // Token for authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_access)

        // Retrieve house ID and token from the intent
        houseId = intent.getIntExtra("HOUSE_ID", -1)
        token = intent.getStringExtra("TOKEN") ?: ""

        // If no token or invalid houseId, finish the activity
        if (houseId == -1 || token.isEmpty()) {
            Toast.makeText(this, "Erreur : Données manquantes.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val addUserButton = findViewById<Button>(R.id.addUserButton)

        // Handle adding a user
        addUserButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un nom d'utilisateur.", Toast.LENGTH_SHORT).show()
            } else {
                addUserToHouse(username) { success ->
                    if (success) {
                        usernameEditText.text.clear()
                        fetchUsersWithAccess() // Refresh the list after adding a user
                    }
                }
            }
        }

        // Fetch initial list of users
        fetchUsersWithAccess()
    }

    private fun addUserToHouse(username: String, callback: (Boolean) -> Unit) {
        // API call to add user access
        Api().post(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$houseId/users",
            data = mapOf("username" to username),
            onSuccess = { responseCode ->
                runOnUiThread {
                    if (responseCode == 200) {
                        Toast.makeText(this, "$username a été ajouté avec succès.", Toast.LENGTH_SHORT).show()
                        callback(true)
                    } else {
                        Toast.makeText(this, "Erreur : Impossible d'ajouter l'utilisateur.", Toast.LENGTH_SHORT).show()
                        callback(false)
                    }
                }
            },
            securityToken = token // Include the token for authentication
        )
    }

    private fun fetchUsersWithAccess() {
        // API call to fetch users
        Api().get<List<UserData>>(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$houseId/users",
            onSuccess = { responseCode, users ->
                runOnUiThread {
                    if (responseCode == 200 && users != null) {
                        usersWithAccess.clear()
                        usersWithAccess.addAll(users)
                        displayUsersWithAccess(users)
                    } else {
                        Toast.makeText(this, "Erreur : Impossible de récupérer les utilisateurs.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            securityToken = token
        )
    }

    private fun displayUsersWithAccess(users: List<UserData>) {
        val usersContainer = findViewById<LinearLayout>(R.id.usersContainer)
        usersContainer.removeAllViews() // Clear previous views

        users.forEach { user ->
            val userView = createUserView(user)
            usersContainer.addView(userView)
        }
    }

    private fun createUserView(user: UserData): View {
        val inflater = LayoutInflater.from(this)
        val userView = inflater.inflate(R.layout.item_user_access, null)

        val usernameTextView = userView.findViewById<TextView>(R.id.usernameTextView)
        val removeAccessButton = userView.findViewById<Button>(R.id.removeAccessButton)

        usernameTextView.text = user.username // Use User object's username
        removeAccessButton.setOnClickListener {
            removeUserAccess(user) { success ->
                if (success) fetchUsersWithAccess() // Refresh the list after removing a user
            }
        }

        return userView
    }

    private fun removeUserAccess(userData: UserData, callback: (Boolean) -> Unit) {
        // API call to remove user access
        Api().delete(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$houseId/users/${userData.id}",
            onSuccess = { responseCode ->
                runOnUiThread {
                    if (responseCode == 200) {
                        usersWithAccess.remove(userData) // Remove user from local list
                        Toast.makeText(this, "${userData.username} n'a plus accès.", Toast.LENGTH_SHORT).show()
                        callback(true)
                    } else {
                        Toast.makeText(this, "Erreur : Impossible de supprimer l'accès.", Toast.LENGTH_SHORT).show()
                        callback(false)
                    }
                }
            },
            securityToken = token // Include the token for authentication
        )
    }
}
