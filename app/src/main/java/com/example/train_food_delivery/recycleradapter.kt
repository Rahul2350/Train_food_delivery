package com.example.train_food_delivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job

class recycleradapter (var list:List<RecyclerModel>,private val onAddToCartClick: (name: String, price: String, image : String) -> Unit): RecyclerView.Adapter<recycleradapter.MyHolderDemo>() {
    private val recItems = mutableListOf<RecyclerModel>()



    class MyHolderDemo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodname : TextView = itemView.findViewById(R.id.foodname)
        var resname : TextView = itemView.findViewById(R.id.resname)
        var price : TextView = itemView.findViewById(R.id.foodprice)
        var foodimage: ImageView = itemView.findViewById(R.id.foodimage)
        var buttonaddcart: Button = itemView.findViewById(R.id.addcart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderDemo {
        var inflator = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return MyHolderDemo(inflator)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolderDemo, position: Int) {
        val data = list[position]
        holder.foodname.text = data.getfoodname()
        holder.resname.text = data.getresname()
        holder.price.text = data.getprice()
        holder.foodimage.setImageResource(data.getIamge()!!)

//        holder.delb.setOnClickListener {
//            list.remove(position)
//            notifyItemRemoved(position)
//
//        }
        holder.buttonaddcart.setOnClickListener {
            onAddToCartClick(data.getfoodname().toString(), data.getprice().toString(),data.getIamge().toString())
        }


    }

}