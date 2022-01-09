package com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities

import com.davoh.moviedb_mvvm_android.data.Movie

fun List<Movie>.toEntityMovieFavoriteList(): List<MoviePopularEntity>  = this.map{
     MoviePopularEntity(
        id = 0,
        adult = it.adult,
        backdropPath = it.backdropPath,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        overview = it.overview,
        popularity = it.popularity,
        posterPath = it.posterPath,
        releaseDate = it.releaseDate,
        title = it.title,
        video = it.video,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount
    )
}

fun List<MoviePopularEntity>.toDomainMovieList():List<Movie> = this.map {
    Movie(
        adult = it.adult,
        backdropPath = it.backdropPath,
        id = it.id,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        overview = it.overview,
        popularity = it.popularity,
        posterPath = it.posterPath,
        releaseDate = it.releaseDate,
        title = it.title,
        video = it.video,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        localdbId = it.id,
        genreIds = emptyList())
}