package com.example.kotlinmodule.passObject_fragment_bundle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityHolderBinding

private lateinit var binding:ActivityHolderBinding
class HolderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Holder Activity"
        binding= ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment=OneFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_holderActivity,fragment)
            .commit()

    }
}