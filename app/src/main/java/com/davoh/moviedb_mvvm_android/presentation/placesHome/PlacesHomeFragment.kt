package com.davoh.moviedb_mvvm_android.presentation.placesHome

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.databinding.FragmentPlacesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlacesHomeFragment : Fragment() {
  
    private lateinit var binding : FragmentPlacesBinding
    private val viewModel by activityViewModels<PlacesHomeViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacesBinding.inflate(inflater, container, false)
        
        requestPermissionsUbication()
        
    return binding.root
    }
    
    
    private fun requestPermissionsUbication() {
        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                
                
                
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }
    
    
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            //shouldShowRequestPermissionRationale(...) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            // showInContextUI(...)
            else -> {
            
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                //   requestPermissionLauncher.launch(
                //     Manifest.permission.REQUESTED_PERMISSION)
            }  // }
        }
    }
    
    
    

}