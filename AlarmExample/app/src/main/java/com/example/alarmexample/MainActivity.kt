package com.example.alarmexample

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.alarmexample.databinding.ActivityMainBinding
import java.util.*



/*
button 클릭시 5초뒤 AlarmActivity 띄우기
AlarmReceiver 내부 notification 생성시 현재 app process가 메모리상 존재 하지 않아도 notification수행
Manifest 내 receiver 작성해야 동작
 */

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 16)
        calendar.set(Calendar.MINUTE, 42)
        calendar.set(Calendar.SECOND, 5)


        binding.btnSet.setOnClickListener {

            val receiverIntent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)   // need to care request Code
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager

            alarmManager?.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+5000, pendingIntent)

        }
    }
}