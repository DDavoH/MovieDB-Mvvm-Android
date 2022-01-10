package com.davoh.moviedb_mvvm_android.presentation.placesHome.utils

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.davoh.moviedb_mvvm_android.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class CloseEventHandler @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    
    override fun doWork(): Result {
        stopBackgroundProcess()
        return Result.success()
    }
    
     fun stopBackgroundProcess(){
        Log.e("WORKMANAGER", "FRAGMENT FINALIZADO")
        val nManager = applicationContext.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        nManager.cancel(applicationContext.getString(R.string.notification_id).toInt())
    
        val intent = Intent(applicationContext, LocationBroadCast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, applicationContext.getString(R.string.alarm_id).toInt(), intent, PendingIntent.FLAG_NO_CREATE)
        val alarmManager = applicationContext.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
    
    
    
}