package com.example.kotlinmodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import com.example.kotlinmodule.databinding.ActivitySplashScreenBinding

private lateinit var binding:ActivitySplashScreenBinding
class SplashScreenActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        },2000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*  Class used to run a message loop for a thread.
         Threads by default do not have a message loop associated with them;
         to create one, call prepare in the thread that is to run the loop,
         and then loop to have it process messages until the loop is stopped. */
    }
}