package com.example.kotlinmodule.job_scheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

private var isStared: Boolean = true

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyDemoService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {

        Log.i("JobInfo", "onStartJob")
        Thread {
            isStared = true
            startNumber()
        }.start()
        return true
    }
    private fun startNumber() {
        var x = 1
        while (x < 30) {
            while (isStared) {
                Thread.sleep(1000)
                Log.i("JobInfo", "$x Name of the Thread is : ${Thread.currentThread().name}")
                x++
            }
        }
    }
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i("JobInfo", "onStopJob")
        return false
    }
    override fun onDestroy() {
        super.onDestroy()
        isStared = false

    }
}