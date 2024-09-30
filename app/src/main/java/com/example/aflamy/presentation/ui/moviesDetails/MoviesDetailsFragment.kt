package com.example.aflamy.presentation.ui.moviesDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentMoviesDetailsBinding
import com.example.aflamy.genrel.navOptionsAnimation
import com.example.aflamy.presentation.adapter.details.RvMovieTypesAdapter
import com.example.aflamy.presentation.adapter.details.RvMoviesActorsAdapter
import com.example.aflamy.presentation.adapter.details.RvMoviesVideosAdapter
import com.example.aflamy.presentation.adapter.details.RvSimilarMoviesAdapter
import com.example.aflamy.presentation.dialog.LoadingDialog
import com.example.aflamy.presentation.ui.BaseFragment
import com.example.aflamy.presentation.viewmodel.MovieDetailsViewModel
import com.example.aflamy.presentation.viewmodel.WishlistViewModel
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.entity.dto.movieDetails.actors.Cast
import com.example.domain.entity.dto.movieDetails.viedos.Result
import com.example.domain.entity.models.MovieModel
import com.example.domain.state.StatusBarUtil
import com.example.domain.state.UiState
import com.example.domain.state.applyCommonSideEffects
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesDetailsFragment : BaseFragment<FragmentMoviesDetailsBinding>(),
//    RvMoviesVideosAdapter.OnItemClickListener, RvMoviesActorsAdapter.OnItemClickListener,
    RvSimilarMoviesAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMoviesDetailsBinding::inflate

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val movieViewModel: WishlistViewModel by viewModels()
    private val similarViewModel: SimilarMoviesViewModel by viewModels()
    private val args: MoviesDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var videoAdapter: RvMoviesVideosAdapter

    @Inject
    lateinit var actorsAdapter: RvMoviesActorsAdapter

    @Inject
    lateinit var similarAdapter: RvSimilarMoviesAdapter

    @Inject
    lateinit var movieTypesAdapter: RvMovieTypesAdapter

    private var currentMovie: MovieDetailsResponse? = null

    var isTextExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchMovieDetails()
        fetchMovieVideos()
        fetchMovieActors()
        fetchSimilarMovies()
        setupListener()
    }

    @SuppressLint("DefaultLocale")
    private fun assignData(model: MovieDetailsResponse?) {
        currentMovie = model
        binding.apply {
            tvMovieTitle.text = model?.originalTitle
            tvRating.text = String.format("%.1f", model?.voteAverage ?: 0.0)

            tvYear.text = model?.releaseDate
            tvDuration.text = getString(R.string.s_minute, model?.runtime.toString())
            movieTypesAdapter.submitList(model?.genres?.map { it?.name ?: "" })

            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${model?.backdropPath}")
                .into(imgBanner)
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${model?.posterPath}")
                .into(ivMovie)
            Log.e("genres", "assignData: ${model?.genres}")

            val spannedText = model?.overview ?: ""
            tvDescription.text = spannedText
            // Define a character limit for the collapsed state
            val maxCharCount = 100

            // Update the TextView based on the expanded state
            updateTextView(spannedText, maxCharCount)

            // Check if the content exceeds the character limit after it's rendered
            tvDescription.post {
                if (spannedText.length <= maxCharCount) {
                    binding.tvShowMore.visibility = View.GONE // Hide 'Show More' if text fits
                } else {
                    binding.tvShowMore.visibility = View.VISIBLE
                }
            }

            // Set a click listener on the 'Show More' TextView to toggle text expansion
            binding.tvShowMore.setOnClickListener {
                isTextExpanded = !isTextExpanded
                updateTextView(spannedText, maxCharCount)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewVideos.adapter = videoAdapter
        binding.rvActors.adapter = actorsAdapter
        binding.rvSimilarMovies.adapter = similarAdapter
        similarAdapter.setListener(this)
        binding.movieTypes.adapter = movieTypesAdapter
//        videoAdapter.setListener(this)
//        actorsAdapter.setListener(this)

    }

    private fun setVideoAdapter(list: List<Result>) {
        videoAdapter.submitList(list)
    }

    private fun setActorsAdapter(list: List<Cast>) {
        actorsAdapter.submitList(list)
    }

    private fun setSimilarAdapter(list: List<MovieModel>) {
        similarAdapter.submitList(list)
    }


    private fun updateTextView(spannedText: String, maxCharCount: Int) {
        binding.apply {
            if (isTextExpanded) {
                // Show full text
                tvDescription.text = spannedText
                tvShowMore.text = getString(R.string.show_less)
            } else {
                // Show truncated text if it exceeds maxCharCount
                if (spannedText.length > maxCharCount) {
                    val truncatedText = spannedText.subSequence(0, maxCharCount).toString() + "..."
                    tvDescription.text = truncatedText
                    tvShowMore.text = getString(R.string.show_more)
                } else {
                    tvDescription.text = spannedText
                    tvShowMore.visibility = View.GONE // Hide 'Show More' if the text is short
                }
            }
        }
    }
    private fun fetchSimilarMovies() {
        val movieId = args.movieId
        similarViewModel.getUpComingMovies(movieId, API_Key)

        lifecycleScope.launch {
            similarViewModel.similarMovies.collect { state ->
                state.applyCommonSideEffects(this@MoviesDetailsFragment) { result ->
                    setSimilarAdapter(result.results?.map { it.toMovieModel() } ?: emptyList())
                }
            }
        }
    }
    private fun fetchMovieActors() {
        val movieId = args.movieId
        viewModel.getMovieActors(movieId, API_Key)

        lifecycleScope.launch {
            viewModel.movieActors.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        // Show loading dialog
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        // Dismiss loading dialog and update UI with movie details
                        LoadingDialog.dismissDialog()
                        setActorsAdapter(state.data?.cast ?: emptyList())
                    }

                    is UiState.Error -> {
                        // Dismiss loading dialog and show error message
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            state.message?.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is UiState.Empty -> {}

                }
            }
        }
    }

    private fun fetchMovieVideos() {
        val movieId = args.movieId
        viewModel.getMovieVideos(movieId, API_Key)
        lifecycleScope.launch {
            viewModel.movieVideos.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        // Show loading dialog
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        // Dismiss loading dialog and update UI with movie details
                        LoadingDialog.dismissDialog()
                        setVideoAdapter(state.data?.results ?: emptyList())

                        Log.e("TAG", "fetchMovieDetails: ${state.data}")
                    }

                    is UiState.Error -> {
                        // Dismiss loading dialog and show error message
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            state.message?.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    is UiState.Empty -> {}
                }
            }


        }
    }
    private fun fetchMovieDetails() {
        val movieId = args.movieId
        viewModel.getMovieDetails(movieId, API_Key)
        lifecycleScope.launch {
            viewModel.movieDetails.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        // Show loading dialog
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        // Dismiss loading dialog and update UI with movie details
                        LoadingDialog.dismissDialog()
                        assignData(state.data)
                        Log.e("TAG", "fetchMovieDetails: ${state.data}")
                        val isBookmarked = movieViewModel.isMovieBookmarked(movieId)
                        if (isBookmarked) {

                            binding.btnBookmark.setImageResource(R.drawable.ic_wish_list)
                            // Set to default icon
                        } else {

                            binding.btnBookmark.setImageResource(R.drawable.ic_wish_border) // Set to bookmarked icon
                        }
                    }

                    is UiState.Error -> {
                        // Dismiss loading dialog and show error message
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            state.message?.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e(
                            "TAG",
                            "fetchMovieDetails: ${state.message?.asString(requireContext())}"
                        )
                    }

                    is UiState.Empty -> {
                        // Dismiss loading dialog and handle empty state
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            "No movie details found",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("TAG", "fetchMovieDetails: No movie details found")
                    }
                }
            }
        }
    }

    private fun setupListener() {
        StatusBarUtil.whiteWithBackground(requireActivity(), R.color.black)
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnBookmark.setOnClickListener {
                currentMovie?.let { movie ->
                    val movieForLocal = MovieModel(
                        id = movie.id ?: 0,
                        title = movie.originalTitle ?: "",
                        posterPath = movie.posterPath,
                        releaseDate = movie.releaseDate,
                        voteAverage = movie.voteAverage ?: 0.0
                    )

                    // Launch a coroutine to handle the suspend function
                    viewLifecycleOwner.lifecycleScope.launch {
                        val isBookmarked = movieViewModel.isMovieBookmarked(movieForLocal.id ?: 0)
                        if (isBookmarked) {
                            // Remove from wishlist
                            movieViewModel.removeMovieFromWishlist(movieForLocal)
                            btnBookmark.setImageResource(R.drawable.ic_wish_border) // Set to default icon
                        } else {
                            // Add to wishlist
                            movieViewModel.addMovieToWishlist(movieForLocal)
                            btnBookmark.setImageResource(R.drawable.ic_wish_list) // Set to bookmarked icon
                        }
                    }
                }
            }
        }
    }

    override fun onItemClicked(model: MovieModel) {

        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }
        )

    }
}
