package com.example.aflamy.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.entity.dto.topRate.TopRateResponse
import com.example.domain.state.DataState
import com.example.domain.usecase.remoteUseCase.GetPopularMovies
import com.example.domain.usecase.remoteUseCase.GetTopRateMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRateMoviesViewModel @Inject constructor(
    private val getTopRateMoviesUseCase: GetTopRateMovies
) : ViewModel() {

    private val _topRateMovies: MutableStateFlow<DataState<BaseResponse<List<TopRateResponse>>>> =
        MutableStateFlow(DataState.Idle)
    val topRateMovies = _topRateMovies.asStateFlow()

    fun getTopRateMovies(apiKey: String) {
        viewModelScope.launch {
            getTopRateMoviesUseCase(apiKey).collectLatest { dataState ->
                _topRateMovies.value = dataState
                Log.e("TAG", "getNewPlayingMovies: $dataState")
            }
        }
    }
}