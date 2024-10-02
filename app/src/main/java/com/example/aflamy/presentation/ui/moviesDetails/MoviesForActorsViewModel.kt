package com.example.aflamy.presentation.ui.moviesDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aflamy.R
import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.MoviesForActorResponse
import com.example.domain.state.DataState
import com.example.domain.state.UiState
import com.example.domain.state.UiText
import com.example.domain.usecase.remoteUseCase.GetMoviesForActors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesForActorsViewModel @Inject constructor(
    private val getMoviesForActorsUseCase: GetMoviesForActors
) : ViewModel() {

    private val _moviesForActor: MutableStateFlow<UiState<MoviesForActorResponse>> =
        MutableStateFlow(UiState.Loading())
    val moviesForActor = _moviesForActor.asStateFlow()

//    fun getMoviesForActors(apiKey: String, personId: Int) {
//        viewModelScope.launch { state ->
//            when (state) {
//                is DataState.Loading -> {
//                    _moviesForActor.value = UiState.Loading()
//                }
//
//                is DataState.Success -> {
//                    _moviesForActor.value = UiState.Success(state.data)
//                }
//
//                is DataState.Error -> {
//                    _moviesForActor.value = UiState.Error(state.message)
//
//                }
//            }
//        }
//    }

    fun getMoviesForActors(apiKey: String, personId: Int){
        viewModelScope.launch {
            try {
                _moviesForActor.value = UiState.Loading()
                val response = getMoviesForActorsUseCase(apiKey, personId)

                if (response.crew.isNullOrEmpty()) {
                    _moviesForActor.value = UiState.Empty()
                } else {
                    _moviesForActor.value = UiState.Success(response)
                }
                } catch (e: Exception) {
                _moviesForActor.value = UiState.Error(UiText.StringResource(R.string.error_message))



            }

        }
    }
}