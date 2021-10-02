package com.example.cardviewexample

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardStackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val wordArea : TextView = itemView.findViewById(R.id.item_textView)

    public fun bind(word : String){
        wordArea.text =  word
    }
}