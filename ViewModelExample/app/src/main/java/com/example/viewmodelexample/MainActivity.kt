package com.example.viewmodelexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelexample.databinding.ActivityMainBinding
import com.example.viewmodelexample.instanceFactory.InstanceFactoryActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }//mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetData.setOnClickListener {
            binding.textView.text = mainViewModel.getData()
        }

        binding.btnMove.setOnClickListener {
            startActivity(Intent(this, InstanceFactoryActivity::class.java))
        }

    }//onCreate()
}