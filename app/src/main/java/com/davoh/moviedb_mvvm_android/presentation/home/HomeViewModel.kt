package com.davoh.moviedb_mvvm_android.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davoh.moviedb_mvvm_android.data.PopularMovies
import com.davoh.moviedb_mvvm_android.data.toDomainPopularMovies
import com.davoh.moviedb_mvvm_android.framework.retrofit.responses.GetPopularResponse
import com.davoh.moviedb_mvvm_android.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel(){
    
    private val _popularMovieList = MutableLiveData<PopularMovies>()
    val popularMovies : LiveData<PopularMovies> = _popularMovieList
    
    
    fun getPopularMovies(pageNum: Int){
        movieRepository.getPopularMovies(pageNum)
            .map(GetPopularResponse::toDomainPopularMovies)
            .doOnSubscribe {}
            .subscribe({
                _popularMovieList.value = it
            },{
            
            })
    }
    
    
    
}