package com.davoh.moviedb_mvvm_android.presentation.placesHome

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.davoh.moviedb_mvvm_android.App
import com.davoh.moviedb_mvvm_android.datasources.remotedatasources.LocationRemoteDataSource
import com.davoh.moviedb_mvvm_android.presentation.BaseViewModel
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.app.AlarmManager

import android.content.Context.ALARM_SERVICE

import androidx.core.content.ContextCompat.getSystemService

import android.app.PendingIntent

import android.content.Intent
import androidx.lifecycle.LiveData
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.data.places.Place
import com.davoh.moviedb_mvvm_android.datasources.constants.Constants
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.LocationBroadCast


@HiltViewModel
class PlacesHomeViewModel @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource
    ) :BaseViewModel() {
    
    var isSaving = false
    
    fun saveNewUbication(context: Context) {
        if(!isSaving){
            isSaving = true
    
            val intent = Intent(context, LocationBroadCast::class.java)
            
            val pendingIntent = PendingIntent.getBroadcast(context, context.getString(R.string.alarm_id).toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), Constants.counterFindLocation, pendingIntent)
        }
        
    }
    
    fun loadLocations(){
        locationRemoteDataSource.getPositions()
    }
    
    fun observeLocations(): LiveData<List<Place>>{
        return locationRemoteDataSource.positionsList
    }
    
}