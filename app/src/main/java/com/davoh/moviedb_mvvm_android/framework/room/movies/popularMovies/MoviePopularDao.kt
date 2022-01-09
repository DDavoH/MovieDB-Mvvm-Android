package com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.MoviePopularEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface MoviePopularDao {
    
    @Query("SELECT * FROM movie_popular_table")
    fun getMovies(): LiveData<List<MoviePopularEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(entity: List<MoviePopularEntity>): Single<List<Long>>
    
    @Query("DELETE FROM movie_popular_table")
    fun deleteMovies(): Single<Int>
    
}