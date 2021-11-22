package com.example.retrofittester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.retrofittester.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL:String = ""



    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val myAPI = retrofit.create(RetrofitAPI::class.java)
        binding.btnSend.setOnClickListener {
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



    }
}