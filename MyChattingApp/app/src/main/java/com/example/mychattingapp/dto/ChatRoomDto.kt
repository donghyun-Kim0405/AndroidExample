package com.example.mychattingapp.dto

import com.google.gson.annotations.SerializedName


//data class PostData(@SerializedName("message") val message:String)


data class ChatRoomDto(
    @SerializedName("roomId") val roomId : String="",
    @SerializedName("name") val name : String=""
)