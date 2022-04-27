package com.example.hiltexample.qualifier

import android.util.Log
import javax.inject.Inject

class TestB @Inject constructor():TestInterface{
    override fun printLog() {
        Log.e("TEST B", "called!")
    }
}