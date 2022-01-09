package com.davoh.moviedb_mvvm_android.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.databinding.FragmentHomeBinding
import com.davoh.moviedb_mvvm_android.databinding.FragmentMovieDetailBinding
import com.davoh.moviedb_mvvm_android.datasources.constants.Constants
import com.davoh.moviedb_mvvm_android.presentation.BaseFragment
import java.util.*


class MovieDetailFragment :  BaseFragment(){
    
    private lateinit var binding : FragmentMovieDetailBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        configToolbar(binding.toolbarInclude.toolbar)
       
        val movie : Movie? = arguments?.getParcelable("movie")
        titleFragment(movie?.title?:"", binding.toolbarInclude.toolbarTitle)
       
        configView(movie)
        
        return binding.root
    }
    
    private fun configView(movie:Movie?){
        
        Glide.with(binding.root)
            .load("${Constants.imageUrl}${movie?.posterPath}")
            //.circleCrop()
            //.placeholder(R.drawable.ic_no_photo)
            .apply(RequestOptions.skipMemoryCacheOf(true))
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(binding.poster)
        
        binding.movieTitle.text = movie?.title
        binding.movieOverview.text = movie?.overview
        binding.movieRating.text = movie?.voteAverage.toString()
        
        binding.btnAddFavorite.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
    
    
    
}