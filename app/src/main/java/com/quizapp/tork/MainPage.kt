package com.quizapp.tork

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
//import kotlinx.android.synthetic.main_page.*
class MainPage {
    class MainPage : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.main_page)

            val myButton: Button = findViewById(R.id.button)
            myButton.setOnClickListener {
                // Handle button click here
                val intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)
            }
        }
    }
}