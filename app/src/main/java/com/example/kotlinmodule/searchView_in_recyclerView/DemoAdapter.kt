package com.example.kotlinmodule.searchView_in_recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmodule.databinding.DemoRowItemBinding

private lateinit var binding:DemoRowItemBinding
class DemoAdapter(private var dataList:List<DemoData>) : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {

    class DemoViewHolder(binding:DemoRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding=binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        binding= DemoRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val demo=dataList[position]
        holder.binding.demo=demo
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}