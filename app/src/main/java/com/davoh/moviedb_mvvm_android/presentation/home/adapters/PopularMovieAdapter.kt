package com.davoh.moviedb_mvvm_android.presentation.home.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.davoh.moviedb_mvvm_android.data.Movie
import com.davoh.moviedb_mvvm_android.databinding.ItemMoviePopularBinding
import com.davoh.moviedb_mvvm_android.datasources.constants.Constants.imageUrl

class PopularMovieAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(DiffCallback()) {
    
    private var listener: OnItemClickListener ?= null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemMoviePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))
       
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ItemViewHolder).bind(getItem(position), position)
    }
    
    inner class ItemViewHolder(private val binding:ItemMoviePopularBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Movie, position: Int){
            binding.titleMovie.text = item.title
            Glide.with(binding.root)
                .load("${imageUrl}${item.posterPath}")
                //.circleCrop()
                //.placeholder(R.drawable.ic_no_photo)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(binding.poster)
            binding.poster.setOnClickListener {
                listener?.onItemClick(item)
            }
        }
    }
    
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
    interface OnItemClickListener{
        fun onItemClick(item: Movie)
    }
    
    
    
    class DiffCallback : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
        
    }
}


