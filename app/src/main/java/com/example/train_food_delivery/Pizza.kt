package com.example.train_food_delivery

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train_food_delivery.database.cartDatabase
import com.example.train_food_delivery.database.cartEntity
import com.example.train_food_delivery.database.cartRepository
import com.example.train_food_delivery.models.cartViewModel
import com.example.train_food_delivery.models.cartViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Pizza : AppCompatActivity() {
    lateinit var vieModel: cartViewModel
    val  list = ArrayList<RecyclerModel>()
    var adapteritem : recycleradapter? = null
    lateinit var itemrecycler : RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza)

        val groceryRepository = cartRepository(cartDatabase(this))
        val factory = cartViewModelFactory(groceryRepository)
        vieModel = ViewModelProvider(this,factory).get(cartViewModel::class.java)

        itemrecycler = findViewById(R.id.itemresview2)
        itemrecycler.setNestedScrollingEnabled(false);



        adapteritem = recycleradapter(list) { name, price, image ->
            val cartItem =
                cartEntity(id = 0, name = name, cost = price.toDouble(), image = image, quantity = 1, total = 0.0)

            vieModel.insert(cartItem)

            Toast.makeText(this, "Added in cart", Toast.LENGTH_LONG).show()

        }
        val layoutManager = LinearLayoutManager(applicationContext)
        itemrecycler.layoutManager = layoutManager
        itemrecycler.itemAnimator = DefaultItemAnimator()
        itemrecycler.adapter = adapteritem
        call()
    }

    private fun call() {
        list.add(RecyclerModel("Veg-Loaded","Domino","120",R.drawable.pizza))
        list.add(RecyclerModel("Onion Pizza","Domino","230",R.drawable.onionpizza))
        list.add(RecyclerModel("Capsicum Pizza","Burger King","480",R.drawable.capsicumtc))
        list.add(RecyclerModel("Mushroom Mix Pizza","Burger King","380",R.drawable.mushroommix))
        list.add(RecyclerModel("Pepperoni Pizza","Domino","110",R.drawable.peroroni))
    }
}