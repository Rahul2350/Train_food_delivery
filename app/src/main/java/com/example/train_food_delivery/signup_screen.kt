package com.example.train_food_delivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class signup_screen : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    lateinit var name1 : EditText
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var confpassword :  EditText
    lateinit var mobile : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        var textacc2 = findViewById<TextView>(R.id.textacc2)

        name1 = findViewById(R.id.editName)
        mobile = findViewById(R.id.editmobile)
        email = findViewById(R.id.editemail)
        password = findViewById(R.id.editpass)
        confpassword = findViewById(R.id.editpassconf)

        var btnsignup : Button = findViewById(R.id.buttonsignup)
        auth = FirebaseAuth.getInstance()
        btnsignup.setOnClickListener {
            var email2 = email.text.toString()
            var password2 = password.text.toString()
            var confirmpassword = confpassword.text.toString()

            if(email2.isNotEmpty() && password2.isNotEmpty() && confirmpassword.isNotEmpty()){
                if(password2 == confirmpassword  ){
                    auth.createUserWithEmailAndPassword(email2, password2)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                uploadData()
                                val intent = Intent(this, Dashboard::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                }
                else {
                    Toast.makeText(this,"Password and Confirm Password doesn't match", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(this,"Fields are empty", Toast.LENGTH_LONG).show()
            }
        }
        textacc2.setOnClickListener {
            var intent = Intent(this,login_screen::class.java)
            startActivity(intent)
        }
        }

    fun uploadData() {
        var namefull = name1.text.toString()
        var fullmail = email.text.toString()
        var fullMob = mobile.text.toString()

        val dataClass = DataClass(namefull,fullmail,fullMob)
        val calendar = Calendar.getInstance()
        FirebaseDatabase.getInstance().getReference("DATA LIST").child(Firebase.auth.currentUser!!.uid).setValue(dataClass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@signup_screen, "Saved", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this@signup_screen,Dashboard::class.java)
//                startActivity(intent)

            }
        }.addOnFailureListener { e ->
            Toast.makeText(
                this@signup_screen, e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    }

