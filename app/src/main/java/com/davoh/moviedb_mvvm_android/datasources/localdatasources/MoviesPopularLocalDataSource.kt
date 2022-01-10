package com.davoh.moviedb_mvvm_android.datasources.localdatasources

import androidx.lifecycle.LiveData
import com.davoh.moviedb_mvvm_android.framework.room.movies.MoviesDatabase
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.MoviePopularEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MoviesPopularLocalDataSource @Inject constructor(private val database:MoviesDatabase) {
    
    fun insertMoviesPopular(movieFavoriteEntity: List<MoviePopularEntity>): Single<List<Long>> {
        return database.moviePopularDao().addMovies(movieFavoriteEntity).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }
    
    fun deleteMoviesPopular(): Single<Int> {
        return database.moviePopularDao().deleteMovies().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }
    
    fun getMoviePopularList(): LiveData<List<MoviePopularEntity>> {
        return database.moviePopularDao().getMovies()
    }

}