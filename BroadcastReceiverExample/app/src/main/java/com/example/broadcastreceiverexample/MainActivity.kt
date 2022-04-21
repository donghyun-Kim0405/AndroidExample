package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.broadcastreceiverexample.databinding.ActivityMainBinding
import kotlinx.coroutines.channels.BroadcastChannel


/*
onResume() -> 브로드캐스트 등록
onPause() -> 브로드캐스트 해제
btnListener -> sendBroadCast()

변수 중 BROADCAST_ACTION_PLUS == Intent.ACTION에 해당
 */


class MainActivity : AppCompatActivity() {

    private val BROADCAST_ACTION_PLUS : String = "com.example.broadcastreceiverexample.plus" //broadcast 식별 flag
    private val BROADCAST_ACTION_MINUS : String = "com.example.broadcastreceiverexample.minus" //broadcast 식별 flag
    private lateinit var binding : ActivityMainBinding
    private var broadcastReceiver: BroadcastReceiver? = null
    private var number : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlus.setOnClickListener {
            val intent : Intent = Intent(BROADCAST_ACTION_PLUS)
            intent.putExtra("data", number)
            sendBroadcast(intent)
        }

        binding.btnMinus.setOnClickListener {
            val intent : Intent = Intent(BROADCAST_ACTION_MINUS)
            intent.putExtra("data", number)
            sendBroadcast(intent)
        }

    }//onCreate()

    override fun onResume() {
        super.onResume()
        registerBroadcastReceiver()
    }//onResume()

    override fun onPause() {
        super.onPause()
        unRegisterBroadCastReceiver()
    }//onPause()
    //lifeCycle
    //------------------------------------------------------------------
    private fun registerBroadcastReceiver(){

     /*   1. Intent filter생성
        2. intent filter에 action추가
        3. BroadCastReceiver를 익명클래스로 구현
        4. intent filter와 BroadCastReceiver등록*/

        if(broadcastReceiver != null) return    //이미 브로드 캐스트 객체 존재 -> 리턴

        broadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                var receivedData : Int = p1!!.getIntExtra("data", 0)    //받은 데이터

                if(p1.action.equals(BROADCAST_ACTION_PLUS)) {
                    number = receivedData+1
                    Log.e("RECEVED", number.toString())

                }else if(p1.action.equals(BROADCAST_ACTION_MINUS)){
                    number = receivedData-1
                    Log.e("RECEVED", number.toString())
                }
            }//onReceive() -> receive발생시 동작 구현
        }//object : BroadcastReceiver()

        this.registerReceiver(this.broadcastReceiver, createIntentFilter()) //receiver와 intentFilter등록
    }//registerReceiver()

    private fun createIntentFilter() : IntentFilter {
        return IntentFilter().apply {
            addAction(BROADCAST_ACTION_PLUS)
            addAction(BROADCAST_ACTION_MINUS)
        }
    }   //createIntentFilater

    private fun unRegisterBroadCastReceiver(){
        if (broadcastReceiver != null) this.unregisterReceiver(broadcastReceiver)
        broadcastReceiver = null
    }//unRegisterReceiver()



}