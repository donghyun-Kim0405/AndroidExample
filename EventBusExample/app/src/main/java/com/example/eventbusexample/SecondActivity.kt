package com.example.eventbusexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.eventbusexample.databinding.ActivitySecondBinding
import com.example.eventbusexample.event.CallEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * btnTest
 * -> Toast Message from MyAPp
 * -> Log from TestUtil
 *
 * **/

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var testUtil: TestUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testUtil = TestUtil(this)


        binding.btnTest.setOnClickListener {
            EventBus.getDefault().post(CallEvent("called by second activity"))
        }

    }//onCreat()

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun printMessage(event: CallEvent) {
        Log.e(TAG, event.message)
    }


    companion object{
        private const val TAG = "SecondActivity"
    }




}