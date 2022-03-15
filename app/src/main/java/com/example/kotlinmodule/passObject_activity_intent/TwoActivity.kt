package com.example.kotlinmodule.passObject_activity_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.databinding.ActivityTwoBinding

private lateinit var binding:ActivityTwoBinding
class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data=intent.getSerializableExtra("object") as? Data  //cast objects as Data class

        binding.tvName.text = data?.name
        binding.tvAge.text= (data?.age.toString())
    }
}