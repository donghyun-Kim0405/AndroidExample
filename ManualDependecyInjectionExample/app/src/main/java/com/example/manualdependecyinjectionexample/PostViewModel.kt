package com.example.manualdependecyinjectionexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.manualdependecyinjectionexample.repository.PostRepository

class PostViewModel(postRepository: PostRepository) : ViewModel(){
    private val postRepository  = postRepository
    public var mutableLiveData : MutableLiveData<String> = MutableLiveData()

    public fun getLocalData(){
        postRepository.getLocalData(mutableLiveData)
    }//LocalDataSource로 부터 데이터 얻기

    public fun getRemoteData(){
        postRepository.getRemoteData(mutableLiveData)
    }//RemoteDataSource로 부터 데이터 얻기


}