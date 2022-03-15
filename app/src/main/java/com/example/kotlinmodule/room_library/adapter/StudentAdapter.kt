package com.example.kotlinmodule.room_library.adapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.kotlinmodule.databinding.StudentRowItemBinding
import com.example.kotlinmodule.room_library.Student

private lateinit var binding: StudentRowItemBinding
private lateinit var listener:StudentAdapter.OnStudentClickListener

class StudentAdapter(private val studentList: List<Student>,val listener:OnStudentClickListener) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    interface OnStudentClickListener{
        fun onClickListener(position:Int)
    }


    class StudentViewHolder(binding: StudentRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding=binding
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        binding = StudentRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)

    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val s1:Student= studentList[position]
        holder.binding.s=s1
        holder.itemView.setOnLongClickListener {
            listener.onClickListener(position)
            false
        }


    }
    override fun getItemCount(): Int {
        return studentList.size
    }
}