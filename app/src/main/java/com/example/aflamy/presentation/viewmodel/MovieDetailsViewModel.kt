package com.example.aflamy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aflamy.R
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.state.UiState
import com.example.domain.state.UiText
import com.example.domain.usecase.remoteUseCase.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetails
) : ViewModel() {

    private val _movieDetails: MutableStateFlow<UiState<MovieDetailsResponse>> = MutableStateFlow(UiState.Empty())
    val movieDetails: StateFlow<UiState<MovieDetailsResponse>> get() = _movieDetails

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
