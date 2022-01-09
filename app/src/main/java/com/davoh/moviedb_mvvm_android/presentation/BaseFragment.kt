package com.davoh.moviedb_mvvm_android.presentation

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

open class BaseFragment : Fragment() {
    
    fun configToolbar(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
       toolbar.setupWithNavController(findNavController(), appBarConfiguration)
    }
    
    fun titleFragment(title:String, tvTitle:TextView){
        tvTitle.text = title
    }

}