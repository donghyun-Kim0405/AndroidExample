package com.example.applicationclassexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applicationclassexample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondBinding
    private lateinit var customApplication : CustomApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customApplication = application as CustomApplication


        binding.btnSetMessage.setOnClickListener {
            var message : String = binding.editText.text.toString()
            customApplication.setMessage(message)
        }

        binding.btnShowMessage.setOnClickListener {
            customApplication.showMessage()
        }

        binding.btnMove.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }



    }//onCreate()


}