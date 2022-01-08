package com.example.moviedb_mvvm_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davoh.moviedb_mvvm_android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}