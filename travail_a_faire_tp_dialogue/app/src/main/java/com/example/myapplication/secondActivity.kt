package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity

class secondActivity : ComponentActivity() {
    lateinit var nom_v: TextView
    lateinit var classe_v: TextView
    lateinit var date_v: TextView
    lateinit var email_v: TextView
    lateinit var button: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

         val intent: Intent = intent
        val nom = intent.getStringExtra("nom")
        val classe = intent.getStringExtra("classe")
        val date = intent.getStringExtra("date")
        val email = intent.getStringExtra("email")

        nom_v = findViewById(R.id.nom)
        email_v = findViewById(R.id.email)
        date_v=findViewById(R.id.date_v)
        classe_v=findViewById(R.id.classe_v)
        button = findViewById(R.id.button)

        if (nom != null) {
            nom_v.text = nom
        }
        if (email != null) {
            email_v.text = email
        }
        if(classe !=null){
            classe_v.text=classe
        }
        if(date !=null){
            classe_v.text=date
        }

        val all_text = "nom : $nom, email : $email,classe=$classe`,date=$date"
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
