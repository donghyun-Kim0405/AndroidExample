package com.example.manualdependecyinjectionexample.factory

import com.example.manualdependecyinjectionexample.Factory
import com.example.manualdependecyinjectionexample.repository.PostRepository
import com.example.manualdependecyinjectionexample.PostViewModel

class PostViewModelFactory(private val postRepository : PostRepository) : Factory<PostViewModel> {

    override fun create(): PostViewModel {
        return PostViewModel(postRepository)
    }
}