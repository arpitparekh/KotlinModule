package com.example.kotlinmodule.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmodule.databinding.PostRowItemBinding
import com.example.kotlinmodule.retrofit.data_classes.Post

private lateinit var binding:PostRowItemBinding

class PostAdapter(private val postList:List<Post>?) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(binding:PostRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding=binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding= PostRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post:Post= postList!![position]
        holder.binding.post=post
    }

    override fun getItemCount(): Int {
        return postList!!.size
    }
}