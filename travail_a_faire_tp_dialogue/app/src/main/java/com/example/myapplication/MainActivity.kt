package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    lateinit var nom: EditText
    lateinit var prenom: EditText
    lateinit var email: EditText
    lateinit var valider: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        nom = findViewById(R.id.nom)
        prenom = findViewById(R.id.prenom)
        email = findViewById(R.id.email)
        valider = findViewById(R.id.valider)
        valider.setOnClickListener {
            if (nom.text != null && prenom.text != null && email.text != null) {
                val intent = Intent(it,secondActivity::class.java)
                intent.putExtra("nom", nom.text.toString())
                intent.putExtra("prenom", prenom.text.toString())
                intent.putExtra("email", email.text.toString())
                startActivity(intent)
            } }
    }
}
