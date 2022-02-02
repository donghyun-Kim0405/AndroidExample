package com.example.glideexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.glideexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.btnLoad.setOnClickListener {

            Toast.makeText(applicationContext, "load", Toast.LENGTH_SHORT).show()

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w92/dRLSoufWtc16F5fliK4ECIVs56p.jpg")    //이미지를 로드할 URL
                .error(R.drawable.ic_baseline_error_outline_24) //image laod에 실패 했을경우 보여줄 이미지
                .into(binding.imageView)    // 로드한 이미지를 띄울 뷰
        }

    }// onCreate()
}