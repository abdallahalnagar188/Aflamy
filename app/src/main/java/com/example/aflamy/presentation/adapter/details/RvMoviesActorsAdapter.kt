package com.example.aflamy.presentation.adapter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.R
import com.example.aflamy.databinding.ItemActorsBinding
import com.example.aflamy.databinding.ItemMovieHomeBinding
import com.example.aflamy.databinding.ItemVideoBinding
import com.example.domain.entity.dto.movieDetails.actors.Cast
import com.example.domain.entity.dto.movieDetails.actors.Crew
import com.example.domain.entity.dto.movieDetails.viedos.Result
import javax.inject.Inject
import com.example.domain.entity.models.MovieModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener


class RvMoviesActorsAdapter @Inject constructor() :
    ListAdapter<Cast, RvMoviesActorsAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemActorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemActorsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(model: Cast) {
            binding.apply {
                tvActorName.text = model.name

                Glide.with(root.context)
                    .load("https://image.tmdb.org/t/p/w500" + model.profilePath)
                    .placeholder(R.drawable.iv_no_image)
                    .error(R.drawable.iv_no_image)
                    .into(ivActorImage)
                // Handle click event
                root.setOnClickListener {
                    listener.onToRateItemClicked(model)
                }
            }
        }
    }



    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    interface OnItemClickListener {
        fun onToRateItemClicked(model: Cast)
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