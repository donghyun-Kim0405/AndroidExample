package com.example.hiltexample.qualifier

import android.util.Log
import javax.inject.Inject

class TestA @Inject constructor(): TestInterface {
    override fun printLog() {
        Log.e("TEST A", "called!")
    }
}