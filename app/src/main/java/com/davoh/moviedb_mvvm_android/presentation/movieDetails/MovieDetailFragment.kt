package com.davoh.moviedb_mvvm_android.presentation.movieDetails

import android.content.res.Resources
import android.graphics.Color.red
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.databinding.FragmentMovieDetailBinding
import com.davoh.moviedb_mvvm_android.datasources.constants.Constants
import com.davoh.moviedb_mvvm_android.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment :  BaseFragment(){
    
    private lateinit var binding : FragmentMovieDetailBinding
    private val viewModel by activityViewModels<MovieDetailViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        configToolbar(binding.toolbarInclude.toolbar)
       
     
            val movie : Movie? = arguments?.getParcelable("movie")
            val isFavorite : Boolean? = arguments?.getBoolean("isFavorite", false)
            titleFragment(movie?.title?:"", binding.toolbarInclude.toolbarTitle)
    
            configView(movie, isFavorite)
            observeData()
        
      
        
        return binding.root
    }
    
    private fun configView(movie:Movie?, isFavorite:Boolean?){
        
        Glide.with(binding.root)
            .load("${Constants.imageUrl}${movie?.posterPath}")
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(binding.poster)
        
        binding.movieTitle.text = movie?.title
        binding.movieOverview.text = movie?.overview
        binding.movieRating.text = movie?.voteAverage.toString()
        
        if(isFavorite == true){
            binding.btnAddFavorite.text = getString(R.string.favorite_movie_delete)
            binding.btnAddFavorite.setBackgroundColor(resources.getColor(R.color.red))
        }else{
            binding.btnAddFavorite.text = getString(R.string.favorite_movie_add)
        }
        binding.btnAddFavorite.setOnClickListener {
            if (isFavorite == true){
                movie?.let { it1 -> viewModel.deleteMovieFavorite(it1) }
            }else{
                movie?.let { it1 -> viewModel.saveMovieFavorite(it1) }
            }
            findNavController().navigate(R.id.moviesHomeFragment)
        }
    }
    
    private fun observeData(){
        /*viewModel.messageAlert.observe(requireActivity(), { messageAlert->
            run {
                if(messageAlert.typeMessage == MessageAlertTypeError.ERROR.id){
                    messageAlert.title = "22"
                    messageAlert.content = "24"
                }else{
                    messageAlert.title = "22"
                    messageAlert.content = "24"
                }
                showMessageAlert(messageAlert)
            }
        })*/
    }
    
    
    
}