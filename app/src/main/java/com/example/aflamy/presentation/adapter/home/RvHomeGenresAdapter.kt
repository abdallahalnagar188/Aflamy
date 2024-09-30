package com.example.aflamy.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aflamy.databinding.ItemMovieTypeBinding
import com.example.domain.entity.dto.genres.GenreDto
import javax.inject.Inject


class RvHomeGenresAdapter @Inject constructor() :
    ListAdapter<GenreDto, RvHomeGenresAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemMovieTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemMovieTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(text: GenreDto) {
            binding.apply {
                movieTypeText.text = text.name
                root.setOnClickListener {
                    listener.onToRateItemClicked(text)
                }
            }
        }
    }



    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    interface OnItemClickListener {
        fun onToRateItemClicked(model: GenreDto)
    }


    //check difference
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<GenreDto>() {
            override fun areItemsTheSame(
                oldItem: GenreDto,
                newItem: GenreDto
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: GenreDto,
                newItem: GenreDto
            ) = oldItem == newItem
        }
    }
}