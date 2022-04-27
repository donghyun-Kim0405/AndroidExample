package com.example.hiltexample

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitAPI {
    @POST("api/test/android")
    public fun getTestCall(@Body data: TestDto) : Call<TestDto>

}