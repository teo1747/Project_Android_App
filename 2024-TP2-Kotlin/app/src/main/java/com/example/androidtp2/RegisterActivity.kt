package com.example.androidtp2



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.Toast
import android.widget.EditText



class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val boutonRegister = findViewById<Button>(R.id.btnRegister)
        boutonRegister.setOnClickListener {
            register()
        }
    }
        public fun goToLogin(view: View) {
            finish();
        }

        private fun register() {
            // Récupération des informations utilisateur depuis les champs d'entrée
            val login = findViewById<EditText>(R.id.RegisterLogin).text.toString().trim()
            val password = findViewById<EditText>(R.id.RegisterPassword).text.toString().trim()


            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return
            }

            // Création de l'objet RegisterData
            val registerData = RegisterData(login, password)

            // Envoi des données à l'API
            Api().post<RegisterData>(
                "https://polyhome.lesmoulinsdudev.com/api/users/register",
                registerData,
                ::registerSuccess
            )

        }


        private fun registerSuccess(responseCode: Int) {
            runOnUiThread {
                 when (responseCode) {
                200 -> {
                    Toast.makeText(this, "Compte créé avec succès !", Toast.LENGTH_SHORT).show()
                    finish() // Navigate back to login
                }
                400 -> Toast.makeText(this, "Données invalides.", Toast.LENGTH_SHORT).show()
                409 -> Toast.makeText(this, "Le login est déjà utilisé.", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Une erreur est survenue ($responseCode).", Toast.LENGTH_SHORT).show()
            }
        }
    }

}



