package com.example.kotlinmodule.fragment_switch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityFragmentHostBinding

private lateinit var binding:ActivityFragmentHostBinding
class FragmentHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFragmentHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment=FirstFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_hostActivity,fragment)
            .commit()
    }

}