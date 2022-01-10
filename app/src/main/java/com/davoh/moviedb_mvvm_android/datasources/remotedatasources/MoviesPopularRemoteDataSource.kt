package com.davoh.moviedb_mvvm_android.datasources.remotedatasources

import com.davoh.moviedb_mvvm_android.datasources.constants.Constants.apiKey
import com.davoh.moviedb_mvvm_android.datasources.constants.Constants.language
import com.davoh.moviedb_mvvm_android.framework.retrofit.APIServices
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MoviesPopularRemoteDataSource @Inject constructor(private val apiServices: APIServices) {
    
    fun getPopularMovies(pageNumer:Int) : Observable<GetPopularResponse> {
        return apiServices.getPopular(apiKey,language, pageNumer).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }
    
}