package com.example.retrofittester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.retrofittester.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL:String = "http://172.30.1.23:8080"
    private val TOKEN : String = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb25naHllb24iLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjQwNDkwMDIzfQ.7hSsWs8TvURAqG4-_hEqd_WVskM-a7ZfUnqmEA_nupqka7VtcZGAy_Jw0ca4EUXZOEg9lsg-rBDEJM71hBR3xg"

    private lateinit var binding : ActivityMainBinding


    //====================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnApiTest.setOnClickListener {
            sendToAPIServer()
        }

        binding.btnTokenTest.setOnClickListener {
            sendToTokenServer(createHttpClient())
        }

    }

    //====================================================================================================
    private fun createInterceptor(): Interceptor {
        return Interceptor{ chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $TOKEN")
                .build()
            val response = chain.proceed(newRequest)
            response.newBuilder().build()
        }
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(createInterceptor())
            .build()
    }



    //====================================================================================================

    private fun createGson(): Gson? {
        return GsonBuilder()
            .setLenient()
            .create()
    }


    private fun sendToAPIServer(){

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()
        val myAPI = retrofit.create(RetrofitAPI::class.java)

        val call = myAPI.getPostCall(PostData(binding.editSend.text.toString()))
        call.enqueue(object :Callback<PostData>{
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                binding.textResult.text = response.body()?.message
                Log.e("MainActivity", response.body().toString())
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Log.e("MainActivity", t.message.toString())
            }
        })
    }

    //====================================================================================================

    private fun sendToTokenServer(client : OkHttpClient){

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .client(client)
            .build()
        val myAPI = retrofit.create(RetrofitAPI::class.java)


        val call = myAPI.getTokenTestCall(AndroidTestDto(binding.editSend.text.toString()))
        call.enqueue(object : Callback<AndroidTestDto>{
            override fun onResponse(
                call: Call<AndroidTestDto>,
                response: Response<AndroidTestDto>
            ) {
                binding.textResult.text = response.body()?.message
                Log.e("MainActivity", response.body().toString())
            }

            override fun onFailure(call: Call<AndroidTestDto>, t: Throwable) {
                Log.e("MainActivity", t.message.toString())
            }

        })
    }

}