package com.example.mychattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mychattingapp.chat.RoomListActivity
import com.example.mychattingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ChatRepository.init(this)

        binding.btnEnter.setOnClickListener {
            val nickName : String = binding.editNickname.text.toString()
            val intent = Intent(this, RoomListActivity::class.java).apply {
                putExtra("nickname", nickName)
            }
            startActivity(intent)
        }
    }
}