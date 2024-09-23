package com.example.aflamy.presentation.adapter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.databinding.ItemActorsBinding
import com.example.aflamy.databinding.ItemSimilarMovieBinding
import javax.inject.Inject
import com.example.domain.entity.models.MovieModel


class RvSimilarMoviesAdapter @Inject constructor() :
    ListAdapter<MovieModel, RvSimilarMoviesAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemSimilarMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemSimilarMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(model: MovieModel) {
            binding.apply {
                tvActorName.text = model.title

                Glide.with(root.context)
                    .load("https://image.tmdb.org/t/p/w500" + model.posterPath)
                    //.placeholder(R.drawable.iv_no_image)
                 //   .error(R.drawable.iv_no_image)
                    .into(ivActorImage)
                // Handle click event
                root.setOnClickListener {
                    listener.onItemClicked(model)
                }
            }
        }
    }



    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    interface OnItemClickListener {
        fun onItemClicked(model: MovieModel)
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