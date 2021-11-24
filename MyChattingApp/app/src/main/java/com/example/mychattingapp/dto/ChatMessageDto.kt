package com.example.mychattingapp.dto

import com.google.gson.annotations.SerializedName

data class ChatMessageDto(
    @SerializedName("roomId") val roomId : String,
    @SerializedName("writer") val writer : String,
    @SerializedName("message") val message : String
)


