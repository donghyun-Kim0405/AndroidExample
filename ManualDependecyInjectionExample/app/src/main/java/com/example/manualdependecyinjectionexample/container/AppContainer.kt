package com.example.manualdependecyinjectionexample.container

import com.example.manualdependecyinjectionexample.dataSource.LocalDataSource
import com.example.manualdependecyinjectionexample.repository.PostRepository
import com.example.manualdependecyinjectionexample.dataSource.RemoteDataSource

class AppContainer {

    // appContainer 내에서 인스턴스 생성
    private val localDataSource = LocalDataSource()
    private val remoteDataSource = RemoteDataSource()
    public val postRepository : PostRepository = PostRepository(localDataSource, remoteDataSource)
    // appContainer 내에서 인스턴스 생성

    //=============================================================================================

    //흐름 컨테이너
    var postContainer : PostContainer? = null
    //흐름 컨테이너


}