package com.example.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Snackbar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {
    lateinit var nom: EditText
    lateinit var prenom: EditText
    lateinit var email: EditText
    lateinit var valider: Button
    private lateinit var btnDatePicker: Button
    private lateinit var tvSelectedDate: TextView
    lateinit var spinner: Spinner
    lateinit var date:String
    lateinit var classe:String
    private val calendar = Calendar.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        nom = findViewById(R.id.nom)
        email = findViewById(R.id.email)
        valider = findViewById(R.id.valider)
        val classes = resources.getStringArray(R.array.classe)
        spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classes)
        spinner.adapter = adapter
        btnDatePicker.setOnClickListener {
            showDatePicker()
        }
        valider.setOnClickListener {
            validate()
        }
        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                classe = classes[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                classe=""
            }
        }
    }
     fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                tvSelectedDate.text = "Selected Date: $formattedDate"
                this.date=formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    fun validate(): Boolean {
        if(nom.text.isEmpty()){
            Toast.makeText(this,"you should fill the name feild",Toast.LENGTH_LONG).show()
            return false
        }
        if(email.text.isEmpty()){
            Toast.makeText(this,"you should fill the email feild",Toast.LENGTH_LONG).show()
           return false
        }
        if(classe.isEmpty()){
            val rootView: View = findViewById(android.R.id.content)
            val snackBar= Snackbar.make(
                    rootView,
                    "you should choose a class",
                    Snackbar.LENGTH_LONG
                )
            snackBar.setAction("OK") {
                snackBar.dismiss()
            }
            snackBar.show()
           return false

        }
        if(date.isEmpty()){
            val rootView: View = findViewById(android.R.id.content)
            val snackBar= Snackbar.make(
                rootView,
                "you should choose your birth date ",
                Snackbar.LENGTH_LONG
            )
            snackBar.setAction("OK") {
                snackBar.dismiss()
            }
            snackBar.show()
            return false
        }
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Dialog Title")
            builder.setMessage("This is a dialog message.")
            builder.setPositiveButton("OK") { dialog, which ->
                val intent = Intent(this, secondActivity::class.java)
                intent.putExtra("nom", nom.text.toString())
                intent.putExtra("email", email.text.toString())
                intent.putExtra("date", date)
                intent.putExtra("classe",classe)
                startActivity(intent)
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(this,"you choose not to continue into sharing",Toast.LENGTH_LONG).show()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            return true
        }
    }

