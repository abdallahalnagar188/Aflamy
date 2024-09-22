package com.example.aflamy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aflamy.R
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.entity.dto.movieDetails.actors.MovieActorsResponse
import com.example.domain.entity.dto.movieDetails.viedos.MovieViediosResponse
import com.example.domain.state.UiState
import com.example.domain.state.UiText
import com.example.domain.usecase.remoteUseCase.GetMovieActors
import com.example.domain.usecase.remoteUseCase.GetMovieDetails
import com.example.domain.usecase.remoteUseCase.GetMovieVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetails,
    private val getVideoUseCase: GetMovieVideos,
    private val getMovieActorsUseCase: GetMovieActors
) : ViewModel() {

    private val _movieDetails: MutableStateFlow<UiState<MovieDetailsResponse>> = MutableStateFlow(UiState.Empty())
    val movieDetails: StateFlow<UiState<MovieDetailsResponse>> get() = _movieDetails

    private val _movieVideos: MutableStateFlow<UiState<MovieViediosResponse>> =
        MutableStateFlow(UiState.Empty())
    val movieVideos: StateFlow<UiState<MovieViediosResponse>> get() = _movieVideos

    private val _movieActors: MutableStateFlow<UiState<MovieActorsResponse>> =
        MutableStateFlow(UiState.Empty())
    val movieActors: StateFlow<UiState<MovieActorsResponse>> get() = _movieActors


    fun getMovieActors(movieId: Int, apiKey: String) {
        viewModelScope.launch {

            try {
                _movieActors.value = UiState.Loading()
                val movieActorsResponse = getMovieActorsUseCase(movieId, apiKey)

                if (movieActorsResponse == null) {
                    _movieActors.value = UiState.Empty()
                } else {
                    _movieActors.value = UiState.Success(movieActorsResponse)
                }

            } catch (e: Exception) {
                _movieActors.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }

        }

    }

    fun getMovieVideos(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {

                _movieVideos.value = UiState.Loading()

                val movieVideosResponse = getVideoUseCase(movieId, apiKey)

                if (movieVideosResponse == null) {
                    _movieVideos.value = UiState.Empty()
                } else {
                    _movieVideos.value = UiState.Success(movieVideosResponse)


                }

            } catch (e: Exception) {
                _movieVideos.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }

        }

    }



    fun getMovieDetails(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                // Set loading state
                _movieDetails.value = UiState.Loading()

                // Fetch movie details
                val movieDetailsResponse = getMovieDetailsUseCase(movieId, apiKey)

                // Check if the response is null
                if (movieDetailsResponse == null) {
                    _movieDetails.value = UiState.Empty()
                } else {
                    // Handle success case
                    _movieDetails.value = UiState.Success(movieDetailsResponse)
                }

            } catch (e: Exception) {
                // Handle error case
                _movieDetails.value = UiState.Error(UiText.StringResource(R.string.error_message))
            }
        }
    }
}
