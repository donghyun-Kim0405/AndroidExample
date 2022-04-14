package com.example.manualdependecyinjectionexample.dataSource

import androidx.lifecycle.MutableLiveData

class LocalDataSource {

    private var data : String = "local data"
    public fun getLocalData(mutableLiveData: MutableLiveData<String>){
        mutableLiveData.value = data    // viewModel의 mutableLiveData
    }
}