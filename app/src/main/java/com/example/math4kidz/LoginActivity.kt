package com.example.math4kidz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // ui elements
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // set onclick instructions for login button
        btnLogin.setOnClickListener  {
            if (editTextFirstName.text.toString().isEmpty() ||
                editTextLastName.text.toString().isEmpty())  {

                val toast = Toast.makeText(this,"Please enter your name!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, -200)
                toast.show()

            }
            // may need more validation checks
            else {
                var firstName = editTextFirstName.text.toString()
                var lastName = editTextLastName.text.toString()
                user = User(0, firstName, lastName, false)
                login()
            }
        }


    }



    private fun login()  {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}