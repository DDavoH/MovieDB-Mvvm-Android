package com.davoh.moviedb_mvvm_android.presentation.placesHome.utils

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import androidx.work.*
import java.util.concurrent.TimeUnit

class LocationBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        @SuppressLint("InvalidWakeLockTag") val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "")
        wl.acquire(10 * 90 * 1000L)
        
        val periodicWorkRequest: PeriodicWorkRequest = PeriodicWorkRequest.Builder(EventHandler::class.java, 5, TimeUnit.SECONDS)
            .addTag("periodic")
            .setConstraints(Constraints.Builder().build())
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
        wl.release()
    }
}