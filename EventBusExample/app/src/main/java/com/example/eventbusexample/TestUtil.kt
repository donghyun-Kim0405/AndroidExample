package com.example.eventbusexample

import android.content.Context
import android.util.Log
import com.example.eventbusexample.event.CallEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TestUtil(val context: Context) {
    init {
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun printMessage(event: CallEvent) {
        Log.e(TAG, event.message)
    }

    companion object{
        private const val TAG = "TestUtil"
    }
}