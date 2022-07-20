package com.example.stepdetectorexample

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService


/**
 *
 * <옵션 사항>
 * SENSOR_DELAY - microsecond delay
 * NORMAL = 200,000
 * UI = 60,000
 * GAME = 20,000
 * FASTEST = 0
 *
 * <사용방법>
 *     1. class생성시 context주입
 *     2. registerListener
 *     3. startSensor
 *     4. unRegisterListener
 *     5. stopSensor
 *
 * <고려사항>
 * stepCount 초기화 시점 고려
 * **/
class StepSensorManager(val context: Context) : SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    private var listeners: ArrayList<Listener>

    private var isSensorWorking: Boolean = false
    private var stepCount: Int = 0


    init {
        getStepDetectorSensor()
        listeners = ArrayList()
    }

    //method - called external class
    public fun startSensor(){
        if(!isSensorWorking){   //sensor동작중이지않을 때
            sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
            isSensorWorking = true
        }
    }

    public fun stopSensor(){
        if(isSensorWorking){    //sensor동작 중일 때
            sensorManager!!.unregisterListener(this)
            isSensorWorking = false
            stepCount = 0
        }
    }

    public fun getStepCount() = stepCount

    //새로 추가할 리스너가 기존의 리스너 목록에 등록되어 있지 않은 경우 추가
    public fun registerListener(listener: Listener) { if(!listeners.contains(listener)) listeners.add(listener) }

    //리스너 목록에서 리스너를 제거
    public fun unRegisterListener(listener: Listener){ listeners.remove(listener) }

    //===========================================================================================================================


    //method - called internal class
    private fun getStepDetectorSensor(){
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    }//getStepDetectorSensor()

    //리스너에게 센서 값이 변경되었음을 알림
    private fun notifyListener(){ for (listener in listeners) listener.onSensorDataChanged() }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_STEP_DETECTOR){
            if(event.values[0] == 1.0f) stepCount += 1
            notifyListener()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    interface Listener{
        fun onSensorDataChanged()
    }

    companion object{
        private const val TAG = "StepSensorManager"
    }
}