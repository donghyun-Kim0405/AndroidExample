package com.example.manualdependecyinjectionexample.container

import com.example.manualdependecyinjectionexample.PostData
import com.example.manualdependecyinjectionexample.repository.PostRepository
import com.example.manualdependecyinjectionexample.factory.PostViewModelFactory

class PostContainer(private val postRepository : PostRepository) {
    val postData = PostData() // 게시글 CRUD에 필요한 정보 생성 (의존성 주입x)
    val postViewModelFactory : PostViewModelFactory = PostViewModelFactory(postRepository)

}