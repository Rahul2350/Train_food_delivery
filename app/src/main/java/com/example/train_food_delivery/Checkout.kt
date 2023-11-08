package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Checkout : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        val edittName = findViewById<EditText>(R.id.editTName)
        val edittTrain = findViewById<EditText>(R.id.editTTrain)
        val edittSeat = findViewById<EditText>(R.id.editTSeat)
        val edittMobile = findViewById<EditText>(R.id.editTMobile)

        val ButtonNext = findViewById<Button>(R.id.buttonNext)

//        var BoardingName = edittName.text.toString()
//        var BoardingTrain = edittTrain.text.toString()
//        var BoardingSeat = edittSeat.text.toString()
//        var BoardingMob = edittMobile.text.toString()
//
//        val intent = intent
//        val TotalPricei = intent.getStringExtra("totalValue")
        val TotalPricei = intent.getStringExtra("totalValue")


        ButtonNext.setOnClickListener {
            val BoardingName = edittName.text.toString()
            val BoardingTrain = edittTrain.text.toString()
            val BoardingSeat = edittSeat.text.toString()
            val BoardingMob = edittMobile.text.toString()
            val intent2 = Intent(this,ConfirmDetails::class.java)
            intent2.putExtra("BoardingName", BoardingName)
            intent2.putExtra("BoardingTrain", BoardingTrain)
            intent2.putExtra("BoardingSeat", BoardingSeat)
            intent2.putExtra("BoardingMob", BoardingMob)
            intent2.putExtra("totalValue", TotalPricei)
            startActivity(intent2)
        }




    }
}