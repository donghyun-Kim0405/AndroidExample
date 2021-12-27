package com.example.districtparserexample

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.districtparserexample.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    private val PERMISSIONS=arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private lateinit var binding : ActivityMainBinding


    private lateinit var spinnerDataManager: SpinnerDataManager

    private var selected_si:String? = null
    private var selected_gu:String? = null
    private var selected_dong:String? = null



    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        requestPermission()
        spinnerDataManager = SpinnerDataManager(this)
        createSiAdapter(spinnerDataManager.findSiDatas())


    }//onCreate

    private fun createDongAdapter(dongDatas: List<String>) {
        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dongDatas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDong.adapter = adapter
        binding.spinnerDong.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected_dong = dongDatas.get(p2)
                binding.textResult.text = selected_si+selected_gu+selected_dong
            }
        }
    }//createGuAdatper


    private fun createGuAdapter(guDatas: List<String>) {
        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, guDatas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGu.adapter = adapter
        binding.spinnerGu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected_gu = guDatas.get(p2)
                createDongAdapter(spinnerDataManager.findDong(selected_si!!, selected_gu!!))
            }
        }
    }//createGuAdatper



    private fun createSiAdapter(siDatas : List<String>){

        for(row in siDatas){
            Log.e("siData", row)
        }

        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siDatas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSi.adapter = adapter
        binding.spinnerSi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected_si = siDatas.get(p2)
                createGuAdapter(spinnerDataManager.findGuDatas(selected_si!!))
            }
        }

    }// createCityAdapter

    private fun requestPermission(){
        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                PERMISSIONS, 100)}
    }//requestPermission

}