package com.example.train_food_delivery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.train_food_delivery.database.cartEntity
import com.example.train_food_delivery.models.cartViewModel



class CartAdapter(var list: List<cartEntity>, val viewmodel: cartViewModel,private val totalPriceUpdateListener: TotalPriceUpdateListener) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    var totalCartPrice: Double = 0.0
    var initialTotalPrice: Double = 0.0

    interface TotalPriceUpdateListener {
        fun updateTotalPrice(totalPrice: Double,initialTotalPrice: Double)
    }
    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvCartItemName: TextView = view.findViewById(R.id.cart_product_title_tv)
        val tvCartItemPrice: TextView = view.findViewById(R.id.cart_product_price_tv)
        val productImage : ImageView = view.findViewById(R.id.product_image_view)
        val cart_product_Del_btn : ImageView = view.findViewById(R.id.cart_product_delete_btn)
        val plusitemButton : ImageView = view.findViewById(R.id.cart_product_plus_btn)
        val minusitemButton: ImageView = view.findViewById(R.id.cart_product_minus_btn)
        val quantityText : TextView = view.findViewById(R.id.cart_product_quantity_text_View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        return CartViewHolder(view)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var currentPosition = list[position]
        holder.tvCartItemName.text = currentPosition.name
        holder.tvCartItemPrice.text = currentPosition.cost.toString()
        holder.quantityText.text = currentPosition.quantity.toString()
        holder.productImage.setImageResource(currentPosition.image.toInt())


//        val itemInitialTotalPrice = currentPosition.cost * currentPosition.quantity
//        initialTotalPrice += itemInitialTotalPrice
//
//
//        if (currentPosition.quantity == 1) {
//            holder.tvCartItemPrice.text = itemInitialTotalPrice.toString()
//            totalPriceUpdateListener.updateTotalPrice(totalCartPrice, initialTotalPrice)
//        }



        holder.cart_product_Del_btn.setOnClickListener {
            viewmodel.delete(currentPosition)
//            val nq = currentPosition.quantity
//            initialTotalPrice -= currentPosition.cost
//            updateTotalPrice()
        }

        holder.plusitemButton.setOnClickListener {
            currentPosition.quantity++
            holder.quantityText.text = currentPosition.quantity.toString()
            // Update the total for this item
            currentPosition.total = currentPosition.cost * currentPosition.quantity
            initialTotalPrice += currentPosition.cost
            updateTotalPrice()
        }
        holder.minusitemButton.setOnClickListener {
            if (currentPosition.quantity > 0) {
                // Decrement quantity
                currentPosition.quantity--
                // Update the total for this item
                currentPosition.total = currentPosition.cost * currentPosition.quantity
                holder.quantityText.text = currentPosition.quantity.toString()
                initialTotalPrice -= currentPosition.cost
                updateTotalPrice() // Notify the adapter that data has changed
            }
        }



    }

    override fun getItemCount(): Int {
        return list.size
    }
    private fun updateTotalPrice() {
        // Calculate the total price for all items in the cart
        initialTotalPrice = list.sumByDouble { it.total }

        // Notify the activity that the total price has changed
        totalPriceUpdateListener.updateTotalPrice(totalCartPrice,initialTotalPrice)
    }




}



