package com.davoh.moviedb_mvvm_android.presentation.placesHome.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.data.places.Place
import com.davoh.moviedb_mvvm_android.datasources.remotedatasources.LocationRemoteDataSource
import com.google.android.gms.location.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.google.android.gms.location.LocationServices

@HiltWorker
class EventHandler @AssistedInject constructor(
     @Assisted appContext: Context,
     @Assisted workerParams: WorkerParameters,
     private val positionRemoteDataSource : LocationRemoteDataSource
) : Worker(appContext, workerParams) {

override fun doWork(): Result {
        showNotification()
        requestLastLocation()
    Log.e("WORKMANAGER", "WORKMANAGER SE EJECUTO!!!!!")
        return Result.success()
    }
    
    fun showNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createGeneralChannel()
        }
    
    
        val intent2 = Intent(applicationContext, CloseEventBroadCast::class.java)
        val pending = PendingIntent.getBroadcast(applicationContext, applicationContext.getString(R.string.alarm_close_id).toInt(), intent2, PendingIntent.FLAG_UPDATE_CURRENT)
      
        
        
        //To Finish process
        //val intent = Intent(applicationContext, CloseEventHandler::class.java)
        //val pending = PendingIntent.getActivities(applicationContext, applicationContext.getString(R.string.alarm_close_id).toInt(), arrayOf(intent), PendingIntent.FLAG_UPDATE_CURRENT)
    
    
        val notificationCompat = NotificationCompat.Builder(applicationContext, applicationContext.getString(R.string.notification_channel_id))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(applicationContext.getString(R.string.notification_title))
            .setContentText(applicationContext.getString(R.string.notification_content_end))
            .setOngoing(true)
            .setContentIntent(pending)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationCompat.setChannelId(applicationContext.getString(R.string.notification_channel_id))
            notificationCompat.setGroup(applicationContext.getString(R.string.notification_channel_group))
            notificationCompat.priority = NotificationManagerCompat.IMPORTANCE_DEFAULT
            NotificationManagerCompat.from(applicationContext).notify(applicationContext.getString(R.string.notification_id).toInt(), notificationCompat.build())
        } else {
            notificationCompat.priority = NotificationCompat.PRIORITY_DEFAULT
            val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
            notificationManagerCompat.notify(applicationContext.getString(R.string.notification_id).toInt(), notificationCompat.build())
        }
    
    
    }
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createGeneralChannel() {
        val channel = NotificationChannel(applicationContext.getString(R.string.notification_channel_id),
            applicationContext.getString(R.string.notification_channel_id),
            NotificationManager.IMPORTANCE_HIGH)
        channel.description = applicationContext.getString(R.string.notification_channel_description)
        channel.enableVibration(false)
        channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(applicationContext)
        notificationManager.createNotificationChannel(channel)
    }
    
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    fun savePosition(place:Place){
        positionRemoteDataSource.savePosition(place)
    }
    

    @SuppressLint("MissingPermission")
    private fun requestLastLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
    
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            savePosition(Place(location.latitude, location.longitude, ""))
                                                    }else{
                            Log.e("WORKMANAGER", "UBICACION VACIA")
                            
                        }
                    }
                    .addOnFailureListener { e->{
                        Log.e("WORKMANAGER", "ERROR WORKMANAGER ${e.localizedMessage}")
                    } }
            }
        }
        
        
        
    }
    
    
    
}