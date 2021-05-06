package com.example.instagram_task.posts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram_task.R
import com.example.instagram_task.data.Post
import com.example.instagram_task.utils.MainThreadScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PostsFragment : Fragment(), OnPostClickListener {


    private val uiScope = MainThreadScope()
    private var postsViewModel: PostsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(uiScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
            title = getString(R.string.posts)
            show()
        }

        val postAdapter = PostAdapter(emptyList(), this)
        recyclerPost?.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = postAdapter
        }

        postsViewModel = getViewModel<PostsViewModel>()
        postsViewModel?.postLiveData?.observe(requireActivity(), Observer { postState ->
            if (postState == null) {
                return@Observer
            }

            when (postState) {
                is PostsState.Loading -> {
                }

                is PostsState.Error -> {
                    context?.let {
                        val message = postState.message ?: getString(R.string.error)
                        Snackbar.make(requireActivity().rootLayout, message, Snackbar.LENGTH_LONG).show()
                    }
                }

                is PostsState.PostsLoaded -> {
                    postAdapter.updatePosts(postState.posts)
                }
            }
        })

        postsViewModel?.refreshPosts()
    }

    override fun postClicked(post: Post) {
        findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToCommentsFragment(post.id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
