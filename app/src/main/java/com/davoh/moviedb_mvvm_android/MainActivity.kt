package com.example.moviedb_mvvm_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import android.app.NotificationManager
import android.content.Context
import android.app.AlarmManager

import android.app.PendingIntent

import android.content.Intent
import android.util.Log
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.CloseEventBroadCast

import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.LocationBroadCast



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding:ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        
    }
}