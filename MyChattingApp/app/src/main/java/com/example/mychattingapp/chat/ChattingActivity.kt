package com.example.mychattingapp.chat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.ActivityChattingBinding
import com.example.mychattingapp.dto.ChatMessageDto
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.StompMessage

class ChattingActivity : AppCompatActivity() {

    private val TAG = "ChattingActivity"
    private val URL = "http://000.000.00.000:80/mobileServer/websocket"  // current - ec2
    private val INVITECODE = "testcode"
    private val USERNAME = "kim"
    private val POSITIONTYPE = "1"



    private lateinit var binding : ActivityChattingBinding
    private lateinit var nickName : String
    private lateinit var roomName : String
    private lateinit var roomId : String
    private lateinit var adapter : ChatAdapter
    private lateinit var stompClient : StompClient
    private val viewModel : ChattingViewModel by lazy{
        ViewModelProvider(this).get(ChattingViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting)
        roomName = intent.getStringExtra("roomname")!!
        nickName = intent.getStringExtra("nickname")!!

        setRecycler()
        getRoomId()

        binding.btnSend.setOnClickListener {
            val message = binding.editContent.text.toString()
            Log.e(TAG, roomId.toString())
            sendMessage(message)
        }

    }


    private fun getRoomId(){
        viewModel.roomId.observe(this, Observer {
            if(it!="empty") {
                roomId = it
                trySubscribe()
            }
        })
        viewModel.findRoomByName(roomName!!)
    }


    private fun setRecycler(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ChatAdapter(ArrayList<JSONObject>())
        binding.recyclerView.adapter = adapter

        viewModel.chattingList.observe(this, Observer {
            updateUI(it)
        })


    }


    private fun updateUI(items : ArrayList<JSONObject>){
        adapter = ChatAdapter(items)
        binding.recyclerView.adapter = adapter
    }



    private fun trySubscribe(){
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL)

        connectServer()

        stompClient.topic("/sub/chat/room"+roomId).subscribe{
            val jsonObj = JSONObject(it.payload)
            viewModel.addChat(jsonObj)
        }
    }

    private fun connectServer(){
        /*val headerList = ArrayList<StompHeader>()
        headerList.add(StompHeader("inviteCode", INVITECODE))
        headerList.add(StompHeader("username", USERNAME))
        headerList.add(StompHeader("positionType", POSITIONTYPE))*/
        stompClient.connect()
    }

    private fun sendMessage(message : String){

        val data = JSONObject().apply {
            put("roomId", roomId)
            put("writer", nickName)
            put("message", message)
        }

        stompClient.send("/app/chat/message", data.toString()).subscribe()


    }




    inner class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val partner = itemView.findViewById<TextView>(R.id.item_text_partner_id)
        val partner_content =  itemView.findViewById<TextView>(R.id.item_text_partner_content)
        val mine = itemView.findViewById<TextView>(R.id.item_text_mine)
        val my_content =itemView.findViewById<TextView>(R.id.item_text_content_mine)


        public fun bind(item : JSONObject){
            if(item.getString("writer")==nickName){ //내가 작성한 글
                mine.text = "내가 작성한 글"
                my_content.text = item.getString("message")
            }else{
                partner.text = item.getString("writer").toString()
                partner_content.text = item.getString("message")
            }
        }
    }
    inner class ChatAdapter(val items : ArrayList<JSONObject>) : RecyclerView.Adapter<ChatHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
            val view = layoutInflater.inflate(R.layout.item_chatting_layout, parent, false)
            return ChatHolder(view)
        }

        override fun onBindViewHolder(holder: ChatHolder, position: Int) {
            holder.bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

    }

}