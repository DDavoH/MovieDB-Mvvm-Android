package com.davoh.moviedb_mvvm_android.presentation.utils.lifecycle

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.datasources.constants.Constants
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.LocationBroadCast

class ActivityLifecycleHandler(private val application: Application) :
    Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(p0: Activity) {
        Log.d(TAG, "onActivityPaused at ${p0.localClassName}")
    }
    
    override fun onActivityStarted(p0: Activity) {
        Log.d(TAG, "onActivityStarted at ${p0.localClassName}")
    }
    
    override fun onActivityDestroyed(p0: Activity) {
        Log.d(TAG, "onActivityDestroyed at ${p0.localClassName}")
    }
    
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.d(TAG, "onActivitySaveInstanceState at ${p0.localClassName}")
    }
    
    override fun onActivityStopped(p0: Activity) {
        Log.d(TAG, "onActivityStopped at ${p0.localClassName}")
    }
    
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        Log.d(TAG, "onActivityCreated at ${p0.localClassName}")
    }
    
    override fun onActivityResumed(p0: Activity) {
        val intent = Intent(p0, LocationBroadCast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(p0, p0.getString(R.string.alarm_id).toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = p0.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), Constants.counterFindLocation, pendingIntent)
    
        Log.d(TAG, "onActivityResumed at ${p0.localClassName}")
    }
    
    companion object {
        private const val TAG = "LifecycleCallbacks"
    }
}