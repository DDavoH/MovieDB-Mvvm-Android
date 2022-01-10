package com.davoh.moviedb_mvvm_android.presentation.placesHome

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.davoh.moviedb_mvvm_android.databinding.FragmentPlacesBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.CloseEventBroadCast
import com.davoh.moviedb_mvvm_android.presentation.placesHome.utils.LocationBroadCast
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions


@AndroidEntryPoint
class PlacesHomeFragment : Fragment() {
    
    private lateinit var binding: FragmentPlacesBinding
    private val viewModel by viewModels<PlacesHomeViewModel>()
    
    
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var mMap: GoogleMap? = null
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacesBinding.inflate(inflater, container, false)
        
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        requestPermissionsUbication()
        
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map_google) as SupportMapFragment
        mapFragment.getMapAsync { p0 -> mMap = p0 }
        
        observeData()
        
        return binding.root
    }
    
    
    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }
    
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(10 * 1000.toLong())
            .setFastestInterval(1 * 1000.toLong())
        
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (location in p0.locations) {
                    //mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12f))
                }
            }
        }
        
        fusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.getMainLooper())
    }
    
    private fun requestPermissionsUbication() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                //performAction(...)
                displayLocationSettingsRequest()
            }
            shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION) -> {
                requestPermissionLauncher.launch(
                    permission.ACCESS_FINE_LOCATION
                )
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissionLauncher.launch(
                    permission.ACCESS_FINE_LOCATION
                )
            }
        }
        
        
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
            
            }
            shouldShowRequestPermissionRationale(permission.ACCESS_BACKGROUND_LOCATION) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestPermissionLauncher.launch(
                        permission.ACCESS_BACKGROUND_LOCATION
                    )
                }
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestPermissionLauncher.launch(
                        permission.ACCESS_BACKGROUND_LOCATION
                    )
                }
            }
        }
    }
    
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            PEMISSION_CODE_ACCES_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    //Toast.makeText(requireContext(), "This persission is granted", Toast.LENGTH_SHORT).show()
                    displayLocationSettingsRequest()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No se podra acceder a las funcionalidades",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }
            
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
        
    }
    
    private fun displayLocationSettingsRequest() {
        
        val mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(10 * 1000.toLong())
            .setFastestInterval(1 * 1000.toLong())
        
        val settingsBuilder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)
        settingsBuilder.setAlwaysShow(true)
        
        val result = LocationServices.getSettingsClient(requireContext())
            .checkLocationSettings(settingsBuilder.build())
        
        Log.d(TAG, "displayLocationSettingsRequest()")
        
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                
                if (response.locationSettingsStates?.isLocationUsable == true) {
                    
                    requestLastLocation()
                    
                }
                
                
            } catch (ex: ApiException) {
                Log.d(TAG, "Solicitando activación gps")
                when (ex.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException.startResolutionForResult(requireActivity(), GPS_REQUEST_CODE)
                    } catch (e: IntentSender.SendIntentException) {
                    
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }
            }
            
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GPS_REQUEST_CODE) {
            Toast.makeText(requireContext(), "GPS ENABLED", Toast.LENGTH_SHORT).show()
            
            displayLocationSettingsRequest()
        }
    }
    
    @SuppressLint("MissingPermission")
    private fun requestLastLocation() {
        
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    viewModel.saveNewUbication(requireContext())
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 19f))
                } else {
                    //update locations
                    //if location return null start to requestLocationUpdated
                    startLocationUpdates()
                }
            }
        
        
    }
    
    
    private fun observeData() {
        viewModel.loadLocations()
        viewModel.observeLocations().observe(viewLifecycleOwner, {places ->
            places.forEach { place->
                mMap?.addMarker(
                    MarkerOptions()
                    .position(LatLng(place.lat, place.lng))
                    .title(place.date))
            }
        })
    }
    

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                requestPermissionsUbication()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }
    
    
    
    companion object {
        const val TAG = "testTagPrincipal"
        const val PEMISSION_CODE_ACCES_FINE_LOCATION = 234
        const val PEMISSION_CODE_ACCESS_BACKGROUND = 256
        const val GPS_REQUEST_CODE = 235
    }
    
}
