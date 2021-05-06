package com.example.instagram_task.data

/*
  {"albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
  },
 */
data class Post(val albumId: Int, val id: Int, val title: String, val url: String,val thumbnailUr:String)

/*
  {
    "postId": 1,
    "id": 1,
    "name": "id labore ex et quam laborum",
    "email": "Eliseo@gardner.biz",
    "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
  }
 */
data class Comment(val postId: Int, val id: Int, val name: String, val email: String, val body: String)