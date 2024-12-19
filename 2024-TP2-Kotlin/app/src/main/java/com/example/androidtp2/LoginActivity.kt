package com.example.androidtp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        btnLogin.setOnClickListener {
            login()// Call the login method when the button is clicked
        }


    }
        public fun registerNewAccount(view: View) {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent);
        }


        private fun login() {

            // Récupération des informations utilisateur depuis les champs d'entrée
            val login = findViewById<EditText>(R.id.LoginLogin).text.toString().trim()
            val password = findViewById<EditText>(R.id.LoginPassword).text.toString().trim()


            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return
            }

            // Création de l'objet RegisterData
            val registerData = RegisterData(login, password)

            Api().post<RegisterData, TokenData>(
                "https://polyhome.lesmoulinsdudev.com/api/users/auth",
                registerData, ::loginSuccess
            )

        }

    private fun loginSuccess(responseCode: Int, tokenData: TokenData?) {
        runOnUiThread {
            when (responseCode) {
                200 -> {
                    if (tokenData != null && tokenData.token.isNotEmpty()) {
                        // Navigate to HomeActivity with the token
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("TOKEN", tokenData.token)
                        startActivity(intent)
                        finish() // Close LoginActivity
                    } else {
                        Toast.makeText(this, "Erreur : Token non reçu.", Toast.LENGTH_SHORT).show()
                    }
                }
                400 -> Toast.makeText(this, "Données incorrectes. Veuillez vérifier vos informations.", Toast.LENGTH_SHORT).show()
                404 -> Toast.makeText(this, "Utilisateur introuvable.", Toast.LENGTH_SHORT).show()
                500 -> Toast.makeText(this, "Erreur serveur. Réessayez plus tard.", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Erreur inconnue : $responseCode.", Toast.LENGTH_SHORT).show()
            }
        }
    }






}

