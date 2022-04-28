package com.example.viewmodelexample.instanceFactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelexample.R
import com.example.viewmodelexample.databinding.ActivityInstanceFactoryBinding
import com.example.viewmodelexample.manualFactory.ManualFactoryActivity


class InstanceFactoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInstanceFactoryBinding

    //lifecycle - extensions 모듈이 필요하지 않은 방법
    private val viewModel : InstanceFactoryViewModel by lazy{
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(InstanceFactoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstanceFactoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetData.setOnClickListener {
            binding.textView.text = viewModel.getData()
        }

        binding.btnMove.setOnClickListener {
            startActivity(Intent(this, ManualFactoryActivity::class.java))
        }

    }//onCreate()


}