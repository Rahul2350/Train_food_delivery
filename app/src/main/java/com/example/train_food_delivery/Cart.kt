package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train_food_delivery.database.cartDatabase
import com.example.train_food_delivery.database.cartEntity
import com.example.train_food_delivery.database.cartRepository
import com.example.train_food_delivery.models.cartViewModel
import com.example.train_food_delivery.models.cartViewModelFactory

class Cart : AppCompatActivity(), CartAdapter.TotalPriceUpdateListener {
    private lateinit var viewModelcart: cartViewModel
    lateinit var emptyText  : TextView
    lateinit var emptycartImage : ImageView
    lateinit var listcart: List<cartEntity>
    var totalsum : Double? = null
    lateinit var checkoutbtn : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val grocerycartRepository = cartRepository(cartDatabase(this))
        val factory = cartViewModelFactory(grocerycartRepository)
        var cartrecview = findViewById<RecyclerView>(R.id.cart_products_recycler_view)
        viewModelcart = ViewModelProvider(this, factory).get(cartViewModel::class.java)
        val groceryAdapter = CartAdapter(listOf(), viewModelcart,this)
        cartrecview.layoutManager = LinearLayoutManager(this)
        cartrecview.adapter = groceryAdapter

       viewModelcart.allGroceryItems().observe(this, Observer {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
        })

        emptyText = findViewById(R.id.cart_empty_text_view)
        emptycartImage = findViewById(R.id.imageViewemptycart)
        val cartItems: LiveData<List<cartEntity>> = viewModelcart.getCartItems()
        cartItems.observe(this, Observer { cartItems ->
            // Update the adapter's list of cart items
            groceryAdapter.list = cartItems

            // Check if the cart is empty
            if (cartItems.isEmpty()) {
                // Show "No products yet" and hide the RecyclerView
                emptyText.visibility = View.VISIBLE
                emptycartImage.visibility = View.VISIBLE
                cartrecview.visibility = View.GONE
                checkoutbtn.text = "Cart Empty"
            } else {
                // Hide "No products yet" and show the RecyclerView
                emptyText.visibility = View.GONE
                emptycartImage.visibility = View.GONE
                cartrecview.visibility = View.VISIBLE
            }

            // Notify the adapter that the data has changed
            groceryAdapter.notifyDataSetChanged()
        })


        checkoutbtn  = findViewById(R.id.cart_check_out_btn)


        checkoutbtn.setOnClickListener {
            val intent = Intent(this,Checkout::class.java)
//            intent.putExtra("totalValue",totalsum)
            startActivity(intent)
        }




    }



    override fun updateTotalPrice(totalPrice: Double,initialTotalPrice: Double) {
        totalsum = initialTotalPrice
        checkoutbtn.text = "Checkout"

    }


}