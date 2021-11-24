package com.example.mychattingapp.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.ChatRepository
import com.example.mychattingapp.dto.ChatRoomDto

class RoomListViewModel : ViewModel(){

    private val chatRepository : ChatRepository? = ChatRepository.getInstance()
    public var roomList : MutableLiveData<ArrayList<ChatRoomDto>> = MutableLiveData()

    public fun getRoomList(nickName:String){
        chatRepository?.getRoomList(nickName, roomList)
    }

    public fun createRoom(roomName: String) {
        chatRepository?.createRoom(roomName, roomList)
    }

}