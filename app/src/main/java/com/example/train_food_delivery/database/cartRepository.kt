package com.example.train_food_delivery.database

import androidx.lifecycle.LiveData

class cartRepository(private val db: cartDatabase) {

    val cartItems: LiveData<List<cartEntity>> = db.cartDao().getCartContents()
    val totalCartPrice: LiveData<Double> = db.cartDao().getCartTotal()
    suspend fun calculateAndUpdateTotalPrice() {
        val total = db.cartDao().calculateTotalPrice()
        db.cartDao().updateTotalPrice(total)
    }
    fun insert(item: cartEntity) = db.cartDao().insertItemToCart(item)
    fun delete(item:cartEntity) = db.cartDao().removeItemFromCart(item)
    fun getCartTotal() = db.cartDao().getCartTotal()

    fun allcartItems() = db.cartDao().getCartContents()
    fun checkIfAddedToCart(id: String) = db.cartDao().checkIfAddedToCart(id)
    fun deleteAllCartItems() = db.cartDao().deleteAllCartItems()
    fun incrementQuantity(id : Int) = db.cartDao().incrementQuantity(id)
    fun dcrementQuantity(id : Int) = db.cartDao().decrementQuantity(id)

}