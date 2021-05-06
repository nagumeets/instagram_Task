package com.example.instagram_task.comments

import com.example.instagram_task.data.Comment
import com.example.instagram_task.data.Post

sealed class CommentsState {
    object Loading : CommentsState()
    data class Error(val message: String?) : CommentsState()
    data class PostsLoaded(val comments: List<Comment>) : CommentsState()
}