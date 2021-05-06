package com.example.instagram_task

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.instagram_task.comments.CommentsViewModel
import com.example.instagram_task.data.Api
import com.example.instagram_task.data.Repository
import com.example.instagram_task.data.RepositoryImpl
import com.example.instagram_task.posts.PostsViewModel
import com.example.instagram_task.utils.NetworkState
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataSourceModule = module {

    single { createRetrofitInstance() }

    single { get<Retrofit>().create(Api::class.java) }

    single { NetworkState(androidApplication()) }

    single { RepositoryImpl(get(), get(), androidApplication()) as Repository }

}

val viewmodelModule = module {
    viewModel { PostsViewModel(get()) }

    viewModel { CommentsViewModel(get()) }
}

fun createRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(OkHttpClient.Builder().also { client ->
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url).placeholder(R.drawable.placeholder)
        .apply(RequestOptions.fitCenterTransform().diskCacheStrategy(DiskCacheStrategy.ALL).override(600,50))
        .into(this)
}