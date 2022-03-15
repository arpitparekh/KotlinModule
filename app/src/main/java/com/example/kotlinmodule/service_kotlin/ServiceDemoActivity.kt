package com.example.kotlinmodule.service_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.databinding.ActivityServiceDemoBinding

private lateinit var binding:ActivityServiceDemoBinding
class ServiceDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityServiceDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnServiceStart.setOnClickListener {
            intent= Intent(this,BackGroundService::class.java)
            startService(intent)
        }
        binding.btnServiceStop.setOnClickListener {
            intent=Intent(this,BackGroundService::class.java)
            stopService(intent)
        }
    }
}