package com.example.alarmexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


@RequiresApi(Build.VERSION_CODES.O)
class AlarmReceiver : BroadcastReceiver(){

    lateinit var notificationManager : NotificationManager
    override fun onReceive(context: Context?, intent: Intent?) {
        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val alarmIntent = Intent(context, AlarmActivity::class.java).apply {
            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context?.startActivity(alarmIntent)
    }

}