package com.example.kotlinmodule.dataBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityDataBindingBinding

private lateinit var binding:ActivityDataBindingBinding
class DataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDataBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding=DataBindingUtil.setContentView(this ,R.layout.activity_data_binding)

        val data=MyData(12,"Arpit","Adajan",true,"https://picsum.photos/seed/picsum/200/300")
        binding.data=data
    }
}