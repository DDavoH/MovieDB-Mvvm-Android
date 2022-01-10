package com.davoh.moviedb_mvvm_android.data.places

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.GeoPoint
import java.text.SimpleDateFormat
import java.util.*


fun DocumentSnapshot.toPlace(): Place? {
    return try {
        val latLang: GeoPoint? = getGeoPoint("position")
        val dateCreated = getTimestamp("created_date")
        Place(latLang?.latitude?:0.0,  latLang?.longitude?:0.1, getDatess(dateCreated?.toDate()))
    } catch (e: Exception) {
        Place(0.0,  0.0, "")
    }
}


private fun getDatess(date:Date?):String{
    val simpleDate = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    return simpleDate.format(date)
}

