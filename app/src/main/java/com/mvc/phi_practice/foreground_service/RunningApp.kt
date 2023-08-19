package com.mvc.phi_practice.foreground_service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.mvc.phi_practice.work_manager.PhotoCompressionWorker

class RunningApp : Application() {
    override fun onCreate(){
        super.onCreate()
        val myConstraints = Constraints.Builder()
            .setRequiresDeviceIdle(true)
            .setRequiresCharging(true)
            .build()
        val request = OneTimeWorkRequest.Builder(PhotoCompressionWorker::class.java).setConstraints(myConstraints).build()
        WorkManager.getInstance(applicationContext).enqueue(request)
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
//            var channel = NotificationChannel("phi_test_foreground_service","foreground_service", NotificationManager.IMPORTANCE_HIGH)
//            var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)

//
//        }
    }
}