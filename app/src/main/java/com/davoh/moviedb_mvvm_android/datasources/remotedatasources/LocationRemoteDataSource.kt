package com.davoh.moviedb_mvvm_android.datasources.remotedatasources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.davoh.moviedb_mvvm_android.data.places.Place
import com.davoh.moviedb_mvvm_android.data.places.toPlace
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.Query
import com.google.gson.Gson
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore){
    
    fun savePosition(place:Place) {
     
        // Create a new user with a first and last name
        
        val hash = GeoPoint(place.lat, place.lng)
        
        val dateCreated = FieldValue.serverTimestamp()
        
        
        val user = hashMapOf(
            "created_date" to dateCreated,
            "position" to hash
        )
    
        firestore.collection("ubications")
            .add(user)
            .addOnSuccessListener { documentReference ->
            
            }
            .addOnFailureListener { e ->
            
            }
    }
    
    var positionsList = MutableLiveData<List<Place>>()
    fun getPositions(): MutableLiveData<List<Place>> {
     
        firestore.collection("ubications")
            .orderBy("created_date", Query.Direction.DESCENDING)
            .limit(30)
            .addSnapshotListener { snapshot, e ->
            
                if (e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val listPlaces = mutableListOf<Place>()
                    for (document in snapshot) {
                       val gson = Gson()
                        Log.d("FIREBASEE", "${gson.toJson(document.toPlace())}")
                       listPlaces.add(document.toPlace()?:Place(0.0,0.0, ""))
                    }
                    positionsList.postValue(listPlaces)
                }
            
            }
        return positionsList
        
  
        
        
    }
    
    
    
    
    
    
}