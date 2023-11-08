package com.example.train_food_delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast

class rateus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rateus)
        var lbtn: Button = findViewById(R.id.laterbtn)
        var rbtn: Button = findViewById(R.id.ratenow)
        var rating: RatingBar = findViewById(R.id.rating)
        var emoji: ImageView = findViewById(R.id.emoji)

        rating.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, _, _ -> // Do something when the rating changes
                val rate = rating.rating
                if (rate <= 1) {
                    emoji.setImageResource(R.drawable.sad)
                } else if (rate <= 2) {
                    emoji.setImageResource(R.drawable.worried)
                } else if (rate <= 3) {
                    emoji.setImageResource(R.drawable.nuetral)
                } else if (rate <= 4) {
                    emoji.setImageResource(R.drawable.happy)
                } else {
                    emoji.setImageResource(R.drawable.sattisfied)
                }
                lbtn.setOnClickListener {
                    Toast.makeText(
                        this,
                        "RateUs Later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                rbtn.setOnClickListener {
                    Toast.makeText(
                        this,
                        "Thanks for your $rate rating",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }
}