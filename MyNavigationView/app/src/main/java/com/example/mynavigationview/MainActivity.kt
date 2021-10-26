package com.example.mynavigationview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var context:Context
    private lateinit var toolBar : Toolbar
    private lateinit var actionBar : ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        actionBar = supportActionBar!!
        actionBar.setDisplayShowCustomEnabled(false)    //기존의 title지우기
        actionBar.setDisplayHomeAsUpEnabled(true)   //뒤로가기 버튼이 생기게 하기 위해 True설정
        actionBar.setHomeAsUpIndicator(R.drawable.icon_nav_menu)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home ->{   //home키 - toolbar에 추가된 외쪽 상단의 버튼을 의미 따로 id를 지정하지 않음
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}