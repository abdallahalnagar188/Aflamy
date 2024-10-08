package com.example.aflamy.presentation.ui.moviesDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentSeeMorePopularMoviesBinding
import com.example.aflamy.genrel.showToast
import com.example.aflamy.presentation.adapter.details.RvMoviesForActorsAdapter
import com.example.aflamy.presentation.dialog.LoadingDialog
import com.example.aflamy.presentation.ui.BaseFragment
import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.Cast
import com.example.domain.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesForActorsFragment : BaseFragment<FragmentSeeMorePopularMoviesBinding>(),
    RvMoviesForActorsAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSeeMorePopularMoviesBinding::inflate

    private val viewModel: MoviesForActorsViewModel by viewModels()

    @Inject
    lateinit var adapter: RvMoviesForActorsAdapter

    private val args: MoviesForActorsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchPopularMovies()
        setupListeners()
    }


    private fun setupRecyclerView() {
        binding.rvPopularMovies.adapter = adapter
        adapter.setListener(this)
    }

    private fun setupPopularRv(list: List<Cast>) {
        adapter.submitList(list)
    }

    private fun fetchPopularMovies() {
        // Collect the paging data from the ViewModel
        lifecycleScope.launch {
            viewModel.getMoviesForActors(apiKey = API_Key, personId = args.personId)
            viewModel.moviesForActor.collect { data ->
                when (data) {
                    is UiState.Loading -> {
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        LoadingDialog.dismissDialog()
                        setupPopularRv(data.data?.cast ?: emptyList())
                    }

                    is UiState.Error -> {
                        LoadingDialog.dismissDialog()
                        data.message?.asString(requireContext())?.let { showToast(it) }
                    }

                    is UiState.Empty -> {
                        LoadingDialog.dismissDialog()
                        binding.ivNoImage.visibility = View.VISIBLE
                    }
                }
            }

        }
    }



    private fun setupListeners() {
        binding.apply {
            topBar.ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
              topBar.tvTitle.text = "${args.personName} Movies"
        }
    }


    override fun onUpComingItemClicked(model: Cast) {
        findNavController().navigate(
            R.id.moviesDetailsFragment, Bundle().apply {
                model.id?.let { putInt("movieId", it) }
            }
        )
    }
}
