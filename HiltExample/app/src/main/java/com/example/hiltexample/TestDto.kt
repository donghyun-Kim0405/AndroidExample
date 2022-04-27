package com.example.hiltexample

import com.google.gson.annotations.SerializedName

data class TestDto(
    @SerializedName("data") val data : String
)