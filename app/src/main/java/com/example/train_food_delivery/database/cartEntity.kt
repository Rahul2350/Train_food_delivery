package com.example.train_food_delivery.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class cartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val cost: Double,
    val image: String,
    var quantity: Int, // Add quantity field
    var total: Double

)