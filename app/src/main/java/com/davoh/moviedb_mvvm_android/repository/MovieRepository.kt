package com.davoh.moviedb_mvvm_android.repository

import com.davoh.moviedb_mvvm_android.datasources.remotedatasources.GetPopularMoviesRemoteDataSource
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieRepository @Inject constructor(private val getPopularMoviesRemoteDataSource: GetPopularMoviesRemoteDataSource){
    
    fun getPopularMovies(pageNum:Int): Observable<GetPopularResponse> {
        return getPopularMoviesRemoteDataSource.getPopularMovies(
             pageNum
        )
    }

}