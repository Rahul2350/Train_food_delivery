package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random

class orderConfirm : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        val OrderIdText = findViewById<TextView>(R.id.textViewOrderId)

        var buttonbrowsse = findViewById<Button>(R.id.buttonbrowse)

        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")

        // Get the current timestamp as a formatted string
        val timestamp = dateFormat.format(Date())

        // Generate a random 4-digit number
        val random = Random().nextInt(10000)

        // Combine the timestamp and random number to create a unique ID
        val orderID = "$timestamp$random"

        OrderIdText.text = "Order id: $orderID"

        buttonbrowsse.setOnClickListener {
            val inte = Intent(this,Dashboard::class.java)
            startActivity(inte)
        }
    }
}