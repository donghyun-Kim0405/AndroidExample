package com.example.eventbusexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.eventbusexample.databinding.ActivityMainBinding
import com.example.eventbusexample.event.CallEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * check dependency for EventBus
 * check manifest for MyApp
 *
 * btnEvent
 * -> Toast Message from MyApp
 * -> Log from MainActivity
 * **/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        binding.btnEvent.setOnClickListener {
            EventBus.getDefault().post(CallEvent("message From MainActivity"))
        }
    }//onCreate()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun printMessage(event: CallEvent) {
        Log.e(TAG, event.message)
    }
    override fun onResume() {
        super.onResume()
        /** this는 fragment & activity 둘다 될 수 있다. **/
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
    }
    override fun onPause() {
        super.onPause()
        /** **/
        if(EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
    }
    companion object{
        private const val TAG = "MainActivity"
    }

}