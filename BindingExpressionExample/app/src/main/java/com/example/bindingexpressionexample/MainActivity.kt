package com.example.bindingexpressionexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bindingexpressionexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.user = createUser() //MainActivity layout의 data -> variable 의 user 에 User클래스 할당

        binding.btnRecycler.setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))   //recyclerView Activity로 이동
        }

    }//onCreate()

    private fun createUser() = User("test", "12")

}