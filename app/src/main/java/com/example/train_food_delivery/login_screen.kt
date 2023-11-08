package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login_screen : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    lateinit var emaillogin : EditText
    lateinit var passlogin : EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        auth = FirebaseAuth.getInstance()

        var forpass = findViewById<TextView>(R.id.textforgotpass)
        var loginBtn = findViewById<Button>(R.id.button2lg)

        emaillogin = findViewById(R.id.editemaillg)
        passlogin = findViewById(R.id.editpasslg)

        loginBtn.setOnClickListener {
            var loginEmail = emaillogin.text.toString()
            var loginPass = passlogin.text.toString()

            if(loginEmail.isNotEmpty() && loginPass.isNotEmpty())
                {
                    auth.signInWithEmailAndPassword(loginEmail, loginPass)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show()
                                val intent = Intent(this, Dashboard::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                }
            else {
                Toast.makeText(this,"Fields are empty", Toast.LENGTH_LONG).show()
            }
        }

        forpass.setOnClickListener {
            var intent3 = Intent(this,forgotpassword::class.java)
            startActivity(intent3)
        }
    }
}