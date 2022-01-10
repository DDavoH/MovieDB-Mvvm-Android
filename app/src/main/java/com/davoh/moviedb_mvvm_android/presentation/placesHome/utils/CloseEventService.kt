package com.davoh.moviedb_mvvm_android.presentation.placesHome.utils

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CloseEventService : Service() {
    var alarm: CloseEventBroadCast = CloseEventBroadCast()
    override fun onCreate() {
        super.onCreate()
    }
    
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        alarm.setAlarm(this)
        return START_STICKY
    }
    
    override fun onStart(intent: Intent, startId: Int) {
        alarm.setAlarm(this)
    }
    
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}