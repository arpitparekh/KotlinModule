package com.example.kotlinmodule.job_scheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.kotlinmodule.databinding.ActivityMyJobSchedulerBinding

private lateinit var binding:ActivityMyJobSchedulerBinding
private lateinit var jobScheduler: JobScheduler

class MyJobSchedulerActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyJobSchedulerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jobScheduler= getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        binding.btnStartThread.setOnClickListener {

            val componentName = ComponentName(this,MyDemoService::class.java)
            val jobInfo= JobInfo.Builder(101,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setOverrideDeadline(3*60*1000)
                .setRequiresCharging(false)
//                .setPersisted(true)
                .build()

            if(jobScheduler.schedule(jobInfo)==JobScheduler.RESULT_SUCCESS){
                Log.i("JobInfo",Thread.currentThread().name+" Job SuccessFully Started")
            }else{
                Log.i("JobInfo",Thread.currentThread().name+" Job Could Not be Schedule")
            }
        }

        binding.btnStopThread.setOnClickListener {

            jobScheduler.cancel(101)
        }
    }
}