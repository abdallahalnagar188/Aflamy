package com.example.aflamy.presentation.ui.home.moviesByGenres

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.map
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentSeeMorePopularMoviesBinding
import com.example.aflamy.presentation.adapter.home.MoviesByGenresPagingAdapter
import com.example.aflamy.presentation.ui.BaseFragment
import com.example.aflamy.presentation.viewmodel.HomeViewModel
import com.example.domain.entity.models.MovieModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesByGenresFragment : BaseFragment<FragmentSeeMorePopularMoviesBinding>(),
    MoviesByGenresPagingAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSeeMorePopularMoviesBinding::inflate

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: MoviesByGenresPagingAdapter

    private val args: MoviesByGenresFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("genreId", "onToRateItemClicked: ${args.genreId}")
        setupRecyclerView()
        fetchPopularMovies()
        setupListeners()
    }


    private fun setupRecyclerView() {
        binding.rvPopularMovies.adapter = adapter
        adapter.setListener(this)
    }

    private fun fetchPopularMovies() {
        // Collect the paging data from the ViewModel
        lifecycleScope.launch {
            viewModel.getMoviesByGenresInPages(apiKey = API_Key, genreId = args.genreId.toString())
                .collect { pagingData ->
                    // Debug the data
                    pagingData.map { movieModel ->
                        Log.e("PagingData", "Movie: ${movieModel.title}, ID: ${movieModel.id}")
                    }
                    adapter.submitData(pagingData)
                }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setupListeners() {
        binding.apply {
            topBar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            topBar.tvTitle.text = "${args.genreName} Movies"
        }
    }

    override fun onItemClicked(model: MovieModel) {
        findNavController().navigate(
            R.id.moviesDetailsFragment, Bundle().apply {
                model.id?.let { putInt("movieId", it) }
            }
        )
    }
}
