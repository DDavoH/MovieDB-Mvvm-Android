package com.davoh.moviedb_mvvm_android.presentation.placesHome.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.Log
import androidx.work.*
import com.davoh.moviedb_mvvm_android.R

class CloseEventBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.e("WORKMANAGER", " CloseEventBroadCast Receive")
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        @SuppressLint("InvalidWakeLockTag") val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "")
        wl.acquire(10 * 90 * 1000L)
        
        val workRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(CloseEventHandler::class.java)
            .setConstraints(Constraints.Builder().build())
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
        wl.release()
        
        
        /*val intent2 = Intent(context, LocationBroadCast::class.java)
        val pending = PendingIntent.getBroadcast(context, context.getString(R.string.alarm_close_id).toInt(), intent2, PendingIntent.FLAG_NO_CREATE)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1 * 1000, pending)*/
        
        
        
        
        /*val intent = Intent(context, CloseEventBroadCast::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, FLAG_C)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)*/
    }
    
    fun setAlarm(context: Context) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context,CloseEventBroadCast::class.java)
        val pi = PendingIntent.getBroadcast(context, 0, i, 0)
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (1000 * 60 * 10).toLong(), pi) // Millisec * Second * Minute
    }
    
    fun cancelAlarm(context: Context) {
        val intent = Intent(context, CloseEventBroadCast::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)
    }
}