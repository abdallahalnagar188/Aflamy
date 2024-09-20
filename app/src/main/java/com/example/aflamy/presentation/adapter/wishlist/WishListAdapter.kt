package com.example.aflamy.presentation.adapter.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.databinding.ItemSeeMoreMoviesBinding
import com.example.domain.entity.models.MovieModel
import com.example.domain.entity.models.MovieModelForLocal
import javax.inject.Inject

class WishListAdapter @Inject constructor() :
    ListAdapter<MovieModelForLocal, WishListAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemSeeMoreMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MovieViewHolder(private val binding: ItemSeeMoreMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition)?.let {
                        listener.onItemClicked(it)
                    }
                }
            }
        }

        fun bind(movie: MovieModelForLocal) {
            binding.apply {
                movieRating.text = String.format("%.1f", movie.voteAverage ?: 0.0)
                movieTitle.text = movie.title
                movieYear.text = movie.releaseDate
//                movieGenre.text = movie.overview
            //    movieDuration.text = movie.originalLanguage.toString()
                Glide.with(moviePoster.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(moviePoster)
            }
        }
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(model: MovieModelForLocal)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieModelForLocal>() {
            override fun areItemsTheSame(oldItem: MovieModelForLocal, newItem: MovieModelForLocal): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieModelForLocal, newItem: MovieModelForLocal): Boolean =
                oldItem == newItem
        }
    }
}