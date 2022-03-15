package com.example.kotlinmodule.passObject_activity_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityOneBinding
import java.io.Serializable

private lateinit var binding:ActivityOneBinding
class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val name=binding.edtName.text.toString()
            val age=binding.edtAge.text.toString()

            val data=Data(name,age.toInt())

            intent= Intent(this,TwoActivity::class.java)
            intent.putExtra("object",data)
            startActivity(intent)
        }

    }
}