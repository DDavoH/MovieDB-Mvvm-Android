package com.davoh.moviedb_mvvm_android.data

import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponseMovieLst

fun GetPopularResponse.toDomainPopularMovies(): PopularMovies {
    
    return PopularMovies(
        this.page ?: 0,
        this.results?.toDomainMovie() ?: emptyList(),
        this.total_pages ?: 0,
        this.total_results ?: 0
    )
}

fun List<GetPopularResponseMovieLst>.toDomainMovie(): List<Movie> = this.map {
    Movie(
        it.adult ?: false,
        it.backdrop_path ?: "",
        it.genre_ids ?: emptyList(),
        it.id ?: 0,
        it.original_language ?: "",
        it.original_title ?: "",
        it.overview ?: "",
        it.popularity ?: 0.0,
        it.poster_path ?: "",
        it.release_date ?: "",
        it.title ?: "",
        it.video ?: false,
        it.vote_average ?: 0.0,
        it.vote_count ?: 0,
        0
    )
}