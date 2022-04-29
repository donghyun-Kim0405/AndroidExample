package com.example.bindingexpressionexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bindingexpressionexample.databinding.ActivityRecyclerBinding
import com.example.bindingexpressionexample.databinding.ItemLayoutBinding

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter(createData())
    }//onCreate()

    private fun createData() : ArrayList<User>{
        val arr = ArrayList<User>()
        for(i in 0..20){
            arr.add(User(i.toString(), i.toString()))
        }
        return arr
    }//User ArrayList 생성

    inner class UserHolder(private val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        public fun bind(item : User){
            binding.user = item //item_layout의 data variable user에 item할당
        }
    }

    inner class UserAdapter(val items : ArrayList<User>) : RecyclerView.Adapter<UserHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
            return UserHolder(ItemLayoutBinding.inflate(layoutInflater, parent, false))
            //UserHolder 객체 생성후 리턴 -> Parameter : ItemLayout 의 dataBinding객체
        }

        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }


}