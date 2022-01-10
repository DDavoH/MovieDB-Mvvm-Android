package com.davoh.moviedb_mvvm_android.repository

import androidx.lifecycle.LiveData
import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.datasources.localdatasources.MoviesFavoriteLocalDataSource
import com.davoh.moviedb_mvvm_android.datasources.localdatasources.MoviesPopularLocalDataSource
import com.davoh.moviedb_mvvm_android.datasources.remotedatasources.MoviesPopularRemoteDataSource
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.MovieFavoriteEntity
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.toEntityMovieFavorite
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.MoviePopularEntity
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.toEntityMovieFavoriteList
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesPopularRemoteDataSource: MoviesPopularRemoteDataSource,
    private val moviesFavoriteLocalDataSource: MoviesFavoriteLocalDataSource,
    private val moviesPopularLocalDataSource: MoviesPopularLocalDataSource
    ){
    
    fun getPopularMovies(pageNum:Int): Observable<GetPopularResponse> {
        return moviesPopularRemoteDataSource.getPopularMovies(
             pageNum
        )
    }
    
    fun saveFavoriteMovie(movie:Movie): Single<Long> {
       return moviesFavoriteLocalDataSource.insertMovieFavorite(movie.toEntityMovieFavorite())
    }
    fun deleteFavoriteMovie(movie:Movie): Single<Int>{
        return moviesFavoriteLocalDataSource.deleteMovieFavorite(movie.localdbId)
    }
    fun getFavoriteMovies(): LiveData<List<MovieFavoriteEntity>> {
        return moviesFavoriteLocalDataSource.getMovieFavoriteList()
    }
    fun existsMovieInFavorite(title:String): Single<Boolean>{
        return moviesFavoriteLocalDataSource.existsMovieInFavorite(title)
    }
    
    fun savePopularMovies(movies:List<Movie>): Single<List<Long>> {
        return moviesPopularLocalDataSource.insertMoviesPopular(movies.toEntityMovieFavoriteList())
    }
    fun deletePopularMovies(): Single<Int>{
        return moviesPopularLocalDataSource.deleteMoviesPopular()
    }
    fun getLocalPopularMovies(): LiveData<List<MoviePopularEntity>> {
        return moviesPopularLocalDataSource.getMoviePopularList()
    }
    

}