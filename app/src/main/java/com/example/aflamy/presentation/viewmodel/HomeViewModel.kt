package com.example.aflamy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.aflamy.R
import com.example.domain.entity.models.MovieModel
import com.example.domain.state.UiState
import com.example.domain.state.UiText
import com.example.domain.usecase.remoteUseCase.GetNewPlayingMovies
import com.example.domain.usecase.remoteUseCase.GetPopularMovies
import com.example.domain.usecase.remoteUseCase.GetPopularMoviesInPages
import com.example.domain.usecase.remoteUseCase.GetTopRateMovies
import com.example.domain.usecase.remoteUseCase.GetTopRateMoviesInPages
import com.example.domain.usecase.remoteUseCase.GetUpComingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMovies,
    private val getTopRateMoviesUseCase: GetTopRateMovies,
    private val getNewPlayingMoviesUseCase: GetNewPlayingMovies,
    private val getUpComingMoviesUseCase: GetUpComingMovies,
    private val getPopularMoviesInPagesUseCase: GetPopularMoviesInPages,
    private val getTopRateMoviesInPagesUseCase: GetTopRateMoviesInPages
) : ViewModel() {

    private val _popularMovies: MutableStateFlow<UiState<List<MovieModel>>> = MutableStateFlow(UiState.Empty())
    val popularMovies: StateFlow<UiState<List<MovieModel>>> get() = _popularMovies

    private val _topRateMovies: MutableStateFlow<UiState<List<MovieModel>>> = MutableStateFlow(UiState.Empty())
    val topRateMovies: StateFlow<UiState<List<MovieModel>>> get() = _topRateMovies

    private val _newPlayingMovies: MutableStateFlow<UiState<List<MovieModel>>> = MutableStateFlow(UiState.Empty())
    val newPlayingMovies: StateFlow<UiState<List<MovieModel>>> get() = _newPlayingMovies

    private val _upComingMovies: MutableStateFlow<UiState<List<MovieModel>>> = MutableStateFlow(UiState.Empty())
    val upComingMovies: StateFlow<UiState<List<MovieModel>>> get() = _upComingMovies

    suspend fun getPopularMoviesInPages(apiKey: String): Flow<PagingData<MovieModel>> {
        return getPopularMoviesInPagesUseCase(apiKey).cachedIn(viewModelScope)
    }
    suspend fun getTopRateMoviesInPages(apiKey: String): Flow<PagingData<MovieModel>> {
        return getTopRateMoviesInPagesUseCase(apiKey).cachedIn(viewModelScope)
    }

    // Refactored function to handle UI state for popular movies
    fun getPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                _popularMovies.value = UiState.Loading()

                val list = getPopularMoviesUseCase(apiKey).results?.map { it.toMovieModel() }

                if (list.isNullOrEmpty()) {
                    _popularMovies.value = UiState.Empty()
                } else {
                    _popularMovies.value = UiState.Success(list)
                }

            } catch (e: Exception) {
                _popularMovies.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }
        }
    }

    // Refactored function to handle UI state for top-rated movies
    fun getTopRateMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                _topRateMovies.value = UiState.Loading()

                val list = getTopRateMoviesUseCase(apiKey).results?.map { it.toMovieModel() }

                if (list.isNullOrEmpty()) {
                    _topRateMovies.value = UiState.Empty()
                } else {
                    _topRateMovies.value = UiState.Success(list)
                }

            } catch (e: Exception) {
                _topRateMovies.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }
        }
    }

    // Refactored function to handle UI state for new playing movies
    fun getNewPlayingMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                _newPlayingMovies.value = UiState.Loading()

                val list = getNewPlayingMoviesUseCase(apiKey).results?.map { it.toMovieModel() }

                if (list.isNullOrEmpty()) {
                    _newPlayingMovies.value = UiState.Empty()
                } else {
                    _newPlayingMovies.value = UiState.Success(list)
                }

            } catch (e: Exception) {
                _newPlayingMovies.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }
        }
    }

    // Already refactored
    fun getUpComingMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                _upComingMovies.value = UiState.Loading()

                val list = getUpComingMoviesUseCase(apiKey).results?.map { it.toMovieModel() }

                if (list.isNullOrEmpty()) {
                    _upComingMovies.value = UiState.Empty()
                } else {
                    _upComingMovies.value = UiState.Success(list)
                }

            } catch (e: Exception) {
                _upComingMovies.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }
        }
    }
}


