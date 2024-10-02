package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.MoviesForActorResponse
import com.example.domain.state.DataState
import com.example.domain.state.UiState
import kotlinx.coroutines.flow.Flow

interface MoviesForActorRepo {
    suspend fun getMoviesForActor(apiKey: String,personId:Int): MoviesForActorResponse
}