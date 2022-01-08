package com.example.moviedb_mvvm_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding:ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}