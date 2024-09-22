package com.example.aflamy.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.constance.API_Key
import com.example.aflamy.databinding.FragmentHomeBinding
import com.example.aflamy.genrel.navOptionsAnimation
import com.example.aflamy.presentation.adapter.home.RvHomeNowPlayingMoviesAdapter
import com.example.aflamy.presentation.adapter.home.RvHomePopularMoviesAdapter
import com.example.aflamy.presentation.adapter.home.RvHomeTopRateMoviesAdapter
import com.example.aflamy.presentation.adapter.home.RvHomeUpComingMoviesAdapter
import com.example.aflamy.presentation.ui.BaseFragment
import com.example.aflamy.presentation.ui.home.viewmodel.NewPlayingMoviesViewModel
import com.example.aflamy.presentation.ui.home.viewmodel.PopularMoviesViewModel
import com.example.aflamy.presentation.ui.home.viewmodel.TopRateMoviesViewModel
import com.example.aflamy.presentation.ui.home.viewmodel.UpComingMoviesViewModel
import com.example.aflamy.presentation.viewmodel.HomeViewModel
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.entity.dto.topRate.TopRateResponse
import com.example.domain.entity.dto.upComing.UpComingMoviesResponse
import com.example.domain.entity.models.MovieModel
import com.example.domain.state.applyCommonSideEffects
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    RvHomePopularMoviesAdapter.OnItemClickListener,
    RvHomeTopRateMoviesAdapter.OnItemClickListener,
    RvHomeNowPlayingMoviesAdapter.OnItemClickListener,
    RvHomeUpComingMoviesAdapter.OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    private val homeViewModel: HomeViewModel by viewModels()
    private val nowMovieViewModel: NewPlayingMoviesViewModel by viewModels()
    private val popularViewModel: PopularMoviesViewModel by viewModels()
    private val topRateViewModel: TopRateMoviesViewModel by viewModels()
    private val upComingViewModel: UpComingMoviesViewModel by viewModels()


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

        popularViewModel.getNewPlayingMovies(API_Key)
        topRateViewModel.getTopRateMovies(API_Key)
        upComingViewModel.getUpComingMovies(API_Key)
        nowMovieViewModel.getNewPlayingMovies(API_Key)

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
            popularViewModel.popularMovies.collect { state ->
                state.applyCommonSideEffects(this@HomeFragment) {
                    setupPopularRv(popularToMovieModel(it.results ?: emptyList()))
                }
            }
        }
    }

    private fun fetchTopRateMovies() {
        lifecycleScope.launch {

            topRateViewModel.topRateMovies.collect { state ->
                state.applyCommonSideEffects(this@HomeFragment) {
                    setupTopRateRv(topRateToMovieModel(it.results ?: emptyList()))
                }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        lifecycleScope.launch {
            nowMovieViewModel.newPlayingMovies.collect { state ->
            state.applyCommonSideEffects(this@HomeFragment) {
                setupNowPlayingRv(nowPlayingToMovieModel(it.results ?: emptyList()))
            }

            }
            }
        }

    private fun fetchUpComingMovies() {
        lifecycleScope.launch {
            upComingViewModel.upComingMovies.collect { state ->
                state.applyCommonSideEffects(this@HomeFragment) {
                    setupUpComingRv(upComingToMovieModel(it.results ?: emptyList()))
                }
            }
        }
    }


    private fun nowPlayingToMovieModel(list: List<NowPlayingMovieResponse>): List<MovieModel> {
        return list.map { it.toMovieModel() }
    }

    private fun popularToMovieModel(list: List<PopularResponse>): List<MovieModel> {
        return list.map { it.toMovieModel() }
    }

    private fun topRateToMovieModel(list: List<TopRateResponse>): List<MovieModel> {
        return list.map { it.toMovieModel() }
    }

    private fun upComingToMovieModel(list: List<UpComingMoviesResponse>): List<MovieModel> {
        return list.map { it.toMovieModel() }
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