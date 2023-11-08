package com.example.train_food_delivery

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train_food_delivery.database.cartDatabase
import com.example.train_food_delivery.database.cartEntity
import com.example.train_food_delivery.database.cartRepository
import com.example.train_food_delivery.models.cartViewModel
import com.example.train_food_delivery.models.cartViewModelFactory

class burger : AppCompatActivity() {
    lateinit var vieModel: cartViewModel
    val  list = ArrayList<RecyclerModel>()
    var adapteritem : recycleradapter? = null
    lateinit var itemrecycler : RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burger)

        val groceryRepository = cartRepository(cartDatabase(this))
        val factory = cartViewModelFactory(groceryRepository)
        vieModel = ViewModelProvider(this,factory).get(cartViewModel::class.java)

        itemrecycler = findViewById(R.id.itemresview2)
        itemrecycler.setNestedScrollingEnabled(false);



        adapteritem = recycleradapter(list) { name, price, image ->
            val cartItem =
                cartEntity(id = 0, name = name, cost = price.toDouble(), image = image, quantity = 1, total = 0.0)

            vieModel.insert(cartItem)

        }
        val layoutManager = LinearLayoutManager(applicationContext)
        itemrecycler.layoutManager = layoutManager
        itemrecycler.itemAnimator = DefaultItemAnimator()
        itemrecycler.adapter = adapteritem
        call()
    }

    private fun call() {
        list.add(RecyclerModel("Cheese Burger","Burger Bar&Bakery","180",R.drawable.cheeseburger))
        list.add(RecyclerModel("Hamburger","Gabbar-Brrgers","70",R.drawable.burger))
        list.add(RecyclerModel("Fluff Screamer","The Black Rabbit","80",R.drawable.fluffscreamerburger))
        list.add(RecyclerModel("Steamed Cheese Burger","Cheesy Juicy Burgers ","280",R.drawable.steamedcheeseburger))
        list.add(RecyclerModel("Bofsandwich","Burger Bar&Bakery","70",R.drawable.bofsandwich))

    }
}