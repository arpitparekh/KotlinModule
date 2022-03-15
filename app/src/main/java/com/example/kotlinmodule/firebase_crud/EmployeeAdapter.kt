package com.example.kotlinmodule.firebase_crud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmodule.databinding.EmployeeRowItemBinding

private lateinit var binding:EmployeeRowItemBinding
private lateinit var listener:EmployeeAdapter.OnEmployeeClickListener
class EmployeeAdapter(private var employeeList:MutableList<HashMap<String,Employee>>,private var listener: OnEmployeeClickListener) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(binding:EmployeeRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding=binding
    }

    interface OnEmployeeClickListener{
        fun onEmployeeClick(key:String,employee:Employee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        binding= EmployeeRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val hashMap: HashMap<String,Employee> = employeeList[position]
        val key:String=hashMap.keys.iterator().next()
        val employee: Employee? =hashMap[key]
        holder.binding.emp=employee

        //click event
        holder.itemView.setOnClickListener {
            if (employee != null) {
                listener.onEmployeeClick(key,employee)
            }
        }

    }
    override fun getItemCount(): Int {
        return employeeList.size
    }
}