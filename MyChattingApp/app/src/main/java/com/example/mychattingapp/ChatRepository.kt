package com.example.mychattingapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mychattingapp.api.ChatRoomAPI
import com.example.mychattingapp.dto.ChatRoomDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatRepository private constructor(context: Context){
    companion object{
        private var INSTANCE: ChatRepository?=null
        fun init(context: Context){
            if(INSTANCE ==null){ INSTANCE = ChatRepository(context) }
        }
        fun getInstance() : ChatRepository?{  //ViewModel에서 객체를 얻기위해 호출
            return INSTANCE ?:
            throw Exception("Repository sould be initialized")
        }
    }

    private val BASE_URL:String = "http://000.000.00.000:80"
    private var retrofit : Retrofit? = null
    private val TAG = "ChatRepository"

    public fun getRoomList(nickName : String, roomList : MutableLiveData<ArrayList<ChatRoomDto>>){
        buildRetrofit()
        val roomAPI = retrofit!!.create(ChatRoomAPI::class.java)
        val call = roomAPI.getAllRoomCall(ChatRoomDto(nickName))
        call.enqueue(object : Callback<ArrayList<ChatRoomDto>>{
            override fun onResponse(
                call: Call<ArrayList<ChatRoomDto>>,
                response: Response<ArrayList<ChatRoomDto>>
            ) {
                roomList.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<ChatRoomDto>>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })
    }

    public fun createRoom(roomName: String, roomList: MutableLiveData<ArrayList<ChatRoomDto>>) {
        buildRetrofit()
        val roomAPI = retrofit!!.create(ChatRoomAPI::class.java)
        val call = roomAPI.getCreateRoomCall(ChatRoomDto(name = roomName))
        call.enqueue(object : Callback<ArrayList<ChatRoomDto>>{
            override fun onResponse(
                call: Call<ArrayList<ChatRoomDto>>,
                response: Response<ArrayList<ChatRoomDto>>
            ) {
                Log.e("test!!", response.body().toString())
                roomList.value = response.body()
            }
            override fun onFailure(call: Call<ArrayList<ChatRoomDto>>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
    public fun findRoomByName(roomName: String, roomId : MutableLiveData<String>) {
        buildRetrofit()
        val roomAPI = retrofit!!.create(ChatRoomAPI::class.java)
        val call = roomAPI.getfindRoomIdByNameCall(ChatRoomDto(name=roomName))
        call.enqueue(object : Callback<ChatRoomDto>{
            override fun onResponse(call: Call<ChatRoomDto>, response: Response<ChatRoomDto>) {
                roomId.value = response.body()?.roomId
            }

            override fun onFailure(call: Call<ChatRoomDto>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })

    }

    private fun buildRetrofit(){
        if(retrofit==null){
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }


}