package com.example.aflamy.presentation.ui.home.more.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentSeeMorePopularMoviesBinding
import com.example.aflamy.genrel.navOptionsAnimation
import com.example.aflamy.presentation.adapter.home.PopularMoviesPagingAdapter
import com.example.aflamy.presentation.dialog.LoadingDialog
import com.example.aflamy.presentation.ui.BaseFragment
import com.example.aflamy.presentation.viewmodel.HomeViewModel
import com.example.domain.entity.models.MovieModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SeeMorePopularMoviesFragment : BaseFragment<FragmentSeeMorePopularMoviesBinding>(),
    PopularMoviesPagingAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSeeMorePopularMoviesBinding::inflate

    private val viewModel: HomeViewModel by viewModels()
    @Inject
     lateinit var adapter: PopularMoviesPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoadingDialog.showDialog()
        setupRecyclerView()
        fetchPopularMovies()
        setupListeners()
        LoadingDialog.dismissDialog()

    }


    private fun setupRecyclerView() {
        // Initialize the adapter
        adapter = PopularMoviesPagingAdapter()
        adapter.setListener(this)
        binding.rvPopularMovies.adapter = adapter

    }
    private fun fetchPopularMovies() {
        // Collect the paging data from the ViewModel
        lifecycleScope.launch {
            viewModel.getPopularMoviesInPages(apiKey = API_Key).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun setupListeners() {
        binding.apply {
            topBar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            topBar.tvTitle.text = "More Popular Movies"
        }
    }

    override fun onItemClicked(model: MovieModel) {
        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }
        )
    }
}
