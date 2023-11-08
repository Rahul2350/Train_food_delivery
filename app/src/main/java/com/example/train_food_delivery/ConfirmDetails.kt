package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.train_food_delivery.database.cartDatabase
import com.example.train_food_delivery.database.cartRepository
import com.example.train_food_delivery.models.cartViewModel
import com.example.train_food_delivery.models.cartViewModelFactory

class ConfirmDetails : AppCompatActivity() {
    private lateinit var viewModelcart: cartViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_details)

        val grocerycartRepository = cartRepository(cartDatabase(this))
        val factory = cartViewModelFactory(grocerycartRepository)
        viewModelcart = ViewModelProvider(this, factory).get(cartViewModel::class.java)


        var addressN = findViewById<TextView>(R.id.addressName)
        var addressT = findViewById<TextView>(R.id.addressTrain)
        var addressS = findViewById<TextView>(R.id.addressSeat)
        var addressM = findViewById<TextView>(R.id.addressMobile)
//        var TTotal = findViewById<TextView>(R.id.tTotal)
        var CnfB = findViewById<Button>(R.id.buttoncnf)

        val intent2 = intent
        val addresName = intent2.getStringExtra("BoardingName")
        val addresTrain = intent2.getStringExtra("BoardingTrain")
        val addresSeat = intent2.getStringExtra("BoardingSeat")
        val addresMob = intent2.getStringExtra("BoardingMob")
//        val TotalValue = intent2.getStringExtra("totalValue")

        addressN.setText("Boarding Person Name : " + addresName)
        addressT.text = "Train No : " + addresTrain
        addressS.text = "Seat No : $addresSeat"
        addressM.text = "Mobile No : $addresMob"
//        TTotal.text = TotalValue

        CnfB.setOnClickListener {
            val itn = Intent(this,orderConfirm::class.java)
            startActivity(itn)

            viewModelcart.deleteAllCartItems()
        }

    }
}