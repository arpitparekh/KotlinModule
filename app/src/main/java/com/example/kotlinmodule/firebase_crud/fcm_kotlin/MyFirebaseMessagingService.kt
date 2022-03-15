package com.example.kotlinmodule.firebase_crud.fcm_kotlin

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.math.log

class MyFirebaseMessagingService : FirebaseMessagingService()  {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("NewToken",token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i("message",message.toString())


    }

}