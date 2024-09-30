package com.example.aflamy.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aflamy.R
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.genres.GenreDto
import com.example.domain.entity.dto.genres.GenresResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMoviesDto
import com.example.domain.state.DataState
import com.example.domain.state.UiState
import com.example.domain.state.UiText
import com.example.domain.usecase.remoteUseCase.GetGenres
import com.example.domain.usecase.remoteUseCase.GetNewPlayingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getGenresUseCase: GetGenres,
): ViewModel() {

    private val _genres: MutableStateFlow<UiState<GenresResponse>> = MutableStateFlow(UiState.Empty())
    val upComingMovies: StateFlow<UiState<GenresResponse>> get() = _genres



    fun getGenres(apiKey: String) {
        viewModelScope.launch {
            try {
                _genres.value = UiState.Loading()

                val list = getGenresUseCase(apiKey)


                    _genres.value = UiState.Success(list)

            } catch (e: Exception) {
                _genres.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }
        }
    }
}