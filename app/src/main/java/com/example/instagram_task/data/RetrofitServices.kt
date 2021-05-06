package com.example.instagram_task.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("photos")
    suspend fun getPostsResponse(): Response<List<Post>>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Int): Response<List<Comment>>

}