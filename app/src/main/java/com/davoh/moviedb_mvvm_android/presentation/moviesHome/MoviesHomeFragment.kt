package com.davoh.moviedb_mvvm_android.presentation.moviesHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.databinding.FragmentMoviesHomeBinding
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.entities.toDomainMovieList
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.entities.toDomainMovieList
import com.davoh.moviedb_mvvm_android.presentation.BaseFragment
import com.davoh.moviedb_mvvm_android.presentation.moviesHome.adapters.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesHomeFragment : BaseFragment() {

    private lateinit var binding : FragmentMoviesHomeBinding
    private val viewModel  by activityViewModels<MoviesHomeViewModel>()
    private lateinit var popularMovieAdapter : MovieAdapter
    private lateinit var favoriteMovieAdapter : MovieAdapter
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesHomeBinding.inflate(inflater, container, false)
        configView()
        observeData()
        return binding.root
    }
    
    private fun configView(){
        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        popularMovieAdapter = MovieAdapter()
        binding.rvPopular.adapter = popularMovieAdapter
        
        binding.rvViewed.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        favoriteMovieAdapter = MovieAdapter()
        binding.rvViewed.adapter = favoriteMovieAdapter
        
        
        popularMovieAdapter.setOnItemClickListener( object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(item: Movie) {
                val data = Bundle()
                data.putParcelable("movie", item)
                data.putBoolean("isFavorite", false)
                findNavController().navigate(R.id.movieDetailFragment, data)
            }
        })
        favoriteMovieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener{
            override fun onItemClick(item: Movie) {
                val data = Bundle()
                data.putParcelable("movie", item)
                data.putBoolean("isFavorite", true)
                findNavController().navigate(R.id.movieDetailFragment, data)
            }
            
        })
    }
    
    private fun observeData(){
        viewModel.getPopularMoviesLocal().observe(requireActivity(), { movies->
            popularMovieAdapter.submitList(movies.toDomainMovieList())
        })
        viewModel.getFavoriteMovies().observe(requireActivity(),{ favoriteMovies->
            favoriteMovieAdapter.submitList(favoriteMovies.toDomainMovieList())
        })
        viewModel.isLoading.observe(requireActivity(), {isLoading-> showLoader(isLoading, binding.loaderLayout.progressBar)})
        viewModel.getPopularMovies(1)
    }
    

}