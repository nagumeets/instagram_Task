package com.example.instagram_task.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aghajari.zoomhelper.ZoomHelper
import com.example.instagram_task.R
import com.example.instagram_task.data.Post
import com.example.instagram_task.loadImage
import kotlinx.android.synthetic.main.list_item_post.view.*


class PostAdapter(
    private var listOfPosts: List<Post>,
    private val postClickListener: OnPostClickListener
) :
    RecyclerView.Adapter<PostViewHolder>(), ZoomHelper.OnZoomStateChangedListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfPosts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = listOfPosts[position]
        holder.bindView(post)
        holder.itemView.setOnClickListener {
            postClickListener.postClicked(post)
        }

    }

    fun updatePosts(posts: List<Post>) {
        listOfPosts = posts
        notifyDataSetChanged()
    }

    override fun onZoomStateChanged(
        zoomHelper: ZoomHelper,
        zoomableView: View,
        isZooming: Boolean
    ) {
        if (isZooming){
            val model: Post = ZoomHelper.getZoomableViewTag(zoomableView) as Post
            Toast.makeText(zoomableView.context,model.title+"'s post started zooming!",Toast.LENGTH_SHORT).show()
        }
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(post: Post) {
        itemView.apply {
            tv_message.text = post.title
            iv_image.loadImage(post.url)
        }
    }
}

interface OnPostClickListener {
    fun postClicked(post: Post)
}
