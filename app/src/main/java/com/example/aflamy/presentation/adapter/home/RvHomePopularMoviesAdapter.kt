package com.example.aflamy.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.databinding.ItemMovieHomeBinding
import com.example.aflamy.databinding.ItemMovieInTopHomeBinding
import com.example.domain.entity.dto.popularMovies.Result
import com.example.domain.entity.models.MovieModel
import javax.inject.Inject


class RvHomePopularMoviesAdapter @Inject constructor() :
    ListAdapter<MovieModel, RvHomePopularMoviesAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemMovieInTopHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemMovieInTopHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(model: MovieModel) {

            binding.apply {
                tvMovieName.text = model.title
                tvDate.text = model.releaseDate
                tvRate.text = String.format("%.1f", model?.voteAverage ?: 0.0)

                val imageUrl = "https://image.tmdb.org/t/p/w500${model.backdropPath}"
                Glide.with(ivMovie.context)
                    .load(imageUrl)
                    .into(ivMovie)

                root.setOnClickListener {
                    listener.onPopularItemClicked(model)
                }

            }
        }
    }


    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    interface OnItemClickListener {
        fun onPopularItemClicked(model: MovieModel)
    }


    //check difference
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ) = oldItem == newItem
        }
    }
}