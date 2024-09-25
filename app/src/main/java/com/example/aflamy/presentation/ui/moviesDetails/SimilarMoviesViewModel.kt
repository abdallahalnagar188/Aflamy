package com.example.aflamy.presentation.ui.moviesDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.movieDetails.similer.SimilarMoviesDto
import com.example.domain.state.DataState
import com.example.domain.usecase.remoteUseCase.GetSimilarMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMovies
) : ViewModel() {

    private val _similarMovies: MutableStateFlow<DataState<BaseResponse<List<SimilarMoviesDto>>>> =
        MutableStateFlow(DataState.Idle)
    val similarMovies = _similarMovies.asStateFlow()

    fun getUpComingMovies(movieId: Int,apiKey: String) {
        viewModelScope.launch {
            getSimilarMoviesUseCase(movieId,apiKey).collectLatest { dataState ->
                _similarMovies.value = dataState
                Log.e("TAG", "getNewPlayingMovies: $dataState")
            }
        }
    }
}