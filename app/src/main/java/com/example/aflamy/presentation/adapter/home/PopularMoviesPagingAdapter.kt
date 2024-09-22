package com.example.aflamy.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.databinding.ItemSeeMoreMoviesBinding
import com.example.domain.entity.models.MovieModel
import javax.inject.Inject

class PopularMoviesPagingAdapter @Inject constructor() :
    PagingDataAdapter<MovieModel, PopularMoviesPagingAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

     private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemSeeMoreMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    inner class MovieViewHolder(private val binding: ItemSeeMoreMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition).let {
                        if (it != null) {
                            listener.onItemClicked(it)
                        }
                    }
                }
            }
        }
        fun bind(movie: MovieModel) {
            binding.apply {
                movieRating.text = String.format("%.1f", movie?.voteAverage ?: 0.0)
                movieTitle.text = movie.title
                movieYear.text = movie.releaseDate
//                movieGenre.text = movie.overview
                movieDuration.text = movie.originalLanguage.toString()
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
        fun onItemClicked(model: MovieModel)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                oldItem == newItem
        }
    }
}
