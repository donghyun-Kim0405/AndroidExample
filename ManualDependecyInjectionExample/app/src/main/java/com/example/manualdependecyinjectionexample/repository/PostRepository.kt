package com.example.manualdependecyinjectionexample.repository

import androidx.lifecycle.MutableLiveData
import com.example.manualdependecyinjectionexample.dataSource.LocalDataSource
import com.example.manualdependecyinjectionexample.dataSource.RemoteDataSource


/*
PostContainer 에서 의존성 주입

생성된 PostRepository는 특정 흐름컨테이너로 전달
 */


class PostRepository(localDataSource : LocalDataSource, remoteDataSource: RemoteDataSource) {

    private val localDataSource : LocalDataSource = localDataSource //container에서 의존성 주입
    private val remoteDataSource : RemoteDataSource = remoteDataSource  //contanser에서 의존성 주입

    public fun getLocalData(mutableLiveData : MutableLiveData<String>) {
        localDataSource.getLocalData(mutableLiveData)
    }

    public fun getRemoteData(mutableLiveData: MutableLiveData<String>) {
        remoteDataSource.getRemoteData(mutableLiveData)
    }

}