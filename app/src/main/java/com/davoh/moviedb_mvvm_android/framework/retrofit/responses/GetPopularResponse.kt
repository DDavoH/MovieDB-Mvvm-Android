package com.davoh.moviedb_mvvm_android.framework.retrofit.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetPopularResponse (
    @SerializedName("page") val page : Int?=null,
    @SerializedName("results") val results : List<GetPopularResponseMovieLst>?=null,
    @SerializedName("total_pages") val total_pages : Int?=null,
    @SerializedName("total_results") val total_results : Int?=null
):Serializable

data class GetPopularResponseMovieLst(
    @SerializedName("adult") val adult : Boolean?=null,
    @SerializedName("backdrop_path") val backdrop_path : String?=null,
    @SerializedName("genre_ids") val genre_ids : List<Int>?=null,
    @SerializedName("id") val id : Int?=null,
    @SerializedName("original_language") val original_language : String?=null,
    @SerializedName("original_title") val original_title : String?=null,
    @SerializedName("overview") val overview : String?=null,
    @SerializedName("popularity") val popularity : Double?=null,
    @SerializedName("poster_path") val poster_path : String?=null,
    @SerializedName("release_date") val release_date : String?=null,
    @SerializedName("title") val title : String?=null,
    @SerializedName("video") val video : Boolean?=null,
    @SerializedName("vote_average") val vote_average : Double?=null,
    @SerializedName("vote_count") val vote_count : Int?=null
):Serializable