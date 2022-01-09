package com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.MovieFavoriteEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieFavoriteDao {
    
    @Query("SELECT * FROM movie_favorite_table")
    fun getFavoriteMovies(): LiveData<List<MovieFavoriteEntity>>
    
    @Insert
    fun addMovieFavorite(entity: MovieFavoriteEntity): Single<Long>
    
    @Query("DELETE FROM movie_favorite_table WHERE id =:movieId")
    fun deleteMovieFavorite(movieId:Int): Single<Int>
    
    @Query("SELECT EXISTS (SELECT * FROM movie_favorite_table WHERE title =:title)")
    fun existsMovieInFavorite(title: String): Single<Boolean>
    
}