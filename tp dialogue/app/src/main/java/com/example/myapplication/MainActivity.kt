package com.example.myapplication

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {
    lateinit var nom: EditText
    lateinit var prenom: EditText
    lateinit var email: EditText
    lateinit var page:LinearLayout
    lateinit var valider_btn: Button
    lateinit var web:WebView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        nom = findViewById(R.id.name)
        prenom = findViewById(R.id.prenom)
        email = findViewById(R.id.email)
        page=findViewById(R.id.layoutLinear)
        valider_btn = findViewById(R.id.valider_btn)
        web=findViewById(R.id.web)
        progressBar=findViewById(R.id.progressBar)
        val snack=Snackbar.make(page,"voulez vous remplir les champs ", Snackbar.LENGTH_LONG)
        snack.show()
    }

    fun valider(v: View?) {
        if (nom.getText().length === 0 || prenom.getText().length === 0 ||
            email.getText().length === 0
        ) {
           /* val ad: AlertDialog.Builder
            ad = AlertDialog.Builder(this)
            println("created ")
            ad.setMessage("Les champs ne doivent pas être vide")
            ad.setTitle("Error")
            ad.setIcon(android.R.drawable.btn_dialog)
            ad.setPositiveButton(
                "yes"
            )
            { dialogInterface, i -> finish() }
            val a = ad.create()
            a.show()*/

            }else{
            Toast.makeText(this, "valid infos your music is about to start downloading", Toast.LENGTH_LONG).show()
            val progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setTitle("Kotlin Progress Dialog")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressDialog.setMessage("Downloading music, please wait")
            progressDialog.setIndeterminate(true)
            progressDialog.setProgress(0)
            progressDialog.show()
        }
    }
    inner class WebViewClient : android.webkit.WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }
}