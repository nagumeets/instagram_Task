package com.example.instagram_task.posts

import com.example.instagram_task.data.Post

sealed class PostsState {
    object Loading : PostsState()
    data class Error(val message: String?) : PostsState()
    data class PostsLoaded(val posts: List<Post>) : PostsState()
}