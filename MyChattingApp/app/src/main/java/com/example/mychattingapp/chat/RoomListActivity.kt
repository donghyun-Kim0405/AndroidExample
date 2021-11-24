package com.example.mychattingapp.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.ActivityRoomListBinding
import com.example.mychattingapp.dto.ChatRoomDto

class RoomListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRoomListBinding
    private lateinit var nickName : String
    private lateinit var adapter :RoomListAdapter
    private val viewModel : RoomListViewModel by lazy{
        ViewModelProvider(this).get(RoomListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_list)
        initRecycler()
        setObserver()
        getRoomList()

        binding.btnCreateRoom.setOnClickListener {
            viewModel.createRoom(binding.editRoomName.text.toString())
        }
    }

    private fun initRecycler(){
        nickName = intent.getStringExtra("nickname")!!
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RoomListAdapter(ArrayList<ChatRoomDto>())
        binding.recyclerView.adapter = adapter
    }

    private fun setObserver(){
        viewModel.roomList.observe(this, Observer {
            updateUI(it)
        })
    }
    private fun getRoomList(){
        viewModel.getRoomList(nickName)
    }

    private fun updateUI(items : ArrayList<ChatRoomDto>){
        adapter = RoomListAdapter(items)
        binding.recyclerView.adapter = adapter
    }


    inner class RoomListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val roomName : TextView = itemView.findViewById(R.id.item_text_roomName)
        val btnEnter : Button = itemView.findViewById(R.id.item_btn_enter)

        init{
            btnEnter.setOnClickListener {
                val intent = Intent(baseContext, ChattingActivity::class.java).apply{
                    putExtra("roomname", roomName.text.toString())
                    putExtra("nickname", nickName)
                }
                startActivity(intent)

            }
        }

        public fun bind(item : ChatRoomDto){
            roomName.text = item.name
        }
    }

    inner class RoomListAdapter(val items : ArrayList<ChatRoomDto>) : RecyclerView.Adapter<RoomListHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomListHolder {
            val view = layoutInflater.inflate(R.layout.item_room_layout, parent, false)
            return RoomListHolder(view)
        }

        override fun onBindViewHolder(holder: RoomListHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}