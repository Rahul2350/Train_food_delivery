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

class desserts : AppCompatActivity() {
    lateinit var vieModel: cartViewModel
    val  list = ArrayList<RecyclerModel>()
    var adapteritem : recycleradapter? = null
    lateinit var itemrecycler : RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desserts)

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
        list.add(RecyclerModel("Zucchini Brownies","Sweets-Day-Cafe","280",R.drawable.zucchinibrownies))
        list.add(RecyclerModel("Belgian Waffles","Ck-Cafe","100",R.drawable.belgianwaffles))
        list.add(RecyclerModel("Rasgulla","Lovely Sweets","50",R.drawable.rassgula))
        list.add(RecyclerModel("Vanilla Pudding","Sweets-Day-Cafe","80",R.drawable.vanillapudding))
        list.add(RecyclerModel("Chocolate Mouse","Ck-Cafe","80",R.drawable.chocolatemouse))
        list.add(RecyclerModel("Strawberry Cake","Oven Express","250",R.drawable.strawberrycake))
        list.add(RecyclerModel("Blueberry cheesecake","Sweet-Day-Cafe","80",R.drawable.blueberrychesecake))
    }
}