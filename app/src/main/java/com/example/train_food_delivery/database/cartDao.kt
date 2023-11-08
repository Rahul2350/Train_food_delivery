package com.example.train_food_delivery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface cartDao {

//    @Insert
//    fun insertItemToCart(cartentity: cartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemToCart(cartentity: cartEntity)

    @Delete
    fun removeItemFromCart(cartentity: cartEntity)

    @Query("SELECT * FROM cart")
    fun getCartContents(): LiveData<List<cartEntity>>

    @Query("SELECT SUM(cost) FROM cart")
    fun getCartTotal(): LiveData<Double>

    @Query("DELETE FROM cart")
    fun deleteAllCartItems()

    @Query("SELECT * FROM cart WHERE id=:id")
    fun checkIfAddedToCart(id: String): cartEntity


    @Query("SELECT * FROM cart WHERE id=:id")
    fun isDataExist(id: String): Int

    @Query("SELECT * FROM cart WHERE name = :name LIMIT 1")
    fun getItemByName(name: String): cartEntity?

    @Query("SELECT SUM(cost * quantity) FROM cart")
    suspend fun calculateTotalPrice(): Double

    @Query("UPDATE cart SET total = :total")
    suspend fun updateTotalPrice(total: Double)

    @Query("UPDATE cart SET quantity = quantity + 1 WHERE id = :id")
    fun incrementQuantity(id: Int)

    @Query("UPDATE cart SET quantity = quantity - 1 WHERE id = :id AND quantity > 0")
    fun decrementQuantity(id: Int)
}