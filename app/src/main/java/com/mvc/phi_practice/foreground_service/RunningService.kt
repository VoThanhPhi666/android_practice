package com.mvc.phi_practice.foreground_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class RunningService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }
    override fun onStartCommand(intent:Intent?, flags:Int, startId:Int) : Int{
        when(intent?.action){
            ACTIONS.START.toString()-> start()
            ACTIONS.STOP.toString()-> stop()
        }
       return super.onStartCommand(intent, flags, startId)
    }
    enum class ACTIONS {
        START,STOP
    }
    fun  start(){
        var notification = NotificationCompat.Builder(this,"phi_test_foreground_service")
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle("Notification of foreground service")
            .setContentText("Hello World")
            .build()
        startForeground(1,notification)
    }
    fun stop(){
        stopSelf()
    }

}

