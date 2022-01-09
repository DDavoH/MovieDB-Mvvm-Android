package com.davoh.moviedb_mvvm_android.datasources.localdatasources

import androidx.lifecycle.LiveData
import com.davoh.moviedb_mvvm_android.framework.room.movies.MoviesDatabase
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.MovieFavoriteEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MoviesFavoriteLocalDataSource @Inject constructor(private val database: MoviesDatabase){
    
    fun insertMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity): Single<Long> {
        return database.movieFavoriteDao().addMovieFavorite(movieFavoriteEntity).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }
    
    fun deleteMovieFavorite(movieId:Int): Single<Int>{
        return database.movieFavoriteDao().deleteMovieFavorite(movieId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }
    
    fun getMovieFavoriteList(): LiveData<List<MovieFavoriteEntity>>{
        return database.movieFavoriteDao().getFavoriteMovies()
    }
    
    fun existsMovieInFavorite(title: String): Single<Boolean> {
        return database.movieFavoriteDao().existsMovieInFavorite(title)
    }
    
}