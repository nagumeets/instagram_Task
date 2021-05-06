package com.example.instagram_task.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_task.R
import com.example.instagram_task.data.Comment
import kotlinx.android.synthetic.main.list_item_comments.view.*

class CommentsAdapter(private var listOfComments: List<Comment>) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_comments, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfComments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindView(listOfComments[position])
    }

    fun updateComments(comments: List<Comment>) {
        listOfComments = comments
        notifyDataSetChanged()
    }
}

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(comment: Comment) {
        itemView.apply {
            tv_name.text = comment.name
            tv_comments.text = comment.body
        }
    }
}
