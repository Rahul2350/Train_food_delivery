package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnspl = findViewById<Button>(R.id.btnsplash)
        btnspl.setOnClickListener {
            var intent = Intent(this,signup_screen::class.java)
            startActivity(intent)
        }
    }
}