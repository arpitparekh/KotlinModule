package com.example.kotlinmodule.social_media_integration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.databinding.ActivityFacebookLoginBinding

private lateinit var binding:ActivityFacebookLoginBinding

class FacebookLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFacebookLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}