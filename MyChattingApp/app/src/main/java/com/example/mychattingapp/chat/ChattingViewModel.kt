package com.example.mychattingapp.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.ChatRepository
import org.json.JSONObject

class ChattingViewModel : ViewModel(){
    private val chatRepository : ChatRepository? = ChatRepository.getInstance()
    public var roomId : MutableLiveData<String> = MutableLiveData()
    public var chattingList : MutableLiveData<ArrayList<JSONObject>> = MutableLiveData()

    init {
        roomId.value = "empty"
        chattingList.value = ArrayList<JSONObject>()
    }

    public fun findRoomByName(roomName : String){
        chatRepository?.findRoomByName(roomName, roomId)
    }

    public fun addChat(jsonObject: JSONObject){
        var temp = chattingList.value
        temp!!.add(jsonObject)
        chattingList.postValue(temp)
    }

}