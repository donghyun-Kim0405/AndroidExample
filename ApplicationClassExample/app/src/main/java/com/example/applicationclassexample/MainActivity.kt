package com.example.applicationclassexample

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applicationclassexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var customApplication: CustomApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customApplication = application as CustomApplication    //내가 선언한 customApplication 얻기

        binding.btnSetMessage.setOnClickListener {
            var message :String = binding.editText.text.toString()
            customApplication.setMessage(message)
        }//btnSetId

        binding.btnShowMessage.setOnClickListener {
            customApplication.showMessage()
        }

        binding.btnMove.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }


    }

}