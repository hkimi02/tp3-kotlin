package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class secondActivity : ComponentActivity() {
    lateinit var nom_v: TextView
    lateinit var prenom_v: TextView
    lateinit var email_v: TextView
    lateinit var button: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val intent: Intent = intent
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val email = intent.getStringExtra("email")

        nom_v = findViewById(R.id.nom)
        prenom_v = findViewById(R.id.prenom)
        email_v = findViewById(R.id.email)
        button = findViewById(R.id.button)

        if (nom != null) {
            nom_v.text = nom
        }
        if (prenom != null) {
            prenom_v.text = prenom
        }
        if (email != null) {
            email_v.text = email
        }

        val all_text = "nom : $nom, prenom : $prenom, email : $email"
        button.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, all_text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, null))
        }
    }
}

