package com.example.train_food_delivery.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.train_food_delivery.database.cartRepository

class cartViewModelFactory(private val repository: cartRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(cartViewModel::class.java)) {
            return cartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}