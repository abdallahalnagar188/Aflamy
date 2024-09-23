package com.example.aflamy.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.R
import com.example.aflamy.databinding.ItemMovieHomeBinding
import com.example.aflamy.genrel.formatDate
import javax.inject.Inject
import com.example.domain.entity.models.MovieModel
import java.text.SimpleDateFormat
import java.util.Locale


class RvHomeTopRateMoviesAdapter @Inject constructor() :
    ListAdapter<MovieModel, RvHomeTopRateMoviesAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemMovieHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemMovieHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(model: MovieModel) {

            binding.apply {
                tvMovieName.text = model.title

//                // Format the release date to show only the year
//                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//                val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
//
//                val date = inputFormat.parse(model.releaseDate?:"")
//                val formattedDate = if (date != null) outputFormat.format(date) else model.releaseDate
                tvDate.text = formatDate(model.releaseDate?:"")

                tvRate.text = String.format("%.1f", model.voteAverage ?: 0.0)

                val imageUrl = "https://image.tmdb.org/t/p/w500${model.posterPath}"
                Glide.with(ivMovie.context)
                    .load(imageUrl)
                    .error(R.drawable.iv_no_image)
                    .into(ivMovie)

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
        fun onToRateItemClicked(model: MovieModel)
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