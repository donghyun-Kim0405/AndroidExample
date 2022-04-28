package com.example.viewmodelexample.manualFactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelexample.MainActivity
import com.example.viewmodelexample.R
import com.example.viewmodelexample.databinding.ActivityManualFactoryBinding

class ManualFactoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityManualFactoryBinding
    private val parameter : String = "manual factory activity param"

    private val viewModel : ManualFactoryViewModel by lazy {
        ViewModelProvider(this, ManualViewModelFactory(parameter))
            .get(ManualFactoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualFactoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetData.setOnClickListener {
            binding.textView.text = viewModel.getData()
        }

        binding.btnMove.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}