package com.davoh.moviedb_mvvm_android.framework.retrofit

import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServices {
    
    @GET("movie/popular?api_key={api_key}&language=en-US&page={page_num}")
    fun getPopular(@Path("api_key") apiKey : String, @Path("page_num") pageNum: Int): Observable<GetPopularResponse>
    
    
    

    
}