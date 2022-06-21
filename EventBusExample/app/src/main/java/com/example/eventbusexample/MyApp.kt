package com.example.eventbusexample

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.Toast
import com.example.eventbusexample.event.CallEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 *Application을 상속 받는 클래스는 앱이 실행되는 동안 계속 메모리에 상주하여 이곳에
 * @Subscribe(threadMode = ThreadMode.MAIN) 메서드를 작성시 앱의 어느곳에서든 발생한 이벤트를 처리할 수 있음
 * **/
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if(!EventBus.getDefault().isRegistered(applicationContext)){
            EventBus.getDefault().register(applicationContext)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showEvent(event: CallEvent) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
    }
}