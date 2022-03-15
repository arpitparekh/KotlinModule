package com.example.kotlinmodule.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmodule.databinding.ActivityPostListBinding
import com.example.kotlinmodule.retrofit.data_classes.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding:ActivityPostListBinding
//private lateinit var postList: List<Post>
private lateinit var adapter:PostAdapter


class PostListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager=LinearLayoutManager(this)

        binding.recyclerViewPost.layoutManager=linearLayoutManager

        fetchPosts()

    }

    private fun fetchPosts() {

        val service:RetrofitService=RetrofitClient.getService()
        val call=service.getPosts()




        call.enqueue(object: Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.i("post",response.body().toString())
                val postList= response.body()
                adapter=PostAdapter(postList)
                binding.recyclerViewPost.adapter= adapter
            }


            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.i("post",t.toString())
            }
        })



    }
}