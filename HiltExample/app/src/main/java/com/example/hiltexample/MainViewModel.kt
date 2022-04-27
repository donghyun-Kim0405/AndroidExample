package com.example.hiltexample

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.annotation.Inherited
import javax.inject.Inject


class MainViewModel @Inject constructor(): ViewModel(){
    @Inject internal lateinit var repository : MainRepository

    public fun getRemoteData(callback : CallbackInterface, data : String) = repository.getRemoteData(callback, data)

    public fun getLocalData(callback : CallbackInterface) = repository.getLocalData(callback)
}