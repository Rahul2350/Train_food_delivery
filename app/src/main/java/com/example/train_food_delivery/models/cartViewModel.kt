package com.example.train_food_delivery.models

import androidx.lifecycle.ViewModel
import com.example.train_food_delivery.database.cartEntity
import com.example.train_food_delivery.database.cartRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

class cartViewModel(private val repository: cartRepository) : ViewModel(){

//    val cartItems: LiveData<List<cartEntity>> = repository.allcartItems()
    val totalCartPrice: LiveData<Double> = repository.getCartTotal()



    fun increaseQuantity(item: cartEntity) {
        item.quantity++
        updateTotalPrice()
    }

    fun decreaseQuantity(item: cartEntity) {
        if (item.quantity > 0) {
            item.quantity--
            updateTotalPrice()
        }
    }

    fun updateTotalPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.calculateAndUpdateTotalPrice()
        }
    }

//    val totalLiveData: MutableLiveData<Double> = MutableLiveData(0.0)



//    val total: LiveData<Double>
//        get() = totalLiveData


//    fun incrementQuantity(item: cartEntity) {
//        item.quantity++
//        item.total = item.cost * item.quantity
//        // Update the total LiveData
//        updateTotal()
//    }
//    fun decrementQuantity(item: cartEntity) {
//        if (item.quantity > 0) {
//            item.quantity--
//            item.total = item.cost * item.quantity
//            // Update the total LiveData
//            updateTotal()
//
//        }
//    }
//    private fun updateTotal() {
//        // Calculate the total price by summing the total of all items
////        val items = allGroceryItems().value
////        val total = items?.sumByDouble { it.total } ?: 0.0
////        totalLiveData.value = total
//        val items = allGroceryItems().value
//        val total = items?.sumByDouble { it.total } ?: 0.0
//
//        // Use postValue to update the LiveData on the main thread
//        totalLiveData.postValue(total)
//    }
    fun insert(item: cartEntity) = GlobalScope.launch {
        repository.insert(item)
//        updateTotal()
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: cartEntity) = GlobalScope.launch {
        repository.delete(item)
    }

    //Here we initialized allGroceryItems function with repository
    fun allGroceryItems() = repository.allcartItems()

//    fun getCartTotal() {
//        repository.getCartTotal().observeForever { total ->
//            updateTotal()
//        }
//    }
    fun isItemInCart(id: String) = repository.checkIfAddedToCart(id)
    fun getCartTotal() = repository.getCartTotal()
    fun getCartItems(): LiveData<List<cartEntity>> {
        return repository.allcartItems()
    }
    fun deleteAllCartItems() = GlobalScope.launch {
        repository.deleteAllCartItems()
    }



}