package com.example.cardviewexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardStackViewAdapter(var items : ArrayList<String>) : RecyclerView.Adapter<CardStackViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return CardStackViewHolder(view)

    }

    override fun onBindViewHolder(holder: CardStackViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}