package com.davoh.moviedb_mvvm_android.repository

import com.davoh.moviedb_mvvm_android.framework.retrofit.APIServices
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiServices: APIServices){
    
    fun getPopularMovies(pageNum:Int): Observable<GetPopularResponse> {
        return apiServices.getPopular(
            "", pageNum
        )
    }

}