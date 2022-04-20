package com.example.handlerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.handlerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainHandler : Handler
    private lateinit var testThread : Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTest.setOnClickListener {
            testThread.start()
        }

        testThread = object : Thread(){
            override fun run() {
                super.run()
                val msg = Message()
                msg.what = 1
                mainHandler.sendMessage(msg)
            }
        }// Thread 에서 Handler로 작업 요청


        mainHandler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                binding.btnTest.text = msg.what.toString()
            }
        }//testThread에서 요청한 message받아 수행





    }//onCreate()





}