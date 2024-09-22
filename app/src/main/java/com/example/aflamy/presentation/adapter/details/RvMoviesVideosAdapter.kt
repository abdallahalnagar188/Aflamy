package com.example.aflamy.presentation.adapter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aflamy.databinding.ItemMovieHomeBinding
import com.example.aflamy.databinding.ItemVideoBinding
import com.example.domain.entity.dto.movieDetails.viedos.Result
import javax.inject.Inject
import com.example.domain.entity.models.MovieModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener


class RvMoviesVideosAdapter @Inject constructor() :
    ListAdapter<Result, RvMoviesVideosAdapter.ProductViewHolder>(PRODUCT_COMPARATOR) {
    private lateinit var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var youTubePlayer: YouTubePlayer

        fun bind(model: Result) {
            binding.apply {
                tvVideoTitle.text = model.name
                tvVideoDuration.text = model.publishedAt

                // Initialize YouTube player
                binding.youtubePlayerView.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        this@ProductViewHolder.youTubePlayer = youTubePlayer
                        youTubePlayer.loadVideo(model.key?:"", 0f) // Load the video using the key
                    }

                    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                        // Handle player state changes if necessary
                    }

                    // Implement other YouTubePlayerListener methods as needed
                    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {}
                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {}
                    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {}
                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {}
                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }
                })

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
        fun onToRateItemClicked(model: Result)
    }


    //check difference
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ) = oldItem == newItem
        }
    }
}