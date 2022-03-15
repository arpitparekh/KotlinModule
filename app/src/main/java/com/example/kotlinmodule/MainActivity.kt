package com.example.kotlinmodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinmodule.databinding.ActivityMainBinding

private lateinit var binding:ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("hi","Activity Created")

        title="Main Activity"
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            intent=Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("hi","Activity Started")
    }

    override fun onResume() {
        super.onResume()
        Log.i("hi","Activity Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("hi","Activity Pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("hi","Activity Destroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("hi","Activity Stop")
    }
}