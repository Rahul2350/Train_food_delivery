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

class thali : AppCompatActivity() {
    lateinit var vieModel: cartViewModel
    val  list = ArrayList<RecyclerModel>()
    var adapteritem : recycleradapter? = null
    lateinit var itemrecycler : RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thali)

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
        list.add(RecyclerModel("North Indian Thali","Kitchen-ette","120",R.drawable.thali))
        list.add(RecyclerModel("Rajasthani thali","Oven express","90",R.drawable.rajasthanithali))
        list.add(RecyclerModel("South indian thali","Bengali Bawarchi","80",R.drawable.souththali))
        list.add(RecyclerModel("Packed-thali for students","Kitcehn-ette","80",R.drawable.thali2))
    }
}