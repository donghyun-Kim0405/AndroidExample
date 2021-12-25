package com.example.retrofittester

import com.google.gson.annotations.SerializedName




data class AndroidTestDto(
    @SerializedName("message") val message:String
)

