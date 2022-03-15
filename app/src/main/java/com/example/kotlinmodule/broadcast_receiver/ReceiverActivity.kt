package com.example.kotlinmodule.broadcast_receiver

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import com.example.kotlinmodule.databinding.ActivityReceiverBinding

private lateinit var binding:ActivityReceiverBinding

class ReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent= Intent()
        when(intent.action){

            Intent.ACTION_AIRPLANE_MODE_CHANGED-> Log.i("receiver","AirPlane Mode Receive")

            Intent.ACTION_BATTERY_LOW->Log.i("receiver","Battery Low Receive")



        }

    }
}