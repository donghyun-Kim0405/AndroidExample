package com.example.myviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    lateinit var viewPager : ViewPager
    lateinit var myViewPagerAdapter: MyViewPagerAdapter
    lateinit var items : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        items = ArrayList<String>()
        for(i in 0..10){
            items.add(i.toString())
        }
        myViewPagerAdapter = MyViewPagerAdapter(this, items)
        viewPager.adapter = myViewPagerAdapter
        
    }
}