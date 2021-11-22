package com.example.retrofittester

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("/test/android")
    public fun getPostCall(@Body data: PostData) : Call<PostData>

}