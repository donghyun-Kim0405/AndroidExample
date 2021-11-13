package com.example.statusviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.statusviewexample.databinding.ActivityMainBinding
import iammert.com.library.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnLoading.setOnClickListener {
            binding.status.setStatus(Status.LOADING)
            binding.status.loadingView
        }
        binding.btnError.setOnClickListener {
            binding.status.setStatus(Status.ERROR)
        }
        binding.btnStop.setOnClickListener {
            binding.status.setStatus(Status.COMPLETE)
        }
    }
}