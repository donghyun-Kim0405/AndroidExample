package com.example.roomdbexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbexample.databinding.ActivitySearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    //private lateinit var items: ArrayList<User>
    private lateinit var roomDB: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        roomDB = UserDatabase.getInstance(applicationContext)!!
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        setRecyclerView()

        binding.btnFindAll.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                roomDB?.let {
                    it.userDao().getAll()
                }?.also {
                    withContext(Main){
                        setRecyclerView(it as ArrayList<User>)
                    }
                }
            }

        }
        binding.btnFindByName.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                roomDB?.let {
                    it.userDao().findByUserName(binding.editName.text.toString())
                }?.also {
                    withContext(Main){
                        setRecyclerView(it as ArrayList<User>)
                    }

                }
            }
        }
        binding.btnDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                roomDB?.let {
                    it.userDao().deleteUserByName(binding.editName.text.toString())
                }
            }
        }

    }
    private fun setRecyclerView(items: ArrayList<User>? = null){
        if (items == null) {
            binding.recyclerView.adapter = UserAdapter(ArrayList<User>())
        }else{
            binding.recyclerView.adapter = UserAdapter(items)
        }
    }


    inner class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.text_name)
        val age : TextView = itemView.findViewById(R.id.text_age)
        val phone : TextView = itemView.findViewById(R.id.text_phone)

        public fun bind(user: User){
            name.text = user.name
            age.text = user.age
            phone.text = user.phone
        }
    }

    inner class UserAdapter(val items: ArrayList<User>) : RecyclerView.Adapter<UserHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
            val view = layoutInflater.inflate(R.layout.user_item_layout, parent, false)
            return UserHolder(view)
        }

        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }


}