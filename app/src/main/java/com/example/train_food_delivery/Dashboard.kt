package com.example.train_food_delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.room.Room
import com.example.train_food_delivery.database.cartDatabase
import com.example.train_food_delivery.database.cartEntity
import com.example.train_food_delivery.database.cartRepository
import com.example.train_food_delivery.models.cartViewModel
import com.example.train_food_delivery.models.cartViewModelFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope

class Dashboard : AppCompatActivity() {
    lateinit var vieModel: cartViewModel
    lateinit var arrayList: ArrayList<GridModel>
    val  list = ArrayList<RecyclerModel>()
    var adapteritem : recycleradapter? = null
    lateinit var recyclerView: RecyclerView
//    lateinit var itemrecycler : RecyclerView
    var adapterRecycler: gridAdapter? = null
    lateinit var cartlist: List<cartEntity>
    private lateinit var db: cartDatabase
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val groceryRepository = cartRepository(cartDatabase(this))
        val factory = cartViewModelFactory(groceryRepository)
        vieModel = ViewModelProvider(this,factory).get(cartViewModel::class.java)
//        val cartAdapter = CartAdapter(listOf(),vieModel,this)
        var searchres = findViewById<ImageView>(R.id.searchres)
        var carticon = findViewById<ImageView>(R.id.cartIcon)


        db  =
            Room.databaseBuilder(this, cartDatabase::class.java, "restaurant_db").build()



        gridListData()
        recyclerView = findViewById(R.id.grid_view)
//        itemrecycler = findViewById(R.id.itemresview)
//        itemrecycler.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this,3)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.setLayoutManager(gridLayoutManager)
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        adapterRecycler = gridAdapter(this,arrayList)
        recyclerView.setAdapter(adapterRecycler)

        adapterRecycler!!.setOnItemClickListener(object : gridAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val clickedItem = arrayList[position]

                when (clickedItem.name) {
                    "Pizza" -> {
                        // Clicked on the "pizza" item
                        val intent = Intent(this@Dashboard, Pizza::class.java)
                        startActivity(intent)
                    }

                    "Burger" -> {
                        // Clicked on the "pizza" item
                        val intent = Intent(this@Dashboard, burger::class.java)
                        startActivity(intent)
                    }

                    "Desserts" -> {
                        val intent = Intent(this@Dashboard, desserts::class.java)
                        startActivity(intent)
                    }

                    "Thali" -> {
                        val intent = Intent(this@Dashboard, thali::class.java)
                        startActivity(intent)
                    }
                    // Handle other items...
                }
            }
        })

//        adapteritem = recycleradapter(list) { name, price, image ->
////            val cartItem =
////                cartEntity(id = 0, name = name, cost = price, image = image)
////
////            vieModel.insert(cartItem)
//            CoroutineScope(Dispatchers.IO).launch {
//
//                val isInCart = checkIfItemIsInCart(name)
//                if (isInCart) {
//                    // The item with the same name is already in the cart.
//                    // You can handle this situation, such as showing a message to the user.
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@Dashboard, "Already in cart", Toast.LENGTH_LONG).show()
//                    }
//
//                } else {
//                    val cartItem =
//                        cartEntity(id = 0, name = name, cost = price.toDouble(), image = image, quantity = 1, total = 0.0)
//
//                    vieModel.insert(cartItem)
//                }
//
//            }
//        }



//        val layoutManager = LinearLayoutManager(applicationContext)
//        itemrecycler.layoutManager = layoutManager
//        itemrecycler.itemAnimator = DefaultItemAnimator()
//        itemrecycler.adapter = adapteritem
//        call()


        carticon.setOnClickListener {
            val intent  = Intent(this,Cart::class.java)
            startActivity(intent)
        }

        val navView : NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                    true
                }
                R.id.myProfile -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.rateus -> {
                    val intent = Intent(this, rateus::class.java)
                    startActivity(intent)
                    true
                }

                R.id.logout -> {
                    val intent = Intent(this, login_screen::class.java)
                    startActivity(intent)
                    true
                }


                else -> {
                    false
                }
            }
        }


    }

//    private fun checkIfItemIsInCart(name: String?): Boolean {
//        var isInCart = false
//        CoroutineScope(Dispatchers.IO).launch{
//            val cartMenuFromDB: cartEntity? = db.cartDao().checkIfAddedToCart(name!!)
//            isInCart = cartMenuFromDB != null
//        }
//        return isInCart
//    }

    private suspend fun checkIfItemIsInCart(name: String): Boolean {
        return withContext(Dispatchers.IO) {
            var existingItem = db.cartDao().getItemByName(name)
            return@withContext existingItem != null
        }
    }

    private fun call() {
        list.add(RecyclerModel("Veg-Loaded","Domino","120",R.drawable.pizza))
        list.add(RecyclerModel("Burger","Gabbar-Brrgers","90",R.drawable.burger))
        list.add(RecyclerModel("Masala Dosa","Madras Cafe","80",R.drawable.dosa))

    }

    private fun gridListData() {
        arrayList = ArrayList<GridModel>()
        arrayList.add(GridModel("Pizza",R.drawable.pizza1cr))
        arrayList.add(GridModel("Burger",R.drawable.burcr))
        arrayList.add(GridModel("Thali",R.drawable.thacr))
        arrayList.add(GridModel("Drinks",R.drawable.drcr))
        arrayList.add(GridModel("Chinese",R.drawable.chicr))
        arrayList.add(GridModel("Desserts",R.drawable.descr))

    }


}