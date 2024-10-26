package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.MoviesForActorResponse

interface MoviesForActorRepo {
    suspend fun getMoviesForActor(apiKey: String,personId:Int): MoviesForActorResponse
}