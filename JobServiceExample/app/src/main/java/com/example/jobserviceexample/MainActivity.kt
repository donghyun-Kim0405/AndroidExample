package com.example.jobserviceexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jobserviceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            TestJobService.startJobService(this)
        }//jobservice시작
        binding.btnStop.setOnClickListener {
            TestJobService.startJobService(this)
        }//jobservice정지


    }//onCreate()
}