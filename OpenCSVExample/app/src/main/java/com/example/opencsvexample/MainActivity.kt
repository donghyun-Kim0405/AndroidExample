package com.example.opencsvexample

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.opencsvexample.databinding.ActivityMainBinding
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.IOException


@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    private val PERMISSIONS=arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private lateinit var binding : ActivityMainBinding
    private lateinit var defaultPath:String
    private lateinit var csvManager:CSVManager

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        requestPermission()

        val status = Environment.getExternalStorageState()  // 외부 저장소가 마운트 되어있는지 상태 확인
        val externalStatus =Environment.isExternalStorageLegacy() // 현재 앱의 저장소 모드가 외부 저장소 모드인지 앱 내부 저장소 모드 인지 체크

        defaultPath = filesDir.toString()
        csvManager = CSVManager(defaultPath)
        Log.e("MAINACTIVITY", defaultPath.toString())


        binding.btnStore.setOnClickListener {
            val dataList1 = arrayListOf<Array<String>>() // csvManager 에 write할때 parameter로 넘겨줄 source
            //헤더로 사용할 데이터 추가
            dataList1.add(arrayOf("Name", "Age"))
            //데이터 추가
            dataList1.add(arrayOf("Kim", "20"))
            dataList1.add(arrayOf("Lee", "21"))
            dataList1.add(arrayOf("Park", "22"))
            csvManager.writeCSV("testFile", dataList1)
        }
        binding.btnRead.setOnClickListener {
            csvManager.readEach("testFile")
        }
    }
    private fun requestPermission(){
        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                PERMISSIONS, 100)}
    }
}