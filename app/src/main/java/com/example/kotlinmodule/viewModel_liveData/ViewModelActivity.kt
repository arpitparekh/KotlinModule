package com.example.kotlinmodule.viewModel_liveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmodule.databinding.ActivityViewModelBinding

private lateinit var binding:ActivityViewModelBinding
private lateinit var counterviewmodel:CounterViewModel

class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counterviewmodel=ViewModelProvider(this).get(CounterViewModel::class.java)

        val count: LiveData<Int> = counterviewmodel.getInitialCount()
        Log.i("count", counterviewmodel.getInitialCount().toString())

        count.observe(this, Observer {
            binding.tvNumber.text=it.toString()
        })

        binding.btnIncrease.setOnClickListener {
            binding.tvNumber.text=counterviewmodel.getCurrentCount().toString()
        }
    }
}