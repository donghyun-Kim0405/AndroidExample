package com.example.hiltexample

import com.example.hiltexample.hiltBind.LocalDataInterface
import com.example.hiltexample.hiltBind.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitAPI: RetrofitAPI){ // 생성자 주입방식

    @Inject internal lateinit var localDataSource: LocalDataInterface   //field 주입 방식

    public fun getRemoteData(callback : CallbackInterface, data : String){
        retrofitAPI.getTestCall(TestDto(data)).enqueue(object : Callback<TestDto>{
            override fun onResponse(call: Call<TestDto>, response: Response<TestDto>) {
                if(response.body()==null) return
                callback.onSuccess(response.body()!!.data)
            }

            override fun onFailure(call: Call<TestDto>, t: Throwable) {
                callback.onFail()
            }
        })
    }

    public fun getLocalData(callback: CallbackInterface){
        localDataSource.getData(callback)
    }

}