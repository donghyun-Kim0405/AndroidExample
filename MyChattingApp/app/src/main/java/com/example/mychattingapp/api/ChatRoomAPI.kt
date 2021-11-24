package com.example.mychattingapp.api

import com.example.mychattingapp.dto.ChatRoomDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatRoomAPI {

    @POST("/room/create")
    public fun getCreateRoomCall(@Body chatRoomDto: ChatRoomDto) : Call<ArrayList<ChatRoomDto>>

    @POST("/room/getAllRoom")
    public fun getAllRoomCall(@Body chatRoomDto : ChatRoomDto) : Call<ArrayList<ChatRoomDto>>

    @POST("/room/findRoomIdByName")
    public fun getfindRoomIdByNameCall(@Body chatRoomDto: ChatRoomDto) : Call<ChatRoomDto>

}