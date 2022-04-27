package com.example.hiltexample.hiltBind

import com.example.hiltexample.CallbackInterface
import javax.inject.Inject
import javax.inject.Singleton

class LocalDataSource @Inject constructor() : LocalDataInterface {

    override fun getData(callbackInterface: CallbackInterface) {
        callbackInterface.onSuccess("data from local environment")
    }//getData()
}



