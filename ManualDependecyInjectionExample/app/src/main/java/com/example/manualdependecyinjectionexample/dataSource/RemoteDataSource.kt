package com.example.manualdependecyinjectionexample.dataSource

import androidx.lifecycle.MutableLiveData

class RemoteDataSource {
    private var data : String = "Remote data"
    public fun getRemoteData(mutableLiveData: MutableLiveData<String>){
        mutableLiveData.value = data    // viewModel의 mutableLiveData
    }
}