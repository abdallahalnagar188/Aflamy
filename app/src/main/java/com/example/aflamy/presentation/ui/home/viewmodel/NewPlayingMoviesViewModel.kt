package com.example.aflamy.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.state.DataState
import com.example.domain.usecase.remoteUseCase.GetNewPlayingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPlayingMoviesViewModel @Inject constructor(
    private val getNewPlayingMoviesUseCase: GetNewPlayingMovies,
): ViewModel() {

    private val _newPlayingMovies: MutableStateFlow<DataState<BaseResponse<List<NowPlayingMovieResponse>>>> = MutableStateFlow(DataState.Idle)
    val newPlayingMovies = _newPlayingMovies.asStateFlow()

    fun getNewPlayingMovies(apiKey: String) {
        viewModelScope.launch {
            getNewPlayingMoviesUseCase(apiKey).collectLatest { dataState ->
                _newPlayingMovies.value = dataState
                Log.e("TAG", "getNewPlayingMovies: $dataState")
            }
        }
    }
}