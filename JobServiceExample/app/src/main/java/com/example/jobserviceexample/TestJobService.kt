package com.example.jobserviceexample

import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.concurrent.TimeUnit

class TestJobService : JobService() {

    companion object{
        private const val JOB_ID = 100  //const -> compile타임에 결정
        private val PERIODIC = TimeUnit.MINUTES.toMillis(15) //runtime에 결정

        fun startJobService(context : Context){
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val serviceComponentName = ComponentName(context, TestJobService::class.java)
            val jobInfo = JobInfo.Builder(JOB_ID, serviceComponentName)
                .setPeriodic(PERIODIC)
                .build()
            jobScheduler.schedule(jobInfo)
        }//startJobService



        //singleTon적용하지 않은 경우
        @RequiresApi(Build.VERSION_CODES.N)
        fun stopJobService(context : Context){
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

            if(jobScheduler.getPendingJob(JOB_ID) == null) return

            with(jobScheduler){ cancelAll() }
        }//stopJobService

    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.e("JOB", "...")
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {

        return false
    }


}