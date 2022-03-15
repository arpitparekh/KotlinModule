package com.example.kotlinmodule.retrofit

import com.example.kotlinmodule.retrofit.data_classes.Post
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitService {
    @GET("posts")
    fun getPosts():Call<List<Post>>


}