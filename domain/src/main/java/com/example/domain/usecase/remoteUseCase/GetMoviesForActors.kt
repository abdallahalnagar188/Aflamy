package com.example.domain.usecase.remoteUseCase

import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.MoviesForActorResponse
import com.example.domain.repo.repoRemote.MoviesForActorRepo

class GetMoviesForActors(private val moviesActorRepo: MoviesForActorRepo) {
    suspend operator fun invoke(apiKey: String,personId:Int): MoviesForActorResponse {
        return moviesActorRepo.getMoviesForActor(apiKey,personId)
    }

}