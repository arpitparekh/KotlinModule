package com.example.kotlinmodule.apache_server_own_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.databinding.ActivityDataListBinding

private lateinit var binding:ActivityDataListBinding

class DataListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDataListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}