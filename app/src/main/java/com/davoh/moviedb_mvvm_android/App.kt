package com.davoh.moviedb_mvvm_android

import android.app.AlarmManager
import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.CloseEventBroadCast
import com.davoh.moviedb_mvvm_android.presentation.utils.lifecycle.ActivityLifecycleHandler
import javax.inject.Inject

@HiltAndroidApp
class App : Application() , Configuration.Provider {
   
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    
    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    
    override fun onCreate() {
        super.onCreate()
        
        val configuration = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
        
        WorkManager.initialize(applicationContext, configuration)
        
        // This is for registering the listener class
        registerActivityLifecycleCallbacks(ActivityLifecycleHandler(this))
    }
    
}