package com.example.aflamy.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.state.DataState
import com.example.domain.usecase.remoteUseCase.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMovies,
) : ViewModel() {

    private val _popularMovies: MutableStateFlow<DataState<BaseResponse<List<PopularResponse>>>> =
        MutableStateFlow(DataState.Idle)
    val popularMovies = _popularMovies.asStateFlow()

    fun getNewPlayingMovies(apiKey: String) {
        viewModelScope.launch {
            getPopularMoviesUseCase(apiKey).collectLatest { dataState ->
                _popularMovies.value = dataState
                Log.e("TAG", "getNewPlayingMovies: $dataState")
            }
        }
    }
}