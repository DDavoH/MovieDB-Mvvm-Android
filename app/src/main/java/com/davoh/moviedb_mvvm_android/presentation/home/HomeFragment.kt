package com.davoh.moviedb_mvvm_android.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davoh.moviedb_mvvm_android.R
import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.databinding.FragmentHomeBinding
import com.davoh.moviedb_mvvm_android.presentation.home.adapters.PopularMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
   
    private lateinit var binding : FragmentHomeBinding
    private val viewModel  by activityViewModels<HomeViewModel>()
    private lateinit var popularMovieAdapter : PopularMovieAdapter
    private lateinit var viewedMovieAdapter : PopularMovieAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false )
        
        configView()
        observeData()
        
        return binding.root
    }
    
    private fun configView(){
        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        popularMovieAdapter = PopularMovieAdapter()
        binding.rvPopular.adapter = popularMovieAdapter
        
        binding.rvViewed.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewedMovieAdapter = PopularMovieAdapter()
        binding.rvViewed.adapter = viewedMovieAdapter
    
    
        popularMovieAdapter.setOnItemClickListener( object : PopularMovieAdapter.OnItemClickListener {
            override fun onItemClick(item: Movie) {
                val data = Bundle()
                data.putParcelable("movie", item)
                findNavController().navigate(R.id.movieDetailFragment, data)
            }
        })
    }
    
    private fun observeData(){
        viewModel.popularMovies.observe(requireActivity(), { movies->
            popularMovieAdapter.submitList(movies.movieList)
            viewedMovieAdapter.submitList(movies.movieList)
        })
        viewModel.getPopularMovies(1)
    }
}