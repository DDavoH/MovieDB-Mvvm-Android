package com.davoh.moviedb_mvvm_android.presentation.movieDetails

import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.data.messageAlert.MessageAlert
import com.davoh.moviedb_mvvm_android.data.messageAlert.MessageAlertTypeError
import com.davoh.moviedb_mvvm_android.presentation.BaseViewModel
import com.davoh.moviedb_mvvm_android.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseViewModel(){
    
    fun saveMovieFavorite(movie:Movie){
        movieRepository.existsMovieInFavorite(movie.title).subscribe({exist ->
            if(exist){
                messageAlert.postValue(MessageAlert(MessageAlertTypeError.ERROR.id))
            }else{
                movieRepository.saveFavoriteMovie(movie).subscribe({
                    messageAlert.postValue(MessageAlert(MessageAlertTypeError.SUCCESS.id))
                },{
        
                })
            }
        },{
            movieRepository.saveFavoriteMovie(movie).subscribe({
                messageAlert.postValue(MessageAlert(MessageAlertTypeError.SUCCESS.id))
            },{
        
            })
        })
      
    }
    
    fun deleteMovieFavorite(movie:Movie){
        movieRepository.deleteFavoriteMovie(movie).subscribe({
        
        },{
        
        })
    }
    
}