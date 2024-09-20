package com.example.aflamy.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentHomeBinding
import com.example.aflamy.genrel.navOptionsAnimation
import com.example.aflamy.genrel.navOptionsFromBottomAnimation
import com.example.aflamy.presentation.adapter.home.RvHomeNowPlayingMoviesAdapter
import com.example.aflamy.presentation.adapter.home.RvHomePopularMoviesAdapter
import com.example.aflamy.presentation.adapter.home.RvHomeTopRateMoviesAdapter
import com.example.aflamy.presentation.adapter.home.RvHomeUpComingMoviesAdapter
import com.example.aflamy.presentation.dialog.LoadingDialog
import com.example.aflamy.presentation.ui.BindingFragment
import com.example.aflamy.presentation.viewmodel.HomeViewModel
import com.example.domain.entity.models.MovieModel
import com.example.domain.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(),
    RvHomePopularMoviesAdapter.OnItemClickListener,
    RvHomeTopRateMoviesAdapter.OnItemClickListener,
    RvHomeNowPlayingMoviesAdapter.OnItemClickListener,
    RvHomeUpComingMoviesAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    private val homeViewModel: HomeViewModel by viewModels()


    @Inject
    lateinit var rvHomePopularMoviesAdapter: RvHomePopularMoviesAdapter

    @Inject
    lateinit var rvHomeTopRateMoviesAdapter: RvHomeTopRateMoviesAdapter

    @Inject
    lateinit var rvHomeNowPlayingMoviesAdapter: RvHomeNowPlayingMoviesAdapter

    @Inject
    lateinit var rvHomeUpComingMoviesAdapter: RvHomeUpComingMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPopularMovies.adapter = rvHomePopularMoviesAdapter
        binding.homeTopRate.rvTopRate.adapter = rvHomeTopRateMoviesAdapter
        binding.homeNowPlaying.rvNowPlaying.adapter = rvHomeNowPlayingMoviesAdapter
        binding.homUpComing.rvUpComing.adapter = rvHomeNowPlayingMoviesAdapter

        setupListeners()
        finishApp()
        fetchMovies()

    }

    private fun finishApp() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish() // Close the app
                }
            }
        )
    }

    private fun fetchMovies() {
        homeViewModel.getPopularMovies(API_Key)
        homeViewModel.getTopRateMovies(API_Key)
        homeViewModel.getNewPlayingMovies(API_Key)
        homeViewModel.getUpComingMovies(API_Key)

        fetchPopularMovies()
        fetchTopRateMovies()
        fetchNowPlayingMovies()
        fetchUpComingMovies()

    }

    private fun setupListeners() {
        binding.tvSeeMorePopularMovies.setOnClickListener {
            findNavController().navigate(R.id.seeMorePopularMoviesFragment, null, navOptionsAnimation())
        }
        binding.homeTopRate.tvTopRatSeeMore.setOnClickListener {
            findNavController().navigate(R.id.seeMoreTopRateMoviesFragment,null, navOptionsAnimation())
        }
        binding.homeTopBar.ivSearch.setOnClickListener {

           // findNavController().navigate(R.id.searchFragment)
        }
        binding.homeTopBar.ivNotification.setOnClickListener {

            // findNavController().navigate(R.id.notificationFragment)
        }
        binding.homeTopBar.ivSettings.setOnClickListener {

            //findNavController().navigate(R.id.settingsFragment)
        }

    }

    private fun fetchPopularMovies() {
        lifecycleScope.launch {
            homeViewModel.popularMovies.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        // Show loading dialog
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        // Dismiss loading dialog and update RecyclerView
                        LoadingDialog.dismissDialog()
                        setupPopularRv(state.data ?: emptyList())
                        Log.e("TAG", "fetchPopularMovies: ${state.data}")
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
                            "fetchPopularMovies: ${state.message?.asString(requireContext())}"
                        )
                    }

                    is UiState.Empty -> {
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            "No popular movies found",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("TAG", "fetchPopularMovies: No popular movies found")
                    }
                }
            }
        }
    }

    private fun fetchTopRateMovies() {
        lifecycleScope.launch {
            homeViewModel.topRateMovies.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        // Show loading dialog
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        // Dismiss loading dialog and update RecyclerView
                        LoadingDialog.dismissDialog()
                        setupTopRateRv(state.data ?: emptyList())
                        Log.e("TAG", "fetchTopRateMovies: ${state.data}")
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
                            "fetchTopRateMovies: ${state.message?.asString(requireContext())}"
                        )
                    }

                    is UiState.Empty -> {
                        // Dismiss loading dialog and handle empty state
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            "No top-rated movies found",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("TAG", "fetchTopRateMovies: No top-rated movies found")
                    }
                }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        lifecycleScope.launch {
            homeViewModel.newPlayingMovies.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        LoadingDialog.dismissDialog()
                        setupNowPlayingRv(state.data ?: emptyList())
                        Log.e("TAG", "fetchNowPlayingMovies: ${state.data}")
                    }

                    is UiState.Error -> {
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            state.message?.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e(
                            "TAG",
                            "fetchNowPlayingMovies: ${state.message?.asString(requireContext())}"
                        )
                    }

                    is UiState.Empty -> {
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            "No now playing movies found",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("TAG", "fetchNowPlayingMovies: No now playing movies found")
                    }
                }
            }
        }
    }


    private fun fetchUpComingMovies() {
        lifecycleScope.launch {
            homeViewModel.upComingMovies.collect { result ->
                when (result) {
                    is UiState.Loading -> {
                        LoadingDialog.showDialog()
                    }

                    is UiState.Success -> {
                        LoadingDialog.dismissDialog()
                        setupUpComingRv(result.data ?: emptyList())

                    }

                    is UiState.Error -> {
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            result.message?.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    is UiState.Empty -> {
                        LoadingDialog.dismissDialog()
                        Toast.makeText(
                            requireContext(),
                            "No upcoming movies found",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        }
    }


    private fun setupPopularRv(list: List<MovieModel>) {
        rvHomePopularMoviesAdapter.submitList(list)
        rvHomePopularMoviesAdapter.setListener(this)
    }

    private fun setupTopRateRv(list: List<MovieModel>) {
        rvHomeTopRateMoviesAdapter.submitList(list)
        rvHomeTopRateMoviesAdapter.setListener(this)
    }

    private fun setupNowPlayingRv(list: List<MovieModel>) {
        rvHomeNowPlayingMoviesAdapter.submitList(list)
        rvHomeNowPlayingMoviesAdapter.setListener(this)
    }

    private fun setupUpComingRv(list: List<MovieModel>) {
        rvHomeUpComingMoviesAdapter.submitList(list)
        rvHomeUpComingMoviesAdapter.setListener(this)
    }

    override fun onPopularItemClicked(model: MovieModel) {

        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }, navOptionsAnimation()
        )
    }

    override fun onToRateItemClicked(model: MovieModel) {

        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }, navOptionsAnimation()
        )
    }

    override fun onNowPlayingItemClicked(model: MovieModel) {

        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }, navOptionsAnimation()
        )
    }

    override fun onUpComingItemClicked(model: MovieModel) {

        findNavController().navigate(R.id.moviesDetailsFragment, Bundle().apply {
            model.id?.let { putInt("movieId", it) }
        }, navOptionsAnimation()
        )
    }
}