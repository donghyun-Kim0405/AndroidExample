package com.example.viewmodelexample.manualFactory

import androidx.lifecycle.ViewModel

class ManualFactoryViewModel(val str : String) : ViewModel() {
    public fun getData() = str
}