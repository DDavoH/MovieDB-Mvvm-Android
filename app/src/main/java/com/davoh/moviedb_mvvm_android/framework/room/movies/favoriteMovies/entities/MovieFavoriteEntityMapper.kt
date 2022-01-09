package com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities

import com.davoh.moviedb_mvvm_android.data.Movie

fun Movie.toEntityMovieFavorite():MovieFavoriteEntity{
    return MovieFavoriteEntity(
        id = 0,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun List<MovieFavoriteEntity>.toDomainMovieList():List<Movie> = this.map {
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