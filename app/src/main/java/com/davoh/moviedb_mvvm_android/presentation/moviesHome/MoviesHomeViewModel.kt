package com.davoh.moviedb_mvvm_android.presentation.moviesHome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davoh.moviedb_mvvm_android.data.PopularMovies
import com.davoh.moviedb_mvvm_android.data.toDomainPopularMovies
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.MovieFavoriteEntity
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.MoviePopularEntity
import com.davoh.moviedb_mvvm_android.presentation.BaseViewModel
import com.davoh.moviedb_mvvm_android.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(private val movieRepository: MovieRepository): BaseViewModel(){
    
    fun getPopularMovies(pageNum: Int){
        movieRepository.getPopularMovies(pageNum)
            .map(GetPopularResponse::toDomainPopularMovies)
            .doOnSubscribe {isLoading.postValue(true)}
            .subscribe({ popularMovies ->
                movieRepository.deletePopularMovies().subscribe({
                    movieRepository.savePopularMovies(popularMovies.movieList).subscribe({
                        isLoading.postValue(false)
                    },{
                        isLoading.postValue(false)
                    })
                },{
                    isLoading.postValue(false)
                })
            },{
                isLoading.postValue(false)
            })
    }
    
    fun getFavoriteMovies():LiveData<List<MovieFavoriteEntity>>{
        return movieRepository.getFavoriteMovies()
    }
    
    fun getPopularMoviesLocal():LiveData<List<MoviePopularEntity>>{
        return movieRepository.getLocalPopularMovies()
    }
    
    
    
    
    
}