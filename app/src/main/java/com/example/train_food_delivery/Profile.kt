package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class Profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val calendar = Calendar.getInstance()

        val namef = findViewById<TextView>(R.id.textView9)
        val mailf = findViewById<TextView>(R.id.textView10)
        val monf = findViewById<TextView>(R.id.textView11)
        val ratecard = findViewById<CardView>(R.id.cardrate)
        val logoutcard = findViewById<CardView>(R.id.Logoutcard)

        ratecard.setOnClickListener() {
            val a = Intent(this,rateus::class.java)
            startActivity(a)
        }
        logoutcard.setOnClickListener(){
            val b = Intent(this,login_screen::class.java)
            startActivity(b)
        }

        FirebaseDatabase.getInstance().getReference("DATA LIST").child(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

            if (it.exists()){

                val firstname = it.child("firstName").value
                val secemail = it.child("registerEmail").value
                val seccontact = it.child("contactNo").value
                Toast.makeText(this,"Successfuly Read", Toast.LENGTH_SHORT).show()
                namef.text = firstname.toString()
                mailf.text = secemail.toString()
                monf.text = seccontact.toString()

            }else{

                Toast.makeText(this,"Updated", Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


        }
    }
}