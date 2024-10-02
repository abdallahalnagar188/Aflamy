package com.example.aflamy.presentation.adapter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.databinding.ItemSeeMoreMoviesBinding
import com.example.aflamy.genrel.formatDate
import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.Cast
import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.Crew
import javax.inject.Inject


class RvMoviesForActorsAdapter @Inject constructor() :
    ListAdapter<Cast, RvMoviesForActorsAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemSeeMoreMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemSeeMoreMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Cast) {
            binding.apply {
                movieRating.text = String.format("%.1f", movie?.voteAverage ?: 0.0)
                movieTitle.text = movie.title
                movieYear.text = movie.releaseDate ?: ""
//                movieGenre.text = movie.overview
                movieDuration.text = movie.originalLanguage.toString()
                Glide.with(moviePoster.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(moviePoster)

                root.setOnClickListener {
                    listener.onUpComingItemClicked(movie)
                }

            }

        }

    }


    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onUpComingItemClicked(model: Cast)
    }


    //check difference
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(
                oldItem: Cast,
                newItem: Cast
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Cast,
                newItem: Cast
            ) = oldItem == newItem
        }
    }
}