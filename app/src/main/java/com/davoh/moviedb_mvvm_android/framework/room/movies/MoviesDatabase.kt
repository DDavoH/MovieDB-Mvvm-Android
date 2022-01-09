package com.davoh.moviedb_mvvm_android.framework.room.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.MovieFavoriteDao
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.MovieFavoriteEntity
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.MoviePopularDao
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.MoviePopularEntity

@Database(entities = [MovieFavoriteEntity::class, MoviePopularEntity::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieFavoriteDao(): MovieFavoriteDao
    abstract fun moviePopularDao() : MoviePopularDao
}