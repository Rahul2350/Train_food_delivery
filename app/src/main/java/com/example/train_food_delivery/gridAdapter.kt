package com.example.train_food_delivery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class gridAdapter (var c: Context, var list:ArrayList<GridModel>) : RecyclerView.Adapter<gridAdapter.MyHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // Function to set the click listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name : TextView
        var image: ImageView
        init {
            name = itemView.findViewById(R.id.name)
            image = itemView.findViewById(R.id.imageView1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v: View = LayoutInflater.from(c).inflate(R.layout.custom_grid,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentItem = list[position]
        holder.name.text = list[position].name
        holder.image.setImageResource(list[position].image)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
}