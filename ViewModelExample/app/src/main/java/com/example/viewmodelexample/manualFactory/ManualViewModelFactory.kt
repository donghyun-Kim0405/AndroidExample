package com.example.viewmodelexample.manualFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ManualViewModelFactory(private val str : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ManualFactoryViewModel::class.java)){
            ManualFactoryViewModel(str) as T
        }else{
            throw IllegalArgumentException()
        }
    }
}