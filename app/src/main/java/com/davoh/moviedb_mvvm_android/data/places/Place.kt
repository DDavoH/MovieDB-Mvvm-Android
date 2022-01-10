package com.davoh.moviedb_mvvm_android.data.places

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Place(
    val lat:Double,
    val lng: Double,
    val date: String?
        ):Parcelable