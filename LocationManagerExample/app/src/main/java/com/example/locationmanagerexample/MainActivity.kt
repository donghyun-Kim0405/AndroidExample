package com.example.locationmanagerexample

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.locationmanagerexample.databinding.ActivityMainBinding

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private val PERMISSIONS=arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.INTERNET,android.Manifest.permission.ACCESS_COARSE_LOCATION)
    lateinit var locationManager: LocationManager
    var lastLocation : Location?=null
    lateinit var providerList : List<String>
    var location : Location? = null



    val myListener : LocationListener = object : LocationListener{
        override fun onLocationChanged(loc: Location) {
            updateUI(loc)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        requestPermission()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        binding.btnGetLocation.setOnClickListener {
            getLastLocation()
        }
        binding.btnUpdateLocation.setOnClickListener {
            getCurrentLocation()
        }


    }

    private fun getLastLocation(){
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }

        if(lastLocation==null){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f, myListener)
        }else{
            updateUI(lastLocation!!)
        }
    }
    private fun getCurrentLocation(){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f, myListener)
    }


    private fun updateUI(location:Location){
        binding.textLat.text = location.latitude.toString()
        binding.textLong.text = location.longitude.toString()
    }

    private fun requestPermission(){
        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED||
            checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                PERMISSIONS, 100)}
    }
}