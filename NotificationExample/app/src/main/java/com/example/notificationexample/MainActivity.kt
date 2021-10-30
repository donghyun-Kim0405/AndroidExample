package com.example.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.example.notificationexample.databinding.ActivityMainBinding

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    companion object {
        const val TAG = "AlarmReceiver"
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    lateinit var notificationManager : NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.btnTest.setOnClickListener {
            createNotificationChannel()
            deliverNotification(this)
        }
    }

    private fun createNotificationChannel(){
        val notificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID, "test noti", NotificationManager.IMPORTANCE_HIGH).apply {
            enableLights(true)
            lightColor= Color.RED
            enableVibration(true)
            description="test!!"
        }//notification channel 생성
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun deliverNotification(context : Context){
        val contentIntent = Intent(context, ResultActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID).apply {
            setSmallIcon(R.drawable.icon_clock)
            setContentTitle("TestApp")
            setContentText("this is alarm test")
            setContentIntent(pendingIntent)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setAutoCancel(true)
            setDefaults(NotificationCompat.DEFAULT_ALL)
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

}