package com.example.stepdetectorexample

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.stepdetectorexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sensorManager: StepSensorManager by lazy { StepSensorManager(this) }

    private val listener = createSensorListener()

    private val PERMISSIONS = arrayOf(Manifest.permission.ACTIVITY_RECOGNITION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermission()
        setBtnListener()

    }//onCreate

    private fun setBtnListener(){
        binding.btnStart.setOnClickListener {
            sensorManager.registerListener(listener)
            sensorManager.startSensor()
        }
        binding.btnTerminate.setOnClickListener {
            sensorManager.unRegisterListener(listener)
            sensorManager.stopSensor()
        }
    }

    private fun createSensorListener() = object: StepSensorManager.Listener{
        override fun onSensorDataChanged() {
            binding.textResult.text = sensorManager.getStepCount().toString()
            Log.e(TAG, sensorManager.getStepCount().toString())
        }
    }//createSensorListener()

    private fun requestPermission() {
        Log.e(TAG, "requestPermission called")
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, PERMISSIONS, 200)   //권한 요청
        }
    }

    companion object{
        private const val TAG = "MainActivity"
    }

}