package com.example.kotlinmodule.firebase_crud.fcm_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinmodule.databinding.ActivityFcmactivityBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

private lateinit var binding:ActivityFcmactivityBinding
class FCMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFcmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {
            if(!it.isSuccessful){
                Log.i("NewToken","Token Failed")
            }
            val token=it.result
            Log.i("NewToken",token.toString())

        })


    }
}