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
import com.example.aflamy.databinding.ItemMovieTypeBinding
import com.example.aflamy.databinding.ItemVideoBinding
import com.example.domain.entity.dto.movieDetails.actors.Cast
import com.example.domain.entity.dto.movieDetails.actors.Crew
import com.example.domain.entity.dto.movieDetails.viedos.Result
import javax.inject.Inject
import com.example.domain.entity.models.MovieModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener


class RvMovieTypesAdapter @Inject constructor() :
    ListAdapter<String, RvMovieTypesAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    //private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemMovieTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemMovieTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(text: String) {
            binding.apply {
                movieTypeText.text = text
            }
        }
    }



//    fun setListener(listener: OnItemClickListener) {
//        this.listener = listener
//    }
//    interface OnItemClickListener {
//        fun onToRateItemClicked(model: Cast)
//    }


    //check difference
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ) = oldItem == newItem
        }
    }
}