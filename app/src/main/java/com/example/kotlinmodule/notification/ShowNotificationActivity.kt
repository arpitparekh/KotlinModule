package com.example.kotlinmodule.notification

import android.app.*
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.kotlinmodule.R
import com.example.kotlinmodule.SplashScreenActivity
import com.example.kotlinmodule.camera.PickImageActivity
import com.example.kotlinmodule.databinding.ActivityShowNotificationBinding

private lateinit var binding:ActivityShowNotificationBinding

class ShowNotificationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowNotification.setOnClickListener {
            showNotification()
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {

        // on Oreo on above
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            val channel=NotificationChannel("id1","myChannel",NotificationManager.IMPORTANCE_DEFAULT)
            val myManager=getSystemService(NotificationManager::class.java)
            myManager.createNotificationChannel(channel)
        }

        //click event on notification
        val intent= Intent(this,PickImageActivity::class.java)
        val pendingIntent=TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        //custom notification
        val notificationLayout=RemoteViews("com.example.kotlinmodule",R.layout.custom_notification)

        // build notification
        val builder=Notification.Builder(this,"id1")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("This is Notification")
            .setContentText("Welcome is this notification")
            .setAutoCancel(true)
            .setStyle(Notification.DecoratedCustomViewStyle())   // add this line in custom notification
            .setCustomContentView(notificationLayout)
            .setContentIntent(pendingIntent)

        val manager: NotificationManagerCompat =NotificationManagerCompat.from(this)
        manager.notify(99,builder.build())
    }
}