package com.example.nestedscrollviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedscrollviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var arr : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        createItems()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomViewAdapter(arr)

    }

    private fun createItems(){
        arr = ArrayList()

        for(i in 0..20){
            arr.add(i.toString())
        }
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.item_textView)


        fun bind(item : String){
            textView.text = item
        }
    }

    inner class CustomViewAdapter(val items : ArrayList<String>) : RecyclerView.Adapter<CustomViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = layoutInflater.inflate(R.layout.item_layout, parent, false)
            return CustomViewHolder(view)

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }


}