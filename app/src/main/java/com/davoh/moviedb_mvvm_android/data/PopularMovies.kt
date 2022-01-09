package com.davoh.moviedb_mvvm_android.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularMovies(
    val page: Int,
    val movieList: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
) : Parcelable

@Parcelize
data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable