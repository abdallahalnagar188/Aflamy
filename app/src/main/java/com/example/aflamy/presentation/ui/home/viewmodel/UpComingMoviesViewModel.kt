package com.example.aflamy.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.entity.dto.topRate.TopRateResponse
import com.example.domain.entity.dto.upComing.UpComingMoviesResponse
import com.example.domain.state.DataState
import com.example.domain.usecase.remoteUseCase.GetPopularMovies
import com.example.domain.usecase.remoteUseCase.GetTopRateMovies
import com.example.domain.usecase.remoteUseCase.GetUpComingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpComingMoviesViewModel @Inject constructor(
    private val getUpComingMoviesUseCase: GetUpComingMovies
) : ViewModel() {

    private val _upComingMovies: MutableStateFlow<DataState<BaseResponse<List<UpComingMoviesResponse>>>> =
        MutableStateFlow(DataState.Idle)
    val upComingMovies = _upComingMovies.asStateFlow()

    fun getUpComingMovies(apiKey: String) {
        viewModelScope.launch {
            getUpComingMoviesUseCase(apiKey).collectLatest { dataState ->
                _upComingMovies.value = dataState
                Log.e("TAG", "getNewPlayingMovies: $dataState")
            }
        }
    }
}