package com.example.nestedrecyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = getOutAdapter()
    }//onCreate()


    private fun getOutAdapter(): OutAdapter {
        val outItems : ArrayList<InnerAdapter> = ArrayList()
        for (i in 0..30) {
            val innerAdapter = InnerAdapter(getInnerItems())
            outItems.add(innerAdapter)
        }
        val outAdapter = OutAdapter(outItems)
        return outAdapter
    }

    private fun getInnerItems() : ArrayList<String>{
        val arr = ArrayList<String>()
        for(i in 0..30){
            arr.add(i.toString())
        }
        return arr
    }

    inner class OutHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.findViewById(R.id.out_recyclerView)

        public fun bind(adapter : InnerAdapter){
            recyclerView.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }//bind()

    }

    inner class OutAdapter(val items : ArrayList<InnerAdapter>) : RecyclerView.Adapter<OutHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutHolder {
            val view = layoutInflater.inflate(R.layout.out_item_layout, parent, false)
            return OutHolder(view)
        }

        override fun onBindViewHolder(holder: OutHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById<TextView>(R.id.inner_text_View)
        public fun bind(item : String){
            textView.text = item
        }
    }

    inner class InnerAdapter(val items : ArrayList<String>) : RecyclerView.Adapter<InnerHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
            val view = layoutInflater.inflate(R.layout.inner_item_layout, parent, false)
            return InnerHolder(view)
        }

        override fun onBindViewHolder(holder: InnerHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }


}