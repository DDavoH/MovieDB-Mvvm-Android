package com.davoh.moviedb_mvvm_android.framework.retrofit

import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServices {
    
    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey : String,
        @Query("language") languaje:String,
        @Query("page") pageNum: Int): Observable<GetPopularResponse>
    
    
    

    
}