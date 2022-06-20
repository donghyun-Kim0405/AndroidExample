package com.example.baseactivityexample

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.baseactivityexample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, TestFragment()).commit()

        binding.btnActivity.setOnClickListener {
            showMessage("Activity")
        }
    }
}